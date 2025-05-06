package behetre.dylan.lore.crafter.universe.domain;

import behetre.dylan.lore.crafter.universe.domain.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.identifier.UniverseIdentifier;
import behetre.dylan.lore.crafter.universe.domain.identifier.exception.NoUniverseIdentifierException;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.UniverseNameException;

public final class Universe {

    private final UniverseIdentifier id;
    private final UniverseName name;
    private final UniverseDescription description;

    /**
     * Constructor
     *
     * @param id          id of the universe. Act as a technical identifier and must be unique.
     * @param name        name of the universe. Act as a functional identifier and must be unique.
     * @param description universe's description
     * @throws NoUniverseIdentifierException if the id is null
     * @throws NoUniverseNameException       if the name is null
     */
    private Universe(UniverseIdentifier id, UniverseName name, UniverseDescription description) throws NoUniverseNameException, NoUniverseIdentifierException {
        assertIdValidity(id);
        assertNameValidity(name);

        this.id = id;
        this.name = name;
        this.description = description;
    }

    /* Getters */

    public UniverseIdentifier id() {
        return id;
    }

    public UniverseName name() {
        return name;
    }

    public UniverseDescription description() {
        return description;
    }

    /* Exposed static methods */

    /**
     * Creates a new builder for Universe
     *
     * @return a new Universe builder
     */
    public static UniverseBuilder builder() {
        return new UniverseBuilder();
    }

    /* Inner static methods */
    private static void assertIdValidity(UniverseIdentifier id) throws NoUniverseIdentifierException {
        if (id == null) {
            throw new NoUniverseIdentifierException();
        }
    }

    private static void assertNameValidity(UniverseName name) throws NoUniverseNameException {
        if (name == null) {
            throw new NoUniverseNameException();
        }
    }

    /**
     * Builder class for Universe
     */
    public static class UniverseBuilder {

        private UniverseIdentifier id;
        private UniverseName name;
        private UniverseDescription description;

        private UniverseBuilder() {
            // Private constructor to enforce the use of the builder() method
        }

        public UniverseBuilder withIdentifier(Long id) {
            this.id = new UniverseIdentifier(id);
            return this;
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
         * Sets the name of the universe
         *
         * @param name the name of the universe. Cannot be null.
         * @return this builder for method chaining
         */
        public UniverseBuilder withName(UniverseName name) {
            this.name = name;
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
         * Sets the description of the universe
         *
         * @param description the description of the universe
         * @return this builder for method chaining
         */
        public UniverseBuilder withDescription(UniverseDescription description) {
            this.description = description;
            return this;
        }

        /**
         * Builds a new Universe instance with the provided values
         *
         * @return a new Universe instance
         */
        public Universe build() throws NoUniverseNameException, NoUniverseIdentifierException {
            return new Universe(id, name, description);
        }
    }
}
