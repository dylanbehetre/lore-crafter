package behetre.dylan.lore.crafter.universe.domain.universe.name.exception;

public final class NullUniverseNameException extends UniverseNameException{
    public NullUniverseNameException() {
        super("Universe name cannot be null");
    }
}
