import java.time.LocalDate;
import java.util.List;

public class GerenciadorLivro {
	private LivroDao lDao;

	public GerenciadorLivro(LivroDao ldao) {
		this.lDao = ldao;
	}

	public void cancelarReservas() {
		// buscar livros reservados
		List<Livro> reservados = lDao.listarReservados();

		// percorre e verificar se a reserva é maior que 7 dias ou nao.
		// caso seja deve cancelarReserva
		reservados.forEach(reserva -> {
			long reservaDay = reserva.getDataReserva().toEpochDay();
			long expiringDay = LocalDate.now().minusDays(7).toEpochDay();
			boolean reservaExpired = reservaDay > expiringDay;

			if (reservaExpired) {
				int _idReserva = reserva.getId();
				Livro reservaCancelada = reservados.remove(_idReserva);
				// persistir no bd o livro alterado
				lDao.alterar(reservaCancelada);
			}
		});
	}
}
