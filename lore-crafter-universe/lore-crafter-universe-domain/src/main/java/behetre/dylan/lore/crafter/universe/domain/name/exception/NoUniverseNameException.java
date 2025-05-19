package behetre.dylan.lore.crafter.universe.domain.name.exception;

public final class NoUniverseNameException extends InvalidUniverseNameException {

    public NoUniverseNameException() {
        super("Universe name cannot be null");
    }

}
