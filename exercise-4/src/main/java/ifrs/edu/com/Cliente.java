package ifrs.edu.com;

import java.time.LocalDate;

public class Cliente {
	private String cpf;
	private String nome;
	private LocalDate dataNasc;

	public Cliente() {}

	public Cliente(String cpf, String nome, LocalDate dataNasc){
		this.setCpf(cpf);
		this.setnome(nome);
		this.setdataNasc(dataNasc);
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}

	private boolean cpfValidation (String cpf) {
		cpf = cpf.replaceAll("[^0-9]", "");

		int[] digits = cpf.chars().map(Character::getNumericValue).toArray();
		int sum1 = 0;
		int sum2 = 0;

		for (int i = 0; i < 9; i++) {
			sum1 += digits[i] * (10 - i);
			sum2 += digits[i] * (11 - i);
		}

		int remainder1 = sum1 % 11;
		int digit10 = (remainder1 < 2) ? 0 : 11 - remainder1;

		sum2 += digit10 * 2;
		int remainder2 = sum2 % 11;
		int digit11 = (remainder2 < 2) ? 0 : 11 - remainder2;

		return digits[9] == digit10 && digits[10] == digit11;
	}

	public void setCpf(String cpf) {
		boolean cpfFormatation = cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");

		if (!cpfFormatation) {
			throw new ClienteException("Cpf não está no formato correto!");
		}
		if (!this.cpfValidation(cpf)) {
			throw new ClienteException("Cpf não é válido!");
		}

		this.cpf = cpf;
	}

	public void setnome(String nome) {
		boolean nomeOnlyAlphabeticChars = nome.matches("[a-zA-Z ]+");

		if (!nomeOnlyAlphabeticChars) {
			throw new ClienteException("Nome deve ser composto por caracteres alfabéticos.");
		}

		this.nome = nome;
	}

	public void setdataNasc(LocalDate dataNasc) {
		boolean dataNascBefore1900 = dataNasc.toEpochDay() <= (LocalDate.of(1900, 12, 31).toEpochDay());
		boolean dataNascAfterToday = dataNasc.toEpochDay() > (LocalDate.now().toEpochDay());

		if (dataNascBefore1900) {
			throw new ClienteException("Data de Nascimento deve ser após 1900.");
		}

		if (dataNascAfterToday) {
			throw new ClienteException("Data de Nascimento deve ser menor que a data atual.");
		}


		this.dataNasc = dataNasc;
	}
}
