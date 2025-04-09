package ifrs.edu.com;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LivroTest {

	@Test
	@DisplayName("testa leitura positiva")
	public void testaLerPos() {
		Livro l = new Livro("Harry Potter 1", 300);
		l.ler(10);
		Assertions.assertEquals(10, l.getPaginasLidas());
	}

	@Test
	@DisplayName("testa leitura positiva de muitas paginas")
	public void testaLerPos2() {
		Livro l = new Livro("Harry Potter 1", 300);
		l.ler(350);
		Assertions.assertEquals(300, l.getPaginasLidas());
	}

	@Test
	@DisplayName("teste simples de sintaxe do mockito")
	public void testaMockito() {
		Livro livroFalso = mock(Livro.class);

		String titulo = "senhor dos aneis";
		livroFalso.setTitulo(titulo);
		System.out.println("chamada antes de simular get: " + livroFalso.getTitulo());

		// simulando a chamada de getTitulo()
		when(livroFalso.getTitulo()).thenReturn(titulo);

		System.out.println("chamada após simular get: " + livroFalso.getTitulo());

		// simulando a chamada de leEGetPagsFaltam()
		when(livroFalso.leEGetPagsFaltam(10)).thenReturn(100);

		System.out.println("chamada de leEGetPagsFaltam sem um parâmetro simulado:"
				+ livroFalso.leEGetPagsFaltam(5));

		System.out.println("chamada de leEGetPagsFaltam com um parâmetro simulado:"
				+ livroFalso.leEGetPagsFaltam(10));

		// verifica se o método getTitulo foi chamado duas vezes
		verify(livroFalso, times(2)).getTitulo();
	}

	@Test
	@DisplayName("testa reserva disponivel")
	public void testaReservar() {
		Livro l = new Livro("senhor dos aneis", 300);

		l.reservar("user@mail.com");

		Assertions.assertEquals(true, l.isReservado());
	}

	@Test
	@DisplayName("testa reserva RuntimeException")
	public void testaReservarException() {
		Livro l = new Livro("senhor dos aneis", 300);
		l.reservar("reservado@email.com");

		assertThrows(RuntimeException.class, () -> {
			l.reservar("user@mail.com");
		});
	}

	@Test
	@DisplayName("testa locar disponivel")
	public void testalocar() {
		Livro l = new Livro("senhor dos aneis", 300);
		l.locar("user@mail.com");

		Assertions.assertEquals(true, l.isLocado());
	}

	@Test
	@DisplayName("testa locar RuntimeException")
	public void testalocarException() {
		Livro l = new Livro("senhor dos aneis", 300);
		l.locar("locar@email.com");

		assertThrows(RuntimeException.class, () -> {
			l.locar("user@mail.com");
		});
	}

	@Test
	@DisplayName("testa cancelar reserva")
	public void testaCancelarReserva() {
		Livro l = new Livro("senhor dos aneis", 300);
		l.reservar("reservado@email.com");

		l.cancelaReserva();
		Assertions.assertEquals(false, l.isReservado());
	}

	@Test
	@DisplayName("testa cancelar reserva RuntimeException")
	public void testaCancelarReservaException() {
		Livro l = new Livro("senhor dos aneis", 300);

		assertThrows(RuntimeException.class, () -> {
			l.cancelaReserva();
		});
	}

	@Test
	@DisplayName("testa devolver")
	public void testaDevolver() {
		Livro l = new Livro("senhor dos aneis", 300);
		l.locar("reservado@email.com");

		l.devolver();
		Assertions.assertEquals(false, l.isLocado());
	}

	@Test
	@DisplayName("testa devolver RuntimeException")
	public void testaDevolverException() {
		Livro l = new Livro("senhor dos aneis", 300);

		assertThrows(RuntimeException.class, () -> {
			l.devolver();
		});
	}
}
