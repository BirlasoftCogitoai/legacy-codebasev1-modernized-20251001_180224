```markdown
# Legacy EGP Application

A legacy Java EE (J2EE) enterprise application built with EJB 3.x, JSP/Servlets, and JPA. This multi-module Maven project represents a typical enterprise government portal (EGP) system from the mid-2000s era.

## Architecture

- **egp-core-ejb**: Core business logic with EJB 3.x stateless session beans, JPA entities, and DAOs
- **egp-portal-war**: Web presentation layer with JSP pages and servlets
- **egp-ear**: Enterprise Archive packaging both WAR and EJB JAR modules

## Modernization Strategy

To modernize this legacy application, we will:
1. Migrate from EJB 3.x to Spring Boot for business logic.
2. Replace JSP/Servlets with a modern web framework (e.g., Thymeleaf or Angular).
3. Use Spring Data JPA for data access.
4. Containerize the application using Docker.
5. Implement continuous integration and continuous deployment (CI/CD) pipelines.

## Build and Run

To build and run the modernized application, follow these steps:

1. Clone the repository:
    ```sh
    git clone https://github.com/BirlasoftCogitoai/legacy-codebasev1.git
    cd legacy-codebasev1
    ```

2. Build the application using Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    java -jar target/legacy-egp-app.jar
    ```

## Contributing

We welcome contributions! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for details.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
```