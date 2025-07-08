# desafio-literatura
Esta aplicación es un proyecto Java con Spring Boot que consume la API pública de Gutendex, un catálogo de libros del Proyecto Gutenberg. Permite buscar libros por título, registrar autores y libros en una base de datos relacional, y realizar consultas personalizadas como:

✨ Características

✅ Buscar libros por título

📖 Listar libros registrados en base de datos

🧑‍🎨 Listar autores registrados

⏳ Filtrar autores vivos en determinado año

🌐 Listar libros por idioma

🔧 Tecnologías utilizadas

Java 17

Spring Boot

Spring Data JPA

H2 (modo desarrollo) o MySQL/PostgreSQL (producción)

API REST: Gutendex

Jackson (para parseo JSON)

Maven

▶️ Cómo ejecutar

Clona el repositorio:

git clone https://github.com/tu-usuario/desafio-literatura.git

Ejecuta el proyecto desde tu IDE o usando Maven:

./mvnw spring-boot:run

Interactúa desde consola: se abrirá un menú interactivo donde podrás realizar las operaciones.

📃 Base de datos

Se utiliza Hibernate con Spring Data JPA para mapear entidades Libro y Autor.

Puedes usar H2 en memoria para pruebas o configurar tu propia base de datos en application.properties.

📚 Ejemplo de uso

1- Buscar libros por título
2- Listar libros registrados
3- Listar autores registrados
4- Listar autores vivos en determinado año
5- Listar libros por idiomas
0- Salir

📕 Aprendizajes

Consumo de APIs REST con HttpClient de Java

Transformación de JSON a objetos con Jackson

Integración de Spring Boot con base de datos

Uso de JPA y relaciones @OneToMany / @ManyToOne
