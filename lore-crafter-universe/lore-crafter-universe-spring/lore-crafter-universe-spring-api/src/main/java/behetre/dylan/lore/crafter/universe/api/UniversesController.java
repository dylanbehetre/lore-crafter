package behetre.dylan.lore.crafter.universe.api;

import behetre.dylan.lore.crafter.universe.api.create.CreateUniverseCommandWrapper;
import behetre.dylan.lore.crafter.universe.api.model.UniverseCreationCommand;
import behetre.dylan.lore.crafter.universe.api.model.UniverseCreationResult;
import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.name.exception.EmptyUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.name.exception.NoUniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.AlreadyExistsUniverseException;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseUseCase;
import behetre.dylan.lore.crafter.universe.spi.UniverseCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

@RestController
public class UniversesController implements UniversesApi {

    // 

    private final CreateUniverseUseCase createUniverseUseCase;

    @Autowired
    public UniversesController(CreateUniverseUseCase createUniverseUseCase) {
        this.createUniverseUseCase = createUniverseUseCase;
    }

    @Override
    public ResponseEntity<UniverseCreationResult> createUniverse(UniverseCreationCommand createUniverseCommand) throws NoUniverseNameException, EmptyUniverseNameException, UniverseCreationException, AlreadyExistsUniverseException {

        final Universe domainCreatedUniverse = this.createUniverseUseCase.execute(new CreateUniverseCommandWrapper(createUniverseCommand).toDomainModel());
        final UniverseCreationResult responseBody = toUniverseCreationResult(domainCreatedUniverse);

        return ResponseEntity.created(buildCreatedUniverseUriLocation(domainCreatedUniverse))
                             .body(responseBody);
    }

    private static UniverseCreationResult toUniverseCreationResult(Universe domainCreatedUniverse) {
        final BigDecimal universeId = new BigDecimal(domainCreatedUniverse.id().toLong());
        return new UniverseCreationResult(universeId);
    }

    private static URI buildCreatedUniverseUriLocation(Universe createdUniverse) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUniverse.id().toLong())
                .toUri();
    }

}
