package behetre.dylan.lore.crafter.universe.domain;

import behetre.dylan.lore.crafter.universe.spi.UniverseRepository;

public class UniverseService {

    private final UniverseRepository universeRepository;

    public UniverseService(
            UniverseRepository universeRepository
    ) {
        this.universeRepository = universeRepository;
    }

    public Universe create(Universe universe) throws InvalidUniverseException, AlreadyExistsUniverseException {
        if (universe.name() == null){
            throw new InvalidUniverseException("Invalid universe: name is mandatory");
        }

        if (this.universeRepository.contains(universe.name())){
            throw new AlreadyExistsUniverseException(universe);
        }

        return this.universeRepository.create(universe);
    }

}
