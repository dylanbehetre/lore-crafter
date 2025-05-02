package behetre.dylan.lore.crafter.universe.domain.universe.name.exception;

public sealed class UniverseNameException extends Exception permits EmptyUniverseNameException, NullUniverseNameException {
    public UniverseNameException(String message) {
        super(message);
    }
}
