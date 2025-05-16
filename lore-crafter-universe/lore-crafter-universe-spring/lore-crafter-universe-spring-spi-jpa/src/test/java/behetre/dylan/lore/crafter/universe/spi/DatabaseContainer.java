package behetre.dylan.lore.crafter.universe.spi;

import org.testcontainers.containers.PostgreSQLContainer;

/** Database container for tests */
public class DatabaseContainer {

    private final static String IMAGE = "postgres:14.17";
    private final static String DATABASE_NAME = "universe";
    private final static String USERNAME = "universe_dev_admin";
    private final static String PASSWORD = "dev_admin";

    private final PostgreSQLContainer<?> postgreSQLContainer;

    public DatabaseContainer() {
        this.postgreSQLContainer = new PostgreSQLContainer<>(IMAGE)
                .withDatabaseName(DATABASE_NAME)
                .withUsername(USERNAME)
                .withPassword(PASSWORD);
    }

    public void start() {
        this.postgreSQLContainer.start();
        System.setProperty("spring.datasource.url", this.postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", this.postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", this.postgreSQLContainer.getPassword());
    }


    public void stop() {
        this.postgreSQLContainer.stop();
        System.clearProperty("spring.datasource.url");
        System.clearProperty("spring.datasource.username");
        System.clearProperty("spring.datasource.password");
    }
}
