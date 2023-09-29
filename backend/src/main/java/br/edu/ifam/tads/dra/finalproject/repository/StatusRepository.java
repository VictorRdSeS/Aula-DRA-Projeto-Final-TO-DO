package br.edu.ifam.tads.dra.finalproject.repository;

import br.edu.ifam.tads.dra.finalproject.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatusRepository extends CrudRepository<Status, Long> {

    Optional<Status> findByTituloContaining(Status titulo);

    Optional<Status> findOneByTitulo(String titulo);
}
