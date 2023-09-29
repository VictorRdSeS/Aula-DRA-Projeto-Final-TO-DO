package br.edu.ifam.tads.dra.finalproject.repository;

import br.edu.ifam.tads.dra.finalproject.model.Tarefa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TarefaRepository extends CrudRepository<Tarefa,Long> {

    Optional<Tarefa> findByTituloContaining(String titulo);

//    @Query("select Tarefa from Tarefa where categoria.titulo = ?{titulo}");
    Optional<Tarefa> findByCategoria_Titulo (String titulo);

    Optional<Tarefa> findOneByTitulo(String titulo);

}
