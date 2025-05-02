package behetre.dylan.lore.crafter.universe.domain.universe;

import behetre.dylan.lore.crafter.universe.test.spi.FakeUniverseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class UniverseServiceTest {

    final Universe validUniverse = Universe.builder()
            .withName("Witchcraft's World")
            .withDescription("A fantastic universe containing witches and wizards.")
            .build();

    @Test
    void givenConformUniverse_whenCreate_thenUniverseCreated() throws Exception {
        // arrange
        final Universe universe = this.validUniverse;

        final FakeUniverseRepository universeRepository = new FakeUniverseRepository();
        final int initialRepositorySize = universeRepository.getUniverseCount();

        final UniverseService universeService = new UniverseService(universeRepository);

        // act
        final Universe createdUniverse = universeService.create(universe);

        // assert
        Assertions.assertAll("Service must return the created universe",
                () -> Assertions.assertEquals(universe.name(), createdUniverse.name()),
                () -> Assertions.assertEquals(universe.description(), createdUniverse.description())
        );

        Assertions.assertEquals(initialRepositorySize + 1, universeRepository.getUniverseCount(),
                "The universe must have been created");
    }

    @Test
    void givenAlreadyExistingUniverse_whenCreate_thenReceivedAnAlreadyExistUniverseException() {
        // arrange
        final Universe universe = this.validUniverse;

        final Collection<Universe> initialRepositoryContent = List.of(universe);
        final FakeUniverseRepository universeRepository = new FakeUniverseRepository(initialRepositoryContent);
        final int initialRepositorySize = universeRepository.getUniverseCount();

        final UniverseService universeService = new UniverseService(universeRepository);

        // act
        final AlreadyExistsUniverseException exception = Assertions.assertThrows(AlreadyExistsUniverseException.class,
                () -> universeService.create(universe)
        );

        // assert
        final String expectedMessage = String.format("Universe with name '%s' already exists", universe.name());
        Assertions.assertEquals(expectedMessage, exception.getMessage());

        Assertions.assertEquals(initialRepositorySize, universeRepository.getUniverseCount(), "No universe should have been created");
    }


}