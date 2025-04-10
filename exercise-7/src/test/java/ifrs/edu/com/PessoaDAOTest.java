package ifrs.edu.com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PessoaDAOTest {
    private Connection conn;
    private PessoaDAO pDao;

    @BeforeEach
    public void setup() {
        try {
            this.conn = ConnectionFactory.connect(Database.TEST);
            this.pDao = new PessoaDAO(conn);
            this.conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void rollback() {
        try {
            this.conn.rollback();
            this.conn.setAutoCommit(true);
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldInsert() {
        Pessoa p = new Pessoa("Robertinho", LocalDate.now());

        this.pDao.insert(p);
        assertEquals(p, this.pDao.get(p.getId()));
    }

    @Test
    public void shouldDelete() {
        this.pDao.delete(3);
        assertEquals(null, this.pDao.get(3));
    }

    @Test
    public void shouldUpdate() {
        Pessoa p = this.pDao.get(3);
        p.setNome("Carlinhos");

        this.pDao.update(p);
        assertEquals(p, this.pDao.get(3));
    }

    @Test
    public void shouldGet() {
        assertEquals("Claudio", this.pDao.get(3).getNome());
    }

    @Test
    public void shouldList() {
        List<Pessoa> pessoas = this.pDao.list(3, 0);

        List<Pessoa> target = new ArrayList<>();
        target.add(new Pessoa("Roberto", 1, LocalDate.of(1985, 3, 15)));
        target.add(new Pessoa("Jose", 2, LocalDate.of(1978, 11, 20)));
        target.add(new Pessoa("Claudio", 3, LocalDate.of(1992, 6, 1)));

        assertEquals(target, pessoas);
    }
}
