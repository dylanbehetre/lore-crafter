package behetre.dylan.lore.crafter.universe.domain.usecase.create;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.identifier.exception.NoUniverseIdentifierException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.InvalidUniverseNameException;
import behetre.dylan.lore.crafter.universe.spi.FakeUniverseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

class CreateUniverseUseCaseTest {

    final Universe validUniverse;

    CreateUniverseUseCaseTest() throws InvalidUniverseNameException, NoUniverseIdentifierException {
        this.validUniverse = Universe.builder()
                                     .withIdentifier(1L)
                                     .withName("Witchcraft's World")
                                     .withDescription("A fantastic universe containing witches and wizards.")
                                     .build();
    }

    @Test
    void givenConformCreateUniverseCommand_whenCreate_thenUniverseCreated() throws Exception {
        // arrange
        final Universe validUniverseToCreate = this.validUniverse;
        final UniverseCreationCommand universeCreationCommand = new UniverseCreationCommand(
                validUniverseToCreate.name(),
                validUniverseToCreate.description()
        );

        final FakeUniverseRepository universeRepository = new FakeUniverseRepository();
        final int initialRepositorySize = universeRepository.getUniverseCount();

        final CreateUniverseUseCase createUniverseUseCase = new CreateUniverseUseCase(universeRepository);

        // act
        final Universe createdUniverse = createUniverseUseCase.execute(universeCreationCommand);

        // assert
        Assertions.assertAll("Service must return the created universe",
                () -> Assertions.assertNotNull(createdUniverse.id()),
                () -> Assertions.assertEquals(validUniverseToCreate.name(), createdUniverse.name()),
                () -> Assertions.assertEquals(validUniverseToCreate.description(), createdUniverse.description())
        );

        Assertions.assertEquals(initialRepositorySize + 1, universeRepository.getUniverseCount(),
                "The universe must have been created");
    }

    @Test
    void givenAlreadyExistingUniverse_whenCreate_thenReceivedAnAlreadyExistUniverseException() {
        // arrange
        final Universe universe = this.validUniverse;
        final UniverseCreationCommand universeCreationCommand = new UniverseCreationCommand(universe.name(), null);

        final Collection<Universe> initialRepositoryContent = List.of(universe);
        final FakeUniverseRepository universeRepository = new FakeUniverseRepository(initialRepositoryContent);
        final int initialRepositorySize = universeRepository.getUniverseCount();

        final CreateUniverseUseCase createUniverseUseCase = new CreateUniverseUseCase(universeRepository);

        // act
        final AlreadyExistsUniverseException exception = Assertions.assertThrows(AlreadyExistsUniverseException.class,
                () -> createUniverseUseCase.execute(universeCreationCommand)
        );

        // assert
        final String expectedMessage = String.format("Universe with name '%s' already exists", universe.name());
        Assertions.assertEquals(expectedMessage, exception.getMessage());

        Assertions.assertEquals(initialRepositorySize, universeRepository.getUniverseCount(), "No universe should have been created");
    }


}