package behetre.dylan.lore.crafter.universe.api.create;

import behetre.dylan.lore.crafter.universe.api.model.UniverseCreationCommand;
import behetre.dylan.lore.crafter.universe.domain.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;

public class CreateUniverseCommandWrapper {

    private final UniverseCreationCommand createUniverseCommand;

    public CreateUniverseCommandWrapper(UniverseCreationCommand createUniverseCommand) {
        this.createUniverseCommand = createUniverseCommand;
    }

    public behetre.dylan.lore.crafter.universe.domain.usecase.create.UniverseCreationCommand toDomainModel() throws NoUniverseNameException, EmptyUniverseNameException {
        return new behetre.dylan.lore.crafter.universe.domain.usecase.create.UniverseCreationCommand(
                new UniverseName(this.createUniverseCommand.getName()),
                new UniverseDescription(this.createUniverseCommand.getDescription())
        );
    }
}
