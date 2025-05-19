package behetre.dylan.lore.crafter.universe.domain.identifier;

import behetre.dylan.lore.crafter.universe.domain.identifier.exception.NoUniverseIdentifierException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class UniverseIdentifierTest {

    @Test
    void givenIdentifierValue_whenToLong_thenReturnLongEquivalentValue() {
        // act
        final UniverseIdentifier universeIdentifier = new UniverseIdentifier(1L);

        // assert
        final long expectedLongValue = 1L;
        Assertions.assertThat(universeIdentifier.toLong())
                  .isEqualTo(expectedLongValue);
    }

    @Test
    void givenNullIdentifierValue_whenConstruct_thenThrowDedicatedException() {
        Assertions.assertThatThrownBy(() -> new UniverseIdentifier(null))
                  .isExactlyInstanceOf(NoUniverseIdentifierException.class)
                  .hasMessage("Universe identifier cannot be null");
    }

}