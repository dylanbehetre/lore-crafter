package behetre.dylan.lore.crafter.universe.domain;

/**
 * Definition of a universe
 * @param name name of the universe. Act as a functional identifier and must be unique
 * @param description universe's description
 */
public record Universe(String name, String description) {
}
