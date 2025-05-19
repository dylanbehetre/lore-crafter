package behetre.dylan.lore.crafter.universe.domain.identifier;

import behetre.dylan.lore.crafter.universe.domain.identifier.exception.NoUniverseIdentifierException;

/**
 * Universe identifier modeling
 *
 * @param id identifier as a non null Long value
 */
public record UniverseIdentifier(Long id) {

    public UniverseIdentifier {
        checkValidity(id);
    }

    public Long toLong() {
        return this.id;
    }

    private static void checkValidity(Long id) throws NoUniverseIdentifierException {
        if (id == null) {
            throw new NoUniverseIdentifierException();
        }
    }

}
