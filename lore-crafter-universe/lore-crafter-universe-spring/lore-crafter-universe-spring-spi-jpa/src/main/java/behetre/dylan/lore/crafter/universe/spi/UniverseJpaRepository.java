package behetre.dylan.lore.crafter.universe.spi;

import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.identifier.exception.NoUniverseIdentifierException;
import behetre.dylan.lore.crafter.universe.domain.name.UniverseName;
import behetre.dylan.lore.crafter.universe.domain.name.exception.UniverseNameException;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.AlreadyExistsUniverseException;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseCommand;
import behetre.dylan.lore.crafter.universe.spi.entity.UniverseEntity;
import behetre.dylan.lore.crafter.universe.spi.repository.UniverseSpringRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

@Repository
public class UniverseJpaRepository implements UniverseRepository {

    private final UniverseSpringRepository universeSpringRepository;

    @Autowired
    public UniverseJpaRepository(UniverseSpringRepository universeSpringRepository) {
        this.universeSpringRepository = universeSpringRepository;
    }

    @Override
    @Transactional
    public Universe create(CreateUniverseCommand createUniverseCommand) throws UniverseCreationException {
        final UniverseEntity universeEntityToCreate = UniverseEntity.from(createUniverseCommand);

        final UniverseEntity createdUniverseEntity;
        try {
            createdUniverseEntity = this.universeSpringRepository.save(universeEntityToCreate);
        } catch (DataIntegrityViolationException exception) {
            throw new UniverseCreationException(new AlreadyExistsUniverseException(createUniverseCommand.name()));
        }

        try {
            return createdUniverseEntity.toUniverse();
        } catch (NoUniverseIdentifierException | UniverseNameException e) {
            throw new UniverseCreationException(e);
        }

    }

    @Override
    public boolean contains(UniverseName universeName) {
        return this.universeSpringRepository.existsByName(universeName.toString());
    }
}
