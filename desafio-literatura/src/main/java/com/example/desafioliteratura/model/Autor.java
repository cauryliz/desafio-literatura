package com.example.desafioliteratura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "autor")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "nombre")
    private  String nombre;
    @Column(name = "anio_nacimiento")
    private String anioNacimiento;
    @Column(name = "anio_muerte")
    private String anioMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   private List<Libro> libros;

    public Autor(){}

    public Autor(String nombre){
        this.nombre = nombre;
    }

    public Autor(DatosAutor a){
        this.nombre = a.nombre();
        this.anioNacimiento = a.anioNacimiento();
        this.anioMuerte = a.anioMuerte();
        System.out.println("Consructor usado: " + this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnioNacimiento() {
        return anioNacimiento;
    }

    public void setAnioNacimiento(String anioNacimiento) {
        this.anioNacimiento = anioNacimiento;
    }

    public String getAnioMuerte() {
        return anioMuerte;
    }

    public void setAnioMuerte(String anioMuerte) {
        this.anioMuerte = anioMuerte;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;

    }
    @Override
    public String toString() {
        return "Autor{" +
                "id" + id +
                "nombre='" + nombre + '\'' +
                ", anioNacimiento='" + anioNacimiento + '\'' +
                ", anioMuerte='" + anioMuerte + '\'' +
                ", libros=" + libros +
                '}';
    }

}
