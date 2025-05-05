package behetre.dylan.lore.crafter.universe.test.spi;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseCommand;
import behetre.dylan.lore.crafter.universe.spi.UniverseCreationException;
import behetre.dylan.lore.crafter.universe.spi.UniverseRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FakeUniverseRepository implements UniverseRepository {

    private final Map<UniverseName, Universe> universeByName;

    public FakeUniverseRepository() {
        this(Collections.emptyList());
    }

    public FakeUniverseRepository(Collection<Universe> initialRepositoryContent) {
        this.universeByName = new HashMap<>();

        initialRepositoryContent.forEach(universe -> this.universeByName.put(universe.name(), universe));
    }

    @Override
    public Universe create(CreateUniverseCommand createUniverseCommand) throws UniverseCreationException {
        try {
            this.universeByName.put(createUniverseCommand.name(), toUniverse(createUniverseCommand));
            return this.universeByName.get(createUniverseCommand.name());
        } catch (Exception exception) {
            throw new UniverseCreationException(exception);
        }
    }

    @Override
    public boolean contains(UniverseName universeName) {
        return this.universeByName.containsKey(universeName);
    }

    public int getUniverseCount() {
        return this.universeByName.size();
    }

    private Universe toUniverse(CreateUniverseCommand createUniverseCommand) throws Exception {
        return Universe.builder()
                       .withIdentifier(this.getUniverseCount() + 1L)
                .withName(createUniverseCommand.name())
                .withDescription(createUniverseCommand.description())
                .build();
    }

}
