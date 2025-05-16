package behetre.dylan.lore.crafter.universe.spi.container;

/** Database container for tests */
public interface DatabaseContainer extends AutoCloseable {

    void start();

    @Override
    void close() throws Exception;

    static DatabaseContainer ofPostgreSQL() {
        return new PostgreSQLContainer();
    }

}
