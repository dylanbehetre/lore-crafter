# lore-crafter

This project is designed to facilitate my technical monitoring.

It consists of a tool for creating fictional universes with stories and enables immersion
within them through a character that would have been previously created.

# Project structure :

Each module, at root, concerns one business domain.
In each module, I split concerns into different modules. For example, for the universe business domain, there is :

- lore-crafer-universe-domain: contains only the business domain logic with a focus on the modeling ;
- lore-crafer-universe-spring: contains the spring implementation of the domain ;
- lore-crafer-universe-spring-application: contains the spring application using the domain ;
- lore-crafer-universe-spring-api: contains the spring implementation of domain API ;
- lore-crafer-universe-spring-spi-jpa: contains the spring implementation of domain SPI with JPA ;
- 