package behetre.dylan.lore.crafter.universe.domain.name.exception;

public sealed class UniverseNameException extends Exception permits EmptyUniverseNameException, NoUniverseNameException {
    public UniverseNameException(String message) {
        super(message);
    }
}
