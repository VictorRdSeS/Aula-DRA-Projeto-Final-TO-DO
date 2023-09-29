package br.edu.ifam.tads.dra.finalproject.repository;

import br.edu.ifam.tads.dra.finalproject.model.Prioridade;
import br.edu.ifam.tads.dra.finalproject.model.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PrioridadeRepository extends CrudRepository<Prioridade, Long>{


    Optional<Prioridade> findOneByTipo(String tipo);
}

