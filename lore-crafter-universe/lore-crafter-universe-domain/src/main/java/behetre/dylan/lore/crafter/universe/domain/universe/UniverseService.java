package behetre.dylan.lore.crafter.universe.domain.universe;

import behetre.dylan.lore.crafter.universe.spi.UniverseRepository;

public class UniverseService {

    private final UniverseRepository universeRepository;

    public UniverseService(
            UniverseRepository universeRepository
    ) {
        this.universeRepository = universeRepository;
    }

    public Universe create(Universe universe) throws AlreadyExistsUniverseException {
        if (this.universeRepository.contains(universe.name())) {
            throw new AlreadyExistsUniverseException(universe);
        }

        return this.universeRepository.create(universe);
    }

}
