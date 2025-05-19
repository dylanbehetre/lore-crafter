package behetre.dylan.lore.crafter.universe.spi;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.UniverseCreationCommand;

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
    public Universe create(UniverseCreationCommand universeCreationCommand) throws UniverseCreationException {
        try {
            this.universeByName.put(universeCreationCommand.name(), toUniverse(universeCreationCommand));
            return this.universeByName.get(universeCreationCommand.name());
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

    private Universe toUniverse(UniverseCreationCommand universeCreationCommand) throws Exception {
        return Universe.builder()
                       .withIdentifier(this.getUniverseCount() + 1L)
                       .withName(universeCreationCommand.name())
                       .withDescription(universeCreationCommand.description())
                       .build();
    }

}
