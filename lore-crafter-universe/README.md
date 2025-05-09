# lore-crafter

This project is designed to facilitate my technical monitoring.

It consists of a tool for creating fictional universes with stories and enables immersion
within them through a character that would have been previously created.

# Conception fonctionnelle

## Création d'un univers :

- Possibilité de nommer l'univers, définir ses caractéristiques (exemple : type d'univers - médiéval, futuriste,
  fantastique) et ses règles (physiques, magiques, etc.).
- Gestion d'une bibliothèque d'éléments propres à l'univers (races, cultures, objets, cartes).

## Création et personnalisation de personnages :

- Un éditeur de personnage avec des templates prédéfinis (exemple : héros, mentor, antagoniste).
- Champs dynamiques basés sur les caractéristiques de l'univers (compétences uniques, pouvoirs, inventaire, etc.).
- Gestion des relations entre personnages (alliances, rivalités).

## Création d'histoires interactives :

- Un éditeur où l'utilisateur peut définir des "nœuds narratifs" (scénarios avec choix multiples).
- Les personnages suivent ces histoires et interagissent avec les événements selon leurs caractéristiques.

## Mode collaboratif (optionnel) :

- Plusieurs utilisateurs peuvent contribuer à la création d'un univers ou à l'écriture d'une histoire.

---

# Proposition d'architecture technique

## Back-End

### Microservices :

- Utiliser une architecture microservices pour découper les fonctionnalités en plusieurs services REST :
    - **Service Univers** : Gestion de la création et mise à jour des univers fictifs.
    - **Service Personnages** : Gestion des personnages et de leurs interactions.
    - **Service Histoires** : Gestion des scénarios narratifs et leurs branches.
    - **Service Recommandation (Kafka)** : Proposer des histoires ou des personnages adaptés via Kafka.
  - **Service Notification (Kafka)** : Notifier les utilisateurs des modifications collaboratives ou des avancées dans
    les histoires.

### Technologies :

- **Spring Boot** pour la plupart des microservices REST.
- **Quarkus** pour les services natifs qui nécessitent une exécution rapide (exemple : tâches Kafka).

### Base de données :

- **PostgreSQL** ou **MongoDB** pour stocker les univers, personnages et histoires.
- **Redis** pour les caches et données volatiles.

### Kafka :

- Kafka est utilisé pour la synchronisation des services ou pour exécuter des processus asynchrones :
    - Proposer automatiquement des templates de personnages adaptés.
    - Calculer les conséquences des choix interactifs dans les histoires.

---

## Front-End

### Framework Angular :

- **Éditeur d'univers** :
    - Interface où les utilisateurs peuvent visualiser et modifier les caractéristiques d'un univers.
- **Éditeur de personnages** :
    - Création avec des formulaires dynamiques pilotés par Angular (template-driven et reactive forms).
- **Éditeur d'histoires** :
    - Interface graphique pour construire une histoire avec glisser-déposer des nœuds narratifs.

### Connexion aux APIs REST :

- Le front-end utilise les API REST exposées par les microservices pour interagir avec les données.

---

# Workflow Kafka

### Topics Kafka suggérés :

- `univers-events` : Contient les événements liés à la création ou modification des univers.
- `character-events` : Contient les événements liés aux personnages.
- `story-events` : Enregistre les décisions prises dans les histoires pour calculer leurs impacts.

### Exemple d'usage Kafka :

1. Lorsqu'un utilisateur crée un personnage, un événement est publié sur `character-events`.
2. Un service Kafka consomme cet événement et vérifie si le personnage est conforme aux règles de l'univers.
3. Si validé, une notification est publiée sur `notification-topic` pour informer l'utilisateur.

---

# Exemple de flux utilisateur

1. **Création d'un univers** :
    - L'utilisateur crée un univers "Fantasy Aventure".
   - Une API REST sur le service Univers traite la requête, la stocke en base, et publie un événement sur
     `univers-events`.

2. **Ajout d'un personnage** :
    - L'utilisateur choisit un template "Héros" et personnalise un personnage (nom, compétences).
    - Le service Personnages valide le personnage selon les règles définies dans l'univers.

3. **Construction d'une histoire** :
    - L'utilisateur utilise l'éditeur d'histoires interactives pour créer un scénario avec des nœuds narratifs.
    - Les nœuds sont stockés en MongoDB, et chaque choix peut être synchronisé via Kafka.

---

# Bonus : Fonctionnalités supplémentaires

- **Simulation interactive** :
    - Interface où les utilisateurs peuvent exécuter des histoires et voir comment les personnages interagissent avec le
      scénario.
- **Export/Import** :
    - Les univers ou personnages peuvent être exportés sous forme de fichiers JSON ou partagés entre utilisateurs.
- **API publique** :
    - Fournir des endpoints permettant à d'autres développeurs d'utiliser les univers créés.
