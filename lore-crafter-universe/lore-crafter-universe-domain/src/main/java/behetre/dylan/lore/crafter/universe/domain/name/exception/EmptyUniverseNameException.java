package behetre.dylan.lore.crafter.universe.domain.name.exception;

/** Thrown when the Universe name value is an empty string */
public final class EmptyUniverseNameException extends UniverseNameException {
    public EmptyUniverseNameException() {
        super("Universe name cannot be empty");
    }
}
