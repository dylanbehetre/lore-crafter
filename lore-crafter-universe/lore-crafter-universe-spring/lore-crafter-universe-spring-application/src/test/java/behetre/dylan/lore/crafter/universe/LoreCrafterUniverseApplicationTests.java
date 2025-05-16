package behetre.dylan.lore.crafter.universe;

import behetre.dylan.lore.crafter.universe.spi.container.DatabaseContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoreCrafterUniverseApplicationTests {

    private static final DatabaseContainer databaseContainer = DatabaseContainer.ofPostgreSQL();

    @BeforeAll
    static void beforeAll() {
        databaseContainer.start();
    }

    @AfterAll
    static void afterAll() throws Exception {
        databaseContainer.close();
    }

    @Test
    void contextLoads() {
    }

}
