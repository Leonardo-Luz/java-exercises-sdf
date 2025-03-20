package ifrs.edu.com;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaTest {
    private Conta conta;

    @BeforeEach
    public void setupClient() {
        this.conta = new Conta();
    }

    @Test
    public void shouldAssertTrue() {
        assertTrue(true);
    }
}
