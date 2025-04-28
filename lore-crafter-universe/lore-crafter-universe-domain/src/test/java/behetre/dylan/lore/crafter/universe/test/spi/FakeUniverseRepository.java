package behetre.dylan.lore.crafter.universe.test.spi;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.spi.UniverseRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FakeUniverseRepository implements UniverseRepository {

    private final Map<String, Universe> universeByName;

    public FakeUniverseRepository() {
        this(Collections.emptyList());
    }

    public FakeUniverseRepository(Collection<Universe> initialRepositoryContent) {
        this.universeByName = new HashMap<>();

        initialRepositoryContent.forEach(universe -> this.universeByName.put(universe.name(), universe));
    }

    @Override
    public Universe create(Universe universe) {
        this.universeByName.put(universe.name(), universe);
        return universe;
    }

    @Override
    public boolean contains(String name) {
        return this.universeByName.containsKey(name);
    }

    public int getUniverseCount() {
        return this.universeByName.size();
    }
}
