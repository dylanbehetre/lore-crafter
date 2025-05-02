package behetre.dylan.lore.crafter.universe.domain.universe.name;

public class EmptyUniverseNameException extends RuntimeException implements UniverseNameException{
    public EmptyUniverseNameException() {
        super("Universe name cannot be empty");
    }
}
