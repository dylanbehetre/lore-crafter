package behetre.dylan.lore.crafter.universe.api.create;

import behetre.dylan.lore.crafter.universe.api.model.CreateUniverseCommand;
import behetre.dylan.lore.crafter.universe.domain.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;

public class CreateUniverseCommandWrapper {

    private final CreateUniverseCommand createUniverseCommand;

    public CreateUniverseCommandWrapper(CreateUniverseCommand createUniverseCommand) {
        this.createUniverseCommand = createUniverseCommand;
    }

    public behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseCommand toDomainModel() throws NoUniverseNameException, EmptyUniverseNameException {
        return new behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseCommand(
                new UniverseName(this.createUniverseCommand.getName()),
                new UniverseDescription(this.createUniverseCommand.getDescription())
        );
    }
}
