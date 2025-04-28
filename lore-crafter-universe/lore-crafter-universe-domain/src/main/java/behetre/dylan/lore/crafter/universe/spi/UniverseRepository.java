package behetre.dylan.lore.crafter.universe.spi;

import behetre.dylan.lore.crafter.universe.domain.Universe;

public interface UniverseRepository {

    Universe create(Universe universe);

    boolean contains(String name);
}
