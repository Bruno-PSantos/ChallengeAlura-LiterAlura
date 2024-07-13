package br.com.alura.ChallengeAlura_LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autor;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    @Column(name = "idiomas")
    private List<String> idiomas = new ArrayList<>();
    private Integer numeroDownlaod;

    public Livro() {}

    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.title();
        this.idiomas = dadosLivro.languages();
        this.numeroDownlaod = dadosLivro.downloadCount();
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }
    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDownlaod() {
        return numeroDownlaod;
    }
    public void setNumeroDownlaod(Integer numeroDownlaod) {
        this.numeroDownlaod = numeroDownlaod;
    }


    @Override
    public String toString() {
        return "\n-----+*+-----\nTitulo: " + titulo +
                "\nAutor: " + autor.getNome() +
                "\nIdioma: " + idiomas +
                "\nNúmero de downloads: " + numeroDownlaod +
                "\n-----+*+-----";
    }
}
