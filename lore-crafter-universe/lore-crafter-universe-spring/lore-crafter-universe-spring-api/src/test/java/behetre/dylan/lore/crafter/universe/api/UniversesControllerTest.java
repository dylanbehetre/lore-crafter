package behetre.dylan.lore.crafter.universe.api;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.AlreadyExistsUniverseException;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseUseCase;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.UniverseCreationCommand;
import behetre.dylan.lore.crafter.universe.spi.UniverseCreationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UniversesController.class)
class UniversesControllerTest {

    private static final Universe VALID_UNIVERSE;

    private static final String POST_UNIVERSES_PATH = "/universes";

    static {
        try {
            VALID_UNIVERSE = Universe.builder()
                                     .withIdentifier(1L)
                                     .withName("The Witch heritage")
                                     .withDescription("A fantastic universe containing witches, vampires and demons.")
                                     .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CreateUniverseUseCase createUniverseUseCase;

    @AfterEach
    void tearDown() {
        Mockito.reset(this.createUniverseUseCase);
    }

    @Test
    void givenValidUniverseCreationCommand_whenCreateUniverse_then201Created() throws Exception {
        // TODO : set up a domain-test module containing one valid universe and invalid use case ?
        // arrange
        final UniverseCreationCommand validUniverseCreationCommand = new UniverseCreationCommand(
                VALID_UNIVERSE.name(),
                VALID_UNIVERSE.description()
        );

        Mockito.when(this.createUniverseUseCase.execute(Mockito.eq(validUniverseCreationCommand))).thenReturn(VALID_UNIVERSE);

        // act
        final ResultActions requestResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(POST_UNIVERSES_PATH)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content("""
                                                      {
                                                        "name": "%s",
                                                        "description": "%s"
                                                      }""".formatted(
                                                      VALID_UNIVERSE.name(),
                                                      VALID_UNIVERSE.description()
                                              )
                                      )
        );

        // assert
        requestResult.andExpect(status().isCreated())
                     .andExpect(header().string("Location", "http://localhost/universes/1"))
                     .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                     .andExpect(content().json("""
                             {
                               "id": 1
                             }
                             """
                     ));

    }

    @Test
    void givenUniverseCreationCommandWithoutName_whenCreateUniverse_then400BadRequest() throws Exception {

        // act
        final ResultActions requestResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(POST_UNIVERSES_PATH)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content("""
                                              {
                                                "name": null,
                                                "description": "A fantastic universe containing witches, vampires and demons."
                                              }""")
        );

        // assert
        requestResult.andExpect(status().isBadRequest())
                     .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                     .andExpect(content().json("""
                             {
                               "status": 400,
                               "detail": "Universe name property cannot be null"
                             }
                             """
                     ));
    }

    @Test
    void givenUniverseCreationCommandWithEmptyName_whenCreateUniverse_then400BadRequest() throws Exception {

        // act
        final ResultActions requestResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(POST_UNIVERSES_PATH)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content("""
                                              {
                                                "name": "",
                                                "description": "A fantastic universe containing witches, vampires and demons."
                                              }""")
        );

        // assert
        requestResult.andExpect(status().isBadRequest())
                     .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                     .andExpect(content().json("""
                             {
                               "status": 400,
                               "detail": "Universe name property cannot be empty"
                             }
                             """
                     ));
    }

    @Test
    void givenUniverseCreationCommandWithAlreadyExistingName_whenCreateUniverse_then409Conflict() throws Exception {
        // arrange
        Mockito.when(this.createUniverseUseCase.execute(Mockito.any()))
               .thenThrow(new AlreadyExistsUniverseException(VALID_UNIVERSE.name()));

        // act
        final ResultActions requestResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(POST_UNIVERSES_PATH)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content("""
                                                      {
                                                        "name": "%s",
                                                        "description": "%s"
                                                      }""".formatted(
                                                      VALID_UNIVERSE.name(),
                                                      VALID_UNIVERSE.description()
                                              )
                                      )
        );

        // assert
        requestResult.andExpect(status().isConflict())
                     .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                     .andExpect(content().json("""
                             {
                               "status": 409,
                               "detail": "Universe name '%s' already exists"
                             }
                             """.formatted(VALID_UNIVERSE.name())
                     ));
    }

    @Test
    void givenUniverseCreationCommandThatThrow_whenCreateUniverse_then500InternalServerError() throws Exception {
        // arrange
        final Exception wrappedException = new RuntimeException();

        Mockito.when(this.createUniverseUseCase.execute(Mockito.any()))
               .thenThrow(new UniverseCreationException(wrappedException));

        // act
        final ResultActions requestResult = this.mockMvc.perform(
                MockMvcRequestBuilders.post(POST_UNIVERSES_PATH)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content("""
                                                      {
                                                        "name": "%s",
                                                        "description": "%s"
                                                      }""".formatted(
                                                      VALID_UNIVERSE.name(),
                                                      VALID_UNIVERSE.description()
                                              )
                                      )
        );

        // assert
        requestResult.andExpect(status().isInternalServerError())
                     .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
                     .andExpect(content().json("""
                             {
                               "status": 500,
                               "detail": "Something go wrong when creating universe: %s"
                             }
                             """.formatted(wrappedException.getMessage())
                     ));
    }

}