package behetre.dylan.lore.crafter.universe.spi.repository;

import behetre.dylan.lore.crafter.universe.spi.entity.UniverseEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface UniverseSpringRepository extends ListCrudRepository<UniverseEntity, Long> {

    boolean existsByName(String name);
}
