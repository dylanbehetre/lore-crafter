package behetre.dylan.lore.crafter.universe.spi;

public class UniverseCreationException extends Exception {

    public UniverseCreationException(Throwable cause) {
        super(buildMessage(cause), cause);
    }

    private static String buildMessage(Throwable cause) {
        return String.format("Something go wrong when creating universe: %s", cause.getMessage());
    }
}
