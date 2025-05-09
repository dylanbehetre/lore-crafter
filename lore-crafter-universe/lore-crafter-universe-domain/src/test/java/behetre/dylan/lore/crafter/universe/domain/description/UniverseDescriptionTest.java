package behetre.dylan.lore.crafter.universe.domain.description;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniverseDescriptionTest {

    @Test
    void givenDescription_whenToString_thenGetDescriptionAsString() {
        final String description = "My Universe";

        final UniverseDescription universeDescription = new UniverseDescription(description);

        assertEquals(description, universeDescription.toString());
    }

}