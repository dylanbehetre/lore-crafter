package behetre.dylan.lore.crafter.universe.domain;

import behetre.dylan.lore.crafter.universe.spi.UniverseRepository;
import behetre.dylan.lore.crafter.universe.test.spi.FakeUniverseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;

class UniverseServiceTest {

    final Universe validUniverse = new Universe("Witchcraft's World", "A fantastic universe containing witches and wizards.");

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
    void givenUniverseWithoutName_whenCreate_thenReceivedAnInvalidUniverseException() {
        // arrange
        final Universe universe = new Universe(null, this.validUniverse.description());

        final FakeUniverseRepository universeRepository = new FakeUniverseRepository();
        final int initialRepositorySize = universeRepository.getUniverseCount();

        final UniverseService universeService = new UniverseService(universeRepository);

        // act
        final InvalidUniverseException exception = Assertions.assertThrows(InvalidUniverseException.class,
                () -> universeService.create(universe)
        );

        // assert
        Assertions.assertEquals("Invalid universe: name is mandatory", exception.getMessage());
        Assertions.assertEquals(initialRepositorySize, universeRepository.getUniverseCount(), "No universe should have been created");
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