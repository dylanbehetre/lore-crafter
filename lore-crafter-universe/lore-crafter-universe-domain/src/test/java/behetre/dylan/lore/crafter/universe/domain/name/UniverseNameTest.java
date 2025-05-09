package behetre.dylan.lore.crafter.universe.domain.name;

import behetre.dylan.lore.crafter.universe.domain.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.UniverseNameException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class UniverseNameTest {

    @Test
    void givenValidName_whenCreateIt_thenItIsCreated() throws UniverseNameException {
        // act
        final String validName = "My Universe";

        // arrange
        final UniverseName createdUniverseName = new UniverseName(validName);

        // assert
        assertNotNull(createdUniverseName);
        assertEquals(validName, createdUniverseName.toString());
    }

    @Test
    void givenNullName_whenCreateIt_thenThrowNoUniverseNameException() {
        // act
        Executable testedAction = () -> new UniverseName(null);

        // assert
        final NoUniverseNameException thrownException = assertThrowsExactly(
                NoUniverseNameException.class,
                testedAction
        );

        assertEquals("Universe name cannot be null", thrownException.getMessage());
    }

    @Test
    void givenEmptyNameValue_whenCreateIt_thenThrowEmptyUniverseNameException() {
        // arrange
        final String emptyName = "";

        // act
        Executable testedAction = () -> new UniverseName(emptyName);

        // assert
        final EmptyUniverseNameException thrownException = assertThrowsExactly(
                EmptyUniverseNameException.class,
                testedAction
        );

        assertEquals("Universe name cannot be empty", thrownException.getMessage());
    }

}