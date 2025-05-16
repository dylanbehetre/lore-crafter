package behetre.dylan.lore.crafter.universe.spi;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.name.exception.UniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UniverseJpaRepositoryTest {

    private static final DatabaseContainer DATABASE_CONTAINER = new DatabaseContainer();

    private final UniverseJpaRepository testedRepository;

    @Autowired
    public UniverseJpaRepositoryTest(UniverseJpaRepository testedRepository) {
        this.testedRepository = testedRepository;
    }

    @BeforeAll
    static void beforeAllTests() {
        DATABASE_CONTAINER.start();
    }

    @AfterAll
    static void afterAllTests() {
        DATABASE_CONTAINER.stop();
    }

    @Test
    void givenNoUniverse_whenCreateOne_thenItIsCreated() throws UniverseNameException, UniverseCreationException {
        // arrange
        final UniverseName expectedUniverseName = new UniverseName("Some Universe name");
        final UniverseDescription expectedUniverseDescription = new UniverseDescription("Some Universe description");

        final CreateUniverseCommand command = new CreateUniverseCommand(expectedUniverseName, expectedUniverseDescription);

        // act
        final Universe createdUniverse = testedRepository.create(command);

        // assert
        Assertions.assertThat(createdUniverse).isNotNull();
        Assertions.assertThat(createdUniverse.id()).isNotNull();
        Assertions.assertThat(createdUniverse.name()).isEqualTo(expectedUniverseName);
        Assertions.assertThat(createdUniverse.description()).isEqualTo(expectedUniverseDescription);

        Assertions.assertThat(testedRepository.contains(expectedUniverseName)).isTrue();
    }

}