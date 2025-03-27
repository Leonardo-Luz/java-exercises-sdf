import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class GerenciadorLivroTest {
	@Test
	public void deveCancelarLivros() {
		// mocka o dao
		LivroDao livroDAOMock = mock(LivroDao.class);
		ArrayList<Livro> livrosReservados = new ArrayList<>();

		livrosReservados.add(new Livro(1, "sr dos aneis", 100, 0, true, LocalDate.now().minusDays(8)));
		livrosReservados.add(new Livro(2, "hobbit"	, 150, 0, true, LocalDate.now().minusDays(6)));
		livrosReservados.add(new Livro(3, "harry potter", 200, 0, true, LocalDate.now().minusDays(10)));

		// criar a gerenciador
		GerenciadorLivro gerenciadorLivro = new GerenciadorLivro(livroDAOMock);

		when(livroDAOMock.listarReservados()).thenReturn(livrosReservados);

		// testar o gerenciador
		gerenciadorLivro.cancelarReservas();

		verify(livroDAOMock).listarReservados();
		verify(livroDAOMock).alterar(livrosReservados.get(0));
	}

}
