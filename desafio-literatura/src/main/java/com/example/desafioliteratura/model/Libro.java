package com.example.desafioliteratura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "descargas_totales")
    private Double descargasTotales;



    public Libro(){

    }

    public Libro(DatosLibro datosLibro, Autor autor){

        this.titulo = datosLibro.titulo();
        this.autor = autor;
        this.idioma = datosLibro.idioma().get(0);
        this.descargasTotales = Double.valueOf(datosLibro.descargasTotales());

    }


    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anioNacimiento=" + autor.getAnioNacimiento() +
                ", anioMuerte=" + autor.getAnioMuerte() +
                ", idioma='" + idioma + '\'' +
                ", descargasTotales=" + descargasTotales +

                '}';
    }

    private void setLibro(Libro libro) {
    }


    public Double getDescargasTotales() {
        return descargasTotales;
    }

    public void setDescargasTotales(Long descargasTotales) {
        this.descargasTotales = Double.valueOf(descargasTotales);
    }


    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }


    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = (long) Math.toIntExact(id);
    }

    public String getTitulo() {
        return titulo;
    }
}
