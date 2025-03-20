package ifrs.edu.com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    private Cliente cliente;

    @BeforeEach
    public void setupClient() {
        this.cliente = new Cliente();
    }

    @Test
    public void cpfShouldBeCorrectedFormatted() {
        this.cliente.setCpf("111.444.777-35");
        assertEquals("111.444.777-35", this.cliente.getCpf());
    }

    @Test
    public void cpfShouldNotBeCorrectedFormatted() {
        assertThrows(ClienteException.class, () -> {
            this.cliente.setCpf("11144477735");
        });
    }


    @Test
    public void nameShouldBeOnlyAlfabeticChars() {
        this.cliente.setnome("Roberto Carlos");
        assertEquals("Roberto Carlos", this.cliente.getNome());
    }

    @Test
    public void nomeShouldNotBeOnlyAlfabeticChars() {
        assertThrows(ClienteException.class, () -> {
            this.cliente.setnome("J0sé_4lm31d4");
        });
    }

    @Test
    public void cpfShouldBeValid() {
        this.cliente.setCpf("111.444.777-35");
        assertEquals("111.444.777-35", this.cliente.getCpf());
    }

    @Test
    public void cpfShouldNotBeValid() {
        assertThrows(ClienteException.class, () -> {
            this.cliente.setCpf("111.222.333-11");
        });
    }


    @Test
    public void dataDeNascShouldBeValid() {
        this.cliente.setdataNasc(LocalDate.of(2002, 11, 7));
        assertEquals(LocalDate.of(2002, 11, 7), this.cliente.getDataNasc());
    }

    @Test
    public void dataDeNascShouldNotBeLessThan1900() {
        assertThrows(ClienteException.class, () -> {
            this.cliente.setdataNasc(LocalDate.of(1777, 7, 7));
        });
    }

    @Test
    public void dataDeNascShouldNotBeHigherThanToday() {
        assertThrows(ClienteException.class, () -> {
            this.cliente.setdataNasc(LocalDate.of(7777, 7, 7));
        });
    }
}
