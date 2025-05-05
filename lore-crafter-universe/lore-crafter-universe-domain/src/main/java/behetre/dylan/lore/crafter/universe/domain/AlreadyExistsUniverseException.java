package behetre.dylan.lore.crafter.universe.domain;

import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;

public class AlreadyExistsUniverseException extends Exception {

    public AlreadyExistsUniverseException(String universeName) {
        super(String.format("Universe with name '%s' already exists", universeName));
    }

    public AlreadyExistsUniverseException(UniverseName universeName) {
        this(universeName.toString());
    }

}
