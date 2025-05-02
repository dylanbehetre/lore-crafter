package behetre.dylan.lore.crafter.universe.domain.universe.name;

public class NullUniverseNameException extends RuntimeException implements UniverseNameException{
    public NullUniverseNameException() {
        super("Universe name cannot be null");
    }
}
