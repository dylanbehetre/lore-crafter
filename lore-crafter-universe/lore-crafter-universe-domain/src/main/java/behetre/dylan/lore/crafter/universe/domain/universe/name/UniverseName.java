package behetre.dylan.lore.crafter.universe.domain.universe.name;

/**
 * Universe's name definition
 * @param name Universe's name. Must not be null or empty.
 */
public record UniverseName(String name) {

    /// Constructor
    public UniverseName {
        checkValidity(name);
    }

    private static void checkValidity(String name) {
        if (name == null) {
            throw new NullUniverseNameException();
        } else if (name.isBlank()) {
            throw new EmptyUniverseNameException();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}