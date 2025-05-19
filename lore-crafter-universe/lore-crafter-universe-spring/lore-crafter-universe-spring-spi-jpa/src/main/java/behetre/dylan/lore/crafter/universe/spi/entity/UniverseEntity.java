package behetre.dylan.lore.crafter.universe.spi.entity;

import behetre.dylan.lore.crafter.universe.domain.InvalidUniversePropertyException;
import behetre.dylan.lore.crafter.universe.domain.Universe;
import behetre.dylan.lore.crafter.universe.domain.usecase.create.UniverseCreationCommand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "universe", schema = "public")
public class UniverseEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    @Column(name = "description")
    private String description;

    /**
     * Default constructor : required by JPA constraints
     *
     * @deprecated : required by JPA but use fully parameterized constructor is more maintainable by forcing fields update management
     */
    @Deprecated
    protected UniverseEntity() {
    }

    public UniverseEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Universe toUniverse() throws InvalidUniversePropertyException {
        return Universe.builder()
                       .withIdentifier(this.id)
                       .withName(this.name)
                       .withDescription(this.description)
                       .build();
    }

    public static UniverseEntity from(UniverseCreationCommand universeCreationCommand) {
        return new UniverseEntity(
                null, // a universe cannot have an identifier before it is created
                universeCreationCommand.name().toString(),
                universeCreationCommand.description().toString()
        );
    }

    /* Getters and Setters */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
