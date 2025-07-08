package com.example.desafioliteratura.principal;

import com.example.desafioliteratura.model.Datos;
import com.example.desafioliteratura.model.DatosAutor;
import com.example.desafioliteratura.model.Autor;
import com.example.desafioliteratura.model.Libro;
import com.example.desafioliteratura.repository.AutorRepository;
import com.example.desafioliteratura.service.ConsumoAPI;
import com.example.desafioliteratura.model.DatosLibro;
import com.example.desafioliteratura.repository.LibroRepository;
import com.example.desafioliteratura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    @Autowired
    private final LibroRepository libroRepositorio;
    @Autowired
    private final AutorRepository autorRepositorio;

    // private LibroService libroService = new LibroService();
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();//
    private final String URL_BASE = "https://gutendex.com/books";
    private Scanner teclado = new Scanner(System.in);
    private String json;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepositorio = libroRepository;
        this.autorRepositorio = autorRepository;
    }


    public void muestraElMenu() throws IOException, InterruptedException {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1- Buscar libros por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autortes vivos en determinado año
                    5- Listar libros por idiomas                
                    
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroWeb();
                    break;
                case 2:
                    listarLibrosConDatosAutor();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                     listarLibrosPorIdiomas();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion ...");
                    break;
                default:
                    System.out.println("Opcion no valida");

            }
        }
    }

    private void listarLibrosPorIdiomas() {
        System.out.println("""
        Ingresa el idioma que deseas buscar: 
        en - Inglés  
        es - Español
        fr - Francés 
        pt - Portugués
        de - Aleman
        """);
        String idiomaBuscado = teclado.nextLine().trim().toLowerCase();

        List<Libro> libros = libroRepositorio.findAll();
        List<Libro> librosFiltrados = libros.stream()
                .filter(l -> idiomaBuscado.equalsIgnoreCase(l.getIdioma()))
                .toList();
        if (librosFiltrados.isEmpty()){
            System.out.println("No se encontraron libros en el idioma " + idiomaBuscado);
            return;
        }
        System.out.println("Libros en idioma: " + idiomaBuscado);
        for (Libro libro : librosFiltrados) {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("--------");
    }
    }

    private void listarAutoresVivosEnAnio() {
        System.out.println("Ingresa el año que deseas consultar: ");
int anioConsulta = teclado.nextInt();
teclado.nextLine();

List<Autor> autores = autorRepositorio.findAll();

for (Autor autor : autores){
    try{
        int nacimiento = Integer.parseInt(autor.getAnioNacimiento());
        String muerteStr = autor.getAnioMuerte();
        int muerte = (muerteStr == null || muerteStr.isEmpty()) ? Integer.MAX_VALUE : Integer.parseInt(muerteStr);
   if (nacimiento <= anioConsulta && muerte >= anioConsulta){
       System.out.println("Nombre: " + autor.getNombre());
       System.out.println("Año Nacimiento: " + autor.getAnioNacimiento());
       System.out.println("Año Muerte: " + autor.getAnioMuerte());
       System.out.println("--------");
   }
    }catch (NumberFormatException e){
        System.out.println("Datos no válidos para el autor: " + autor.getNombre());
    }
}
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepositorio.findAll();

        for(Autor autor : autores){
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Año Nacimiento: " + autor.getAnioNacimiento());
            System.out.println("Año Muerte: " + autor.getAnioMuerte());
            System.out.println("Libros: ");
            if (autor.getLibros() != null && !autor.getLibros().isEmpty()) {
                for (Libro libro : autor.getLibros()) {
                    System.out.println(" - " + libro.getTitulo());
                }
            } else {
                System.out.println(" No tiene libros registrados.");
            }

            System.out.println("--------");
        }
    }

    public void listarLibrosConDatosAutor() {
        List<Libro> libros = libroRepositorio.findAll();

        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Año Nacimiento: " + libro.getAutor().getAnioNacimiento());
            System.out.println("Año Muerte: " + libro.getAutor().getAnioMuerte());
            System.out.println("--------");
        }
    }


private DatosLibro getDatosLibro() throws IOException, InterruptedException {
    System.out.println("Escribe el nombre del libro que deseas buscar: ");
    var nombreLibro = teclado.nextLine();
    var json = consumoApi.obtenerDatos(URL_BASE+ "?search=" + nombreLibro.replace(" ", "+"));
    Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);
    Optional<DatosLibro> libroBuscado = datosBusqueda.resultados()
            .stream()
            .filter(libro -> libro.titulo().toUpperCase()
                    .contains(nombreLibro.toUpperCase()))
            .findFirst();
    if (libroBuscado.isPresent()) {
        return libroBuscado.get();
    } else {
        return null;
    }

}
    private void buscarLibroWeb() throws IOException, InterruptedException {
        DatosLibro datosLibro = getDatosLibro();
        if (datosLibro != null) {
            Libro libro;
            DatosAutor datosAutor = datosLibro.autor().get(0);
            Autor nuevoAutor = new Autor(datosAutor);
            System.out.println(nuevoAutor);
            Optional<Autor> autorOptional = autorRepositorio.findByNombre(datosAutor.nombre());
            Autor autorExistente = autorOptional.orElse(null);
            if (autorExistente != null) {
                libro = new Libro(datosLibro, autorExistente);
            } else {
                autorRepositorio.save(nuevoAutor);
                libro = new Libro(datosLibro, nuevoAutor);

            }

            try {
                libroRepositorio.save(libro);
                System.out.println(libro);
            } catch (Exception e) {
                System.out.println("No puedes registrar el mismo libro más de una vez");
            }

        } else {
            System.out.println("Libro no encontrado en la API");
        }
    }



}





