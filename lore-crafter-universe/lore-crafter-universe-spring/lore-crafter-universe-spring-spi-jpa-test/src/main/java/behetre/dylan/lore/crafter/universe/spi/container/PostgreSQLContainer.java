package behetre.dylan.lore.crafter.universe.spi.container;

public class PostgreSQLContainer implements DatabaseContainer, AutoCloseable {

    private static final String IMAGE = "postgres:14.17";
    private static final String DATABASE_NAME = "universe";
    private static final String USERNAME = "universe_dev_admin";
    private static final String PASSWORD = "dev_admin";

    /** Wrapped container */
    private final org.testcontainers.containers.PostgreSQLContainer<?> postgreSQLTestContainer;

    public PostgreSQLContainer() {
        this(IMAGE, DATABASE_NAME, USERNAME, PASSWORD);
    }

    public PostgreSQLContainer(String dockerImageName, String databaseName, String username, String password) {
        //noinspection: resource closes when its instance will be closed
        this.postgreSQLTestContainer = new org.testcontainers.containers.PostgreSQLContainer<>(dockerImageName)
                .withDatabaseName(databaseName)
                .withUsername(username)
                .withPassword(password);
    }

    @Override
    public void start() {
        this.postgreSQLTestContainer.start();
        System.setProperty("spring.datasource.url", this.postgreSQLTestContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", this.postgreSQLTestContainer.getUsername());
        System.setProperty("spring.datasource.password", this.postgreSQLTestContainer.getPassword());
    }

    @Override
    public void close() throws Exception {
        this.postgreSQLTestContainer.stop();
        System.clearProperty("spring.datasource.url");
        System.clearProperty("spring.datasource.username");
        System.clearProperty("spring.datasource.password");
    }

}
