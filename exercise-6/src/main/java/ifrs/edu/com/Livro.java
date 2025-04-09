package ifrs.edu.com;

import java.time.LocalDate;

public class Livro {
	private int id;
	private String titulo;
	private int paginasLidas;
	private int paginas;
	private Reservado reservado;
	private LocalDate dataReserva;
	private LocalDate dataLocacao;
	private String emailUsuario;

	public Livro(int id, String titulo, int paginas, int pagsLidas, Reservado reservado, LocalDate dataReserva,
			LocalDate dataLocacao, String emailUsuario) {
		this.id = id;
		this.paginas = paginas;
		this.titulo = titulo;
		this.reservado = reservado;
		this.dataReserva = dataReserva;
		this.dataLocacao = dataLocacao;
		this.paginasLidas = pagsLidas;
		this.emailUsuario = emailUsuario;
	}

	public Livro(String titulo, int paginas) {
		this.paginas = paginas;
		this.titulo = titulo;
		this.reservado = Reservado.DISPONIVEL;
		this.dataReserva = null;
		this.paginasLidas = 0;
	}

	public Livro() {
	}

	public void ler(int pags) {
		if (this.paginasLidas + pags < this.paginas) {
			paginasLidas = +pags;
		} else {
			this.paginasLidas = this.paginas;
		}
	}

	// retorna qntas paginas faltam
	public int leEGetPagsFaltam(int pgs) {
		this.ler(pgs);
		return this.paginas - this.paginasLidas;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPaginasLidas() {
		return this.paginasLidas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public boolean isReservado() {
		return reservado == Reservado.RESERVADO;
	}

	public boolean isLocado() {
		return reservado == Reservado.LOCADO;
	}

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public LocalDate getDataLocacao() {
		return dataLocacao;
	}

	public void reservar(String email) {
		if (this.reservado == Reservado.DISPONIVEL) {
			this.dataReserva = LocalDate.now();
			this.reservado = Reservado.RESERVADO;
			this.emailUsuario = email;
		} else {
			throw new RuntimeException("Livro já está reservado ou locado");
		}
	}

	public void cancelaReserva() {
		if (this.reservado == Reservado.RESERVADO) {
			this.dataReserva = null;
			this.reservado = Reservado.DISPONIVEL;
			this.emailUsuario = null;
		} else {
			throw new RuntimeException(
					"Não é possivel cancelar a reserva de um livro que não foi reservado");
		}
	}

	public void locar(String email) {
		if (this.reservado == Reservado.DISPONIVEL
				|| (this.emailUsuario == email && this.reservado == Reservado.RESERVADO)) {
			this.dataReserva = null;
			this.dataLocacao = LocalDate.now();
			this.reservado = Reservado.LOCADO;
			this.emailUsuario = email;
		} else {
			throw new RuntimeException("Livro já está locado ou reservado por outra pessoa!");
		}
	}

	public void devolver() {
		if (this.reservado == Reservado.LOCADO) {
			this.dataReserva = null;
			this.dataLocacao = null;
			this.reservado = Reservado.DISPONIVEL;
			this.emailUsuario = null;
		} else {
			throw new RuntimeException("Não é possivel devolver um livro que não foi locado");
		}
	}

	@Override
	public String toString() {
	    return "Livro{" +
		    "id=" + id +
		    ", paginas=" + paginas +
		    ", titulo='" + titulo + '\'' +
		    ", reservado=" + reservado +
		    ", dataReserva=" + dataReserva +
		    ", dataLocacao=" + dataLocacao +
		    ", paginasLidas=" + paginasLidas +
		    ", emailUsuario='" + emailUsuario + '\'' +
		    '}';
	}
}
