package ifrs.edu.com;

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
		int removeId = -1;

		// percorre e verificar se a reserva é maior que 7 dias ou nao.
		// caso seja deve cancelarReserva
		do {
			if (removeId >= 0) {
				Livro reservaCancelada = reservados.remove(removeId);
				lDao.alterar(reservaCancelada);
				removeId = -1;
			}

			for (int i = 0; i < reservados.size(); i++) {
				Livro reserva = reservados.get(i);

				long reservaDay = reserva.getDataReserva().toEpochDay();
				long expiringDay = LocalDate.now().minusDays(7).toEpochDay();
				boolean reservaExpired = reservaDay < expiringDay;

				if (reservaExpired) {
					removeId = i;
				}
			}
		} while (removeId >= 0);
	}

	public void avisarReservaNoFinal(EmailService emailService) {
		List<Livro> reservados = lDao.listarReservados();

		reservados.forEach(reserva -> {
			long reservaDay = reserva.getDataReserva().toEpochDay();
			long offsetDay = LocalDate.now().minusDays(5).toEpochDay();
			long limitDay = LocalDate.now().minusDays(7).toEpochDay();
			boolean reservaNotification = (reservaDay <= offsetDay) && (reservaDay >= limitDay);

			if (reservaNotification) {
				emailService.enviarEmail(
						String.format("A sua reserva do livro %s está prestes a expirar!",
								reserva.getTitulo()),
						"Reserva expirando", reserva.getEmailUsuario());
			}
		});
	}

	public void avisarLocacaoFinal(EmailService emailService) {
		List<Livro> locados = lDao.listarLocados();

		locados.forEach(locacao -> {
			long locacaoDay = locacao.getDataLocacao().toEpochDay();
			long offsetDay = LocalDate.now().minusDays(12).toEpochDay();
			long limitDay = LocalDate.now().minusDays(14).toEpochDay();
			boolean locacaoNotificationExpiring = (locacaoDay <= offsetDay) && (locacaoDay >= limitDay);
			boolean locacaoNotificationExpired = locacaoDay < offsetDay;

			if (locacaoNotificationExpiring) {
				emailService.enviarEmail(
						String.format("A sua locação do livro %s está prestes a expirar!",
								locacao.getTitulo()),
						"Locação expirando",
						locacao.getEmailUsuario());
			} else if (locacaoNotificationExpired) {
				emailService.enviarEmail(
						String.format("A sua locação do livro %s expirou!",
								locacao.getTitulo()),
						"Locação expirada",
						locacao.getEmailUsuario());
			}
		});
	}

	public void locar(int id, String email) {
		Livro livroLocar = lDao.get(id);
		livroLocar.locar(email);
		lDao.alterar(livroLocar);
	}

	public void reservar(int id, String email) {
		Livro livroReservar = lDao.get(id);
		livroReservar.reservar(email);
		lDao.alterar(livroReservar);
	}

	public void devolver(int id) {
		Livro livroDevolver = lDao.get(id);
		livroDevolver.devolver();
		lDao.alterar(livroDevolver);
	}
}
