package behetre.dylan.lore.crafter.universe.spi;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseCommand;

public interface UniverseRepository {

    Universe create(CreateUniverseCommand createUniverseCommand) throws UniverseCreationException;

    boolean contains(UniverseName universeName);
}
