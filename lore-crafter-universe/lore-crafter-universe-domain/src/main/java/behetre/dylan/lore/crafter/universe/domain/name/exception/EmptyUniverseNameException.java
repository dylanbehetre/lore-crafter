package behetre.dylan.lore.crafter.universe.domain.name.exception;

public final class EmptyUniverseNameException extends UniverseNameException{
    public EmptyUniverseNameException() {
        super("Universe name cannot be empty");
    }
}
