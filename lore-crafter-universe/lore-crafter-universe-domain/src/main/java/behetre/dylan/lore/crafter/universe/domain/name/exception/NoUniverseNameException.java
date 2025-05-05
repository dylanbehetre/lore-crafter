package behetre.dylan.lore.crafter.universe.domain.name.exception;

public final class NoUniverseNameException extends UniverseNameException {
    public NoUniverseNameException() {
        super("Universe name cannot be null");
    }
}
