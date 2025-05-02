package behetre.dylan.lore.crafter.universe.domain.universe.name;

import behetre.dylan.lore.crafter.universe.domain.universe.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.universe.name.exception.NullUniverseNameException;

import java.util.Objects;

public final class UniverseName {
    private final String name;

    /// Constructor
    /// @param name universe's name. Cannot be null or empty.
    /// @throws NullUniverseNameException if the name is null
    /// @throws EmptyUniverseNameException if the name is empty
    public UniverseName(String name) throws NullUniverseNameException, EmptyUniverseNameException {
        checkValidity(name);
        this.name = name;
    }

    private static void checkValidity(String name) throws NullUniverseNameException, EmptyUniverseNameException {
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

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UniverseName) obj;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}