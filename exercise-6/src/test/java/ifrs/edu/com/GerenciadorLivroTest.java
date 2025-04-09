package ifrs.edu.com;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GerenciadorLivroTest {
	private LivroDao livroDAOMock;
	private ArrayList<Livro> livrosReservados;
	private ArrayList<Livro> livrosLocados;
	private GerenciadorLivro gerenciadorLivro;

	@BeforeEach
	public void setup() {
		this.livroDAOMock = mock(LivroDao.class);
		this.livrosReservados = new ArrayList<>();
		this.livrosLocados = new ArrayList<>();

		this.livrosReservados.add(new Livro(1, "sr dos aneis", 100, 0, Reservado.RESERVADO,
				LocalDate.now().minusDays(8), null, "harry.Pot@magic.bol"));

		this.livrosReservados.add(new Livro(2, "hobbit", 150, 0, Reservado.RESERVADO,
				LocalDate.now().minusDays(6), null, "harry.Pot@magic.bol"));

		this.livrosReservados.add(new Livro(3, "harry potter", 200, 0, Reservado.RESERVADO,
				LocalDate.now().minusDays(10), null, "frodo0Cinzento@mail.com"));

		this.livrosLocados.add(new Livro(4, "sr dos aneis", 100, 0, Reservado.LOCADO,
				null, LocalDate.now().minusDays(8), "harry.Pot@magic.bol"));

		this.livrosLocados.add(new Livro(5, "hobbit", 150, 0, Reservado.LOCADO,
				null, LocalDate.now().minusDays(13), "harry.Pot@magic.bol"));

		this.livrosLocados.add(new Livro(6, "harry potter", 200, 0, Reservado.LOCADO,
				null, LocalDate.now().minusDays(16), "frodo0Cinzento@mail.com"));

		// criar a gerenciador
		this.gerenciadorLivro = new GerenciadorLivro(this.livroDAOMock);
	}

	@Test
	public void deveCancelarLivros() {
		when(this.livroDAOMock.listarReservados()).thenReturn(this.livrosReservados);
		Livro l = this.livrosReservados.get(0);

		this.gerenciadorLivro.cancelarReservas();

		verify(this.livroDAOMock).listarReservados();
		verify(this.livroDAOMock).alterar(l);
	}

	@Test
	public void deveLocarLivro() {
		Livro l = new Livro(0, "placeholder", 100, 0, Reservado.DISPONIVEL, null, null, null);
		when(this.livroDAOMock.get(0))
				.thenReturn(l);

		this.gerenciadorLivro.locar(0, "roberto.carlos@gmail.com");
		verify(this.livroDAOMock).alterar(l);
	}

	@Test
	public void deveReservarLivro() {
		Livro l = new Livro(0, "placeholder", 100, 0, Reservado.DISPONIVEL, null, null, null);
		when(this.livroDAOMock.get(0))
				.thenReturn(l);

		this.gerenciadorLivro.reservar(0, "ricardo_almeida@gmail.com");
		verify(this.livroDAOMock).alterar(l);
	}

	@Test
	public void deveDevolverLivro() {
		when(this.livroDAOMock.get(0))
				.thenReturn(this.livrosLocados.get(0));

		this.gerenciadorLivro.devolver(0);
		verify(this.livroDAOMock).alterar(this.livrosLocados.get(0));
	}

	@Test
	public void deveAvisarReservasNoFinal() {
		EmailService emailServiceMock = mock(EmailService.class);

		when(this.livroDAOMock.listarReservados()).thenReturn(this.livrosReservados);

		this.gerenciadorLivro.avisarReservaNoFinal(emailServiceMock);

		verify(this.livroDAOMock).listarReservados();
		verify(emailServiceMock).enviarEmail(
				String.format("A sua reserva do livro %s está prestes a expirar!",
						this.livrosReservados.get(1).getTitulo()),
				"Reserva expirando",
				this.livrosReservados.get(1).getEmailUsuario());
	}

	@Test
	public void deveAvisarLocacoesNoFinal() {
		EmailService emailServiceMock = mock(EmailService.class);

		when(this.livroDAOMock.listarLocados()).thenReturn(this.livrosLocados);

		this.gerenciadorLivro.avisarLocacaoFinal(emailServiceMock);

		verify(this.livroDAOMock).listarLocados();
		verify(emailServiceMock).enviarEmail(
				String.format("A sua locação do livro %s está prestes a expirar!",
						this.livrosLocados.get(1).getTitulo()),
				"Locação expirando",
				this.livrosLocados.get(1).getEmailUsuario());
		verify(emailServiceMock).enviarEmail(
				String.format("A sua locação do livro %s expirou!",
						this.livrosLocados.get(2).getTitulo()),
				"Locação expirada",
				this.livrosLocados.get(2).getEmailUsuario());
	}
}
