package behetre.dylan.lore.crafter.universe.domain.usecase.create;

import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;

public class AlreadyExistsUniverseException extends Exception {

    public AlreadyExistsUniverseException(String universeName) {
        super(String.format("Universe name '%s' already exists", universeName));
    }

    public AlreadyExistsUniverseException(UniverseName universeName) {
        this(universeName.toString());
    }

}
