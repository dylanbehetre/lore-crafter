package behetre.dylan.lore.crafter.universe.domain.identifier.exception;

public class NoUniverseIdentifierException extends Exception implements UniverseIdentifierException {
    public NoUniverseIdentifierException() {
        super("Universe identifier cannot be null");
    }
}
