package behetre.dylan.lore.crafter.universe.domain.universe;

import behetre.dylan.lore.crafter.universe.domain.universe.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.universe.name.exception.NullUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.universe.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.universe.name.exception.UniverseNameException;

import java.util.Objects;

/**
 * Definition of a universe
 */

public final class Universe {

    private final UniverseName name;
    private final UniverseDescription description;

    /**
     * @param name        name of the universe. Act as a functional identifier and must be unique
     * @param description universe's description
     * @throws NullUniverseNameException if the name is null
     */
    private Universe(UniverseName name, UniverseDescription description) throws NullUniverseNameException {
        if (name == null) {
            throw new NullUniverseNameException();
        }
        this.name = name;
        this.description = description;
    }


    /**
     * Creates a new builder for Universe
     *
     * @return a new Universe builder
     */
    public static UniverseBuilder builder() {
        return new UniverseBuilder();
    }

    public UniverseName name() {
        return name;
    }

    public UniverseDescription description() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Universe) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }

    @Override
    public String toString() {
        return "Universe[" +
                "name=" + name + ", " +
                "description=" + description + ']';
    }


    /**
     * Builder class for Universe
     */
    public static class UniverseBuilder {
        private UniverseName name;
        private UniverseDescription description;

        private UniverseBuilder() {
            // Private constructor to enforce the use of the builder() method
        }

        /**
         * Sets the name of the universe
         *
         * @param name the name of the universe. Cannot be null or empty.
         * @return this builder for method chaining
         */
        public UniverseBuilder withName(String name) throws UniverseNameException {
            this.name = new UniverseName(name);
            return this;
        }

        /**
         * Sets the description of the universe
         *
         * @param description the description of the universe
         * @return this builder for method chaining
         */
        public UniverseBuilder withDescription(String description) {
            this.description = new UniverseDescription(description);
            return this;
        }

        /**
         * Builds a new Universe instance with the provided values
         *
         * @return a new Universe instance
         */
        public Universe build() throws NullUniverseNameException {
            return new Universe(name, description);
        }
    }
}
