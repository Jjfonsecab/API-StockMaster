# API-StockMaster
The StockMaster API was developed using Java, MySQL, and Spring Boot 3 with the following dependencies: spring-boot-starter-data-jdbc, spring-boot-starter-web, spring-boot-devtools, mysql-connector-j, lombok, spring-boot-starter-test.

As part of its structure, the following classes are found:

The ControllerProducto class acts as the intermediary between the GUI and product data in an inventory management system, allowing the addition, updating, deletion of products, and generating reports about stored data.

The Producto class has the following attributes:
1.codigo: Represents the unique product code and is mapped to the "codigo" column in the database table.
2.nombre: Represents the product name and is mapped to the "producto" column in the database table.
3.precio: Represents the product price and is mapped to the "precio" column in the database table.
4.inventario: Represents the product inventory quantity and is mapped to the "cantidad" column in the database table.
5.proveedor: Represents the product supplier and is mapped to the "proveedor" column in the database table. This class encapsulates the relevant information of a product in the inventory management system and provides methods to create instances with different data sets.

The RepositorioProducto interface provides a high-level abstraction for performing CRUD operations in the database for the Producto entity, thanks to the functionality provided by Spring Data and its integration with JPA (Java Persistence API).

The Principal class defines and configures the graphical user interface to interact with an inventory management system. It provides text fields, buttons, and a table for entering, displaying, and manipulating product data.

The ApiApplication class is the main class of the application that configures and runs the inventory management application. It handles Spring Boot configuration, dependency injection, and the creation of the graphical interface and controller for user interaction and data manipulation.
