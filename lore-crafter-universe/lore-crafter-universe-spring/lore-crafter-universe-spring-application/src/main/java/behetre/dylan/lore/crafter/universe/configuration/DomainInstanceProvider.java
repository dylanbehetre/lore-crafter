package behetre.dylan.lore.crafter.universe.configuration;

import behetre.dylan.lore.crafter.universe.domain.usecase.create.CreateUniverseUseCase;
import behetre.dylan.lore.crafter.universe.spi.UniverseRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainInstanceProvider {

    @Bean
    public CreateUniverseUseCase createUniverseUseCase(UniverseRepository universeRepository) {
        return new CreateUniverseUseCase(universeRepository);
    }
}

