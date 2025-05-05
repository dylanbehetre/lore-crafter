package behetre.dylan.lore.crafter.universe.domain.usecase.create;

import behetre.dylan.lore.crafter.universe.domain.AlreadyExistsUniverseException;
import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.spi.UniverseCreationException;
import behetre.dylan.lore.crafter.universe.spi.UniverseRepository;

public class CreateUniverseUseCase {


    private final UniverseRepository universeRepository;

    public CreateUniverseUseCase(UniverseRepository universeRepository) {
        this.universeRepository = universeRepository;
    }

    public Universe execute(CreateUniverseCommand createUniverseCommand) throws AlreadyExistsUniverseException, UniverseCreationException {
        assertUniqueUniverseName(createUniverseCommand.name());

        return this.universeRepository.create(createUniverseCommand);
    }

    private void assertUniqueUniverseName(final UniverseName universeName) throws AlreadyExistsUniverseException {
        if (this.universeRepository.contains(universeName)) {
            throw new AlreadyExistsUniverseException(universeName);
        }
    }
}
