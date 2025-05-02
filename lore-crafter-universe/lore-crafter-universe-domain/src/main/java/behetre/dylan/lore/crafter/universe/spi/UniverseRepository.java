package behetre.dylan.lore.crafter.universe.spi;

import behetre.dylan.lore.crafter.universe.domain.universe.Universe;
import behetre.dylan.lore.crafter.universe.domain.universe.name.UniverseName;

public interface UniverseRepository {

    Universe create(Universe universe);

    boolean contains(UniverseName universeName);
}
