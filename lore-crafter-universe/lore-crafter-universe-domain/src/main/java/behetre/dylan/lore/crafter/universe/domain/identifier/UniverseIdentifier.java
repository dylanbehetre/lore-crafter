package behetre.dylan.lore.crafter.universe.domain.identifier;

public record UniverseIdentifier(Long id) {

    public Long toLong() {
        return this.id;
    }

}
