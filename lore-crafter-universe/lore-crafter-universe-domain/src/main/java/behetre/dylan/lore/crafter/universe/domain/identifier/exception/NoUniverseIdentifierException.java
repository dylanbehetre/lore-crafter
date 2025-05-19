package behetre.dylan.lore.crafter.universe.domain.identifier.exception;

public class NoUniverseIdentifierException extends InvalidUniverseIdentifierException {

    public NoUniverseIdentifierException() {
        super("Universe identifier cannot be null");
    }
}
