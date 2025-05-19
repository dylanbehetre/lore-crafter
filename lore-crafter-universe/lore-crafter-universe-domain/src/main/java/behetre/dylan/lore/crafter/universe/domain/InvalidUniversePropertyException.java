package behetre.dylan.lore.crafter.universe.domain;

/// Parent exception for all exceptions related to an invalid universe property.
///
/// @implNote A `class` is preferred over an `interface` to serve as the parent for all exceptions related to a domain property,
/// **allowing a later switch between [Exception] and [RuntimeException]**.
public class InvalidUniversePropertyException extends RuntimeException {

    public InvalidUniversePropertyException(String message) {
        super(message);
    }

}
