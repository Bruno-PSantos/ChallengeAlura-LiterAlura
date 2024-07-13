package br.com.alura.ChallengeAlura_LiterAlura.repository;

import br.com.alura.ChallengeAlura_LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    @Query("SELECT a FROM Autor a WHERE a.anoMorte > :ano AND a.anoNascimento <= :ano")
    List<Autor> encontrarAutoresVivosEmDeterminadoAno(String ano);

}
