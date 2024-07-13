package br.com.alura.ChallengeAlura_LiterAlura.repository;

import br.com.alura.ChallengeAlura_LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.titulo = :titulo")
    List<Livro> encontrarLivroPeloTitulo(@Param("titulo") String titulo);

    List<Livro> findByIdiomasContains(String idioma);

}
