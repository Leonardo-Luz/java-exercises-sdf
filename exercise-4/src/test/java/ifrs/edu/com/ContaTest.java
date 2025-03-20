package ifrs.edu.com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaTest {
    private Conta conta;
    private Cliente cliente;

    @BeforeEach
    public void setupTest() {
        this.cliente = new Cliente("111.444.777-35", "Roberto Carlos", LocalDate.of(2002, 11, 7));
        this.conta = new Conta(this.cliente, 800.0, 1100);
    }

    @Test
    public void saldoShouldBePositive() {
        assertEquals(800.0, this.conta.getSaldo(), 0.0001);
    }

    @Test
    public void saldoShouldBeNegative() {
        this.conta.saca(900);
        assertEquals(-100.0, this.conta.getSaldo(), 0.0001);
    }

    @Test
    public void saldoShouldNotBeLowerThanLimite() {
        assertThrows(ContaException.class, () -> this.conta.saca(2000));
    }

    @Test
    public void sacaShouldReceivePositive() {
        this.conta.saca(800);
        assertEquals(00.0, this.conta.getSaldo(), 0.0001);
    }

    @Test
    public void sacaShouldNotReceiveNegative() {
        assertThrows(ContaException.class, () -> this.conta.saca(-100));
    }

    @Test
    public void depositaShouldReceivePositive() {
        this.conta.deposita(800);
        assertEquals(1600.0, this.conta.getSaldo(), 0.0001);
    }

    @Test
    public void depositaShouldNotReceiveNegative() {
        assertThrows(ContaException.class, () -> this.conta.deposita(-100));
    }

    @Test
    public void transfereShouldNotReceiveNullConta() {
        assertThrows(NullPointerException.class, () -> this.conta.transfere(100, null));
    }

    @Test
    public void transfereShouldReceivePositive() {
        Conta conta = new Conta(null, 0, 10000);
        this.conta.transfere(100, conta);
        assertEquals(100.0, conta.getSaldo(), 0.0001);
    }

    @Test
    public void transfereShouldNotReceiveNegative() {
        Conta conta = new Conta(null, 0, 10000);

        assertThrows(ContaException.class, () -> this.conta.transfere(-100, conta));
    }
}
