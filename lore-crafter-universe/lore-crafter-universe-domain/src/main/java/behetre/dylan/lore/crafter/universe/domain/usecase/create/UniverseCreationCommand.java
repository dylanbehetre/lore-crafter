package behetre.dylan.lore.crafter.universe.domain.usecase.create;

import behetre.dylan.lore.crafter.universe.domain.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;

public record UniverseCreationCommand(UniverseName name, UniverseDescription description) {

}
