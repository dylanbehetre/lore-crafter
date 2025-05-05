package behetre.dylan.lore.crafter.universe.domain.description;

public record UniverseDescription(String description) {

    @Override
    public String toString() {
        return description;
    }
}
