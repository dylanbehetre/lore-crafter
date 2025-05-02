package behetre.dylan.lore.crafter.universe.domain.universe;

import behetre.dylan.lore.crafter.universe.domain.universe.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.universe.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.universe.name.exception.NullUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.universe.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.universe.name.exception.UniverseNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UniverseUniverseBuilderTest {

    @Test
    void givenNameAndDescription_whenBuildUniverse_thenUniverseIsCreated() throws UniverseNameException {
        // Given
        String name = "Test Universe";
        String description = "This is a test universe";

        // When
        Universe universe = Universe.builder()
                .withName(name)
                .withDescription(description)
                .build();

        // Then
        assertEquals(new UniverseName(name), universe.name());
        assertEquals(new UniverseDescription(description), universe.description());
    }

    @Test
    void givenNullName_whenBuildUniverse_thenThrowNullUniverseNameException() {
        // Given
        final Universe.UniverseBuilder builder = Universe.builder();

        // When
        Executable testedBuildAction = builder::build;

        // Then
        assertThrows(NullUniverseNameException.class, testedBuildAction);
    }

    @Test
    void givenEmptyName_whenBuildUniverse_thenThrowEmptyUniverseNameException() {
        // Given
        final String emptyName = "";

        // When
        final Executable testedAction = () -> Universe.builder().withName(emptyName);

        // Then
        assertThrows(EmptyUniverseNameException.class, testedAction);
    }
}
