package behetre.dylan.lore.crafter.universe.domain.universe;

public class AlreadyExistsUniverseException extends Exception {

    public AlreadyExistsUniverseException(Universe universe) {
        super(String.format("Universe with name '%s' already exists", universe.name()));
    }
}
