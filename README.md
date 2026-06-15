# Sistema de Tienda de Videojuegos
Materia: Programación 1 - Análisis de Sistemas

## 👥 Integrantes del Grupo
* [Juan Alvarez] 
* [Pablo Ruiz Diaz] 

---
## 🛠️ Tecnologías y Conceptos Aplicados

### 1. Modelo de Dominio (POO y Polimorfismo)
* **Jerarquías de Herencia:** * `Persona` ➔ `Cliente` / `Vendedor`
  `Producto` (Clase Abstracta que implementa la interfaz `IVendible`) ➔ `Juego` / `Consola` / `Accesorio`.
* **Polimorfismo:** Procesamiento genérico de productos en colecciones dinámicas para llamadas a métodos comunes como `vender()` y `mostrarInfo()`.
* **Encapsulamiento:** Atributos privados y protegidos con sus respectivos métodos getters y setters.
* **Estados:** Control estricto del ciclo de vida del producto mediante el enum `EstadoProducto` (DISPONIBLE, ALQUILADO, SIN_STOCK).

### 2. Programación Funcional (Streams y Lambdas)
* Búsqueda inteligente de productos por título mediante el uso de `Stream.filter()` y `findFirst()`.
* Filtrado de productos disponibles retornando colecciones optimizadas con `collect(Collectors.toList())`.
* Despliegue del catálogo ordenado alfabéticamente por título empleando `Stream.sorted()` acoplado a expresiones Lambda.
* Uso de referencias a métodos (`::`) aplicados en iteraciones `forEach`.

### 3. Manejo de Excepciones
* Implementación de una excepción personalizada de tipo checked: `StockInsuficienteException`.
* Esta excepción es lanzada automáticamente al intentar realizar transacciones (ventas o alquileres) de productos sin unidades disponibles en inventario.
* Captura y gestión formal en el menú principal mediante bloques `try-catch-finally` para asegurar la estabilidad del sistema y guiar al usuario.

### 4. Persistencia de Datos (Serialización)
* Las clases base `Producto` y `Persona` implementan la interfaz `Serializable`.
* Encapsulación de la lógica de persistencia bajo el patrón Repository con la interfaz `IRepositorio`.
* **Guardado:** Al finalizar o actualizar, el catálogo completo se guarda en el archivo local `productos.dat` mediante `ObjectOutputStream`.
* **Carga:** Al iniciar el sistema, el programa verifica la existencia de `productos.dat`, cargando automáticamente los datos guardados a la memoria con `ObjectInputStream`.


## 📝 Descripción del Sistema
Este sistema consiste en una aplicación de consola desarrollada en Java orientada a la gestión integral de una tienda de videojuegos. Permite la administración de productos (juegos, consolas y accesorios), clientes y empleados (vendedores), controlando de manera estricta las operaciones de venta y alquiler según la disponibilidad de stock de cada artículo.

---

## 💻 Funcionalidades del Menú por Consola
El sistema cuenta con un menú interactivo controlado mediante `Scanner` que permite:
1. Agregar producto (Juego, Consola o Accesorio).
2. Registrar cliente.
3. Vender producto (Valida stock y estado).
4. Alquilar producto (Valida stock y estado).
5. Mostrar productos disponibles en tienda.
6. Mostrar catálogo completo ordenado alfabéticamente por título.

---

## 🚀 Instrucciones de Ejecución

### Requisitos Previos
* Tener instalado el Java Development Kit (JDK 8 o superior).
* Un IDE o editor de texto (Visual Studio Code, Eclipse, IntelliJ, etc.).

### Pasos para ejecutar:
1. Abrir la carpeta del proyecto en tu entorno de desarrollo (ej. Visual Studio Code).
2. Localizar el archivo principal que contiene el método `main` (por lo general `Main.java` o `Principal.java`).
3. Ejecutar el archivo principal ("Run").
4. Interactuar con la aplicación utilizando los números de opción indicados en la consola.