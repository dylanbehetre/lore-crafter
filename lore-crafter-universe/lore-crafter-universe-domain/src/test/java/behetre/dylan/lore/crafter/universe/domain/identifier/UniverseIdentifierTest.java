package behetre.dylan.lore.crafter.universe.domain.identifier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniverseIdentifierTest {

    @Test
    void givenIdentifierValue_whenToLong_thenReturnLongEquivalentValue() {
        // act
        final UniverseIdentifier universeIdentifier = new UniverseIdentifier(1L);

        // assert
        final long expectedLongValue = 1L;
        assertEquals(expectedLongValue, universeIdentifier.toLong());
    }

}