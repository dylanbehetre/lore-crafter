package behetre.dylan.lore.crafter.universe.domain.name;

import behetre.dylan.lore.crafter.universe.domain.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UniverseName(@NotNull @NotEmpty String name) {
    /// Constructor
    ///
    /// @param name universe's name. Cannot be null or empty.
    /// @throws NoUniverseNameException    if the name is null
    /// @throws EmptyUniverseNameException if the name is empty
    public UniverseName {
        checkValidity(name);
    }

    private static void checkValidity(String name) throws NoUniverseNameException, EmptyUniverseNameException {
        if (name == null) {
            throw new NoUniverseNameException();
        } else if (name.isBlank()) {
            throw new EmptyUniverseNameException();
        }
    }

    @Override
    public @NotNull String toString() {
        return this.name;
    }

}