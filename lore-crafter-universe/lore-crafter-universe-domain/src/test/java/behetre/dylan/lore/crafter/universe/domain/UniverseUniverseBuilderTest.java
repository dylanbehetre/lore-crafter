package behetre.dylan.lore.crafter.universe.domain;

import behetre.dylan.lore.crafter.universe.domain.description.UniverseDescription;
import behetre.dylan.lore.crafter.universe.domain.identifier.UniverseIdentifier;
import behetre.dylan.lore.crafter.universe.domain.identifier.exception.NoUniverseIdentifierException;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.UniverseNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UniverseUniverseBuilderTest {

    final Long validId = 1L;
    final String validName = "Test Universe";
    final String validDescription = "This is a test universe";

    @Test
    void givenAllFields_whenBuildUniverse_thenUniverseIsCreated() throws Exception {
        // Given

        // When
        final Universe universe = Universe.builder()
                                          .withIdentifier(validId)
                                          .withName(validName)
                                          .withDescription(validDescription)
                                          .build();

        // Then
        assertEquals(new UniverseIdentifier(validId), universe.id());
        assertEquals(new UniverseName(validName), universe.name());
        assertEquals(new UniverseDescription(validDescription), universe.description());
    }

    @Test
    void givenNoName_whenBuildUniverse_thenThrowNoUniverseNameException() {
        // Given
        final Universe.UniverseBuilder builder = Universe.builder()
                                                         .withIdentifier(this.validId);

        // When
        final Executable testedBuildAction = builder::build;

        // Then
        assertThrows(NoUniverseNameException.class, testedBuildAction);
    }

    @Test
    void givenNullName_whenBuildUniverse_thenThrowNoUniverseNameException() {
        // Given
        final Universe.UniverseBuilder builder = Universe.builder()
                                                         .withIdentifier(this.validId)
                                                         .withName((UniverseName) null);

        // When
        final Executable testedBuildAction = builder::build;

        // Then
        assertThrows(NoUniverseNameException.class, testedBuildAction);
    }

    @Test
    void givenNoIdentifier_whenBuildUniverse_thenThrowNoUniverseIdentifierException() throws UniverseNameException {
        // Given
        final Universe.UniverseBuilder builder = Universe.builder()
                                                         .withName(this.validName);

        // When
        final Executable testedBuildAction = builder::build;

        // Then
        assertThrows(NoUniverseIdentifierException.class, testedBuildAction);
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
