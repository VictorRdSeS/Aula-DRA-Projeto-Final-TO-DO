package br.edu.ifam.tads.dra.finalproject.repository;

import br.edu.ifam.tads.dra.finalproject.model.Categoria;
import br.edu.ifam.tads.dra.finalproject.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {


    Optional<Categoria> findOneByTitulo(String titulo);
}
