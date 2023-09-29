package br.edu.ifam.tads.dra.finalproject.controller;

import br.edu.ifam.tads.dra.finalproject.dto.TarefaImputDTO;
import br.edu.ifam.tads.dra.finalproject.dto.TarefaOutputDTO;
import br.edu.ifam.tads.dra.finalproject.model.Tarefa;
import br.edu.ifam.tads.dra.finalproject.repository.CategoriaRepository;
import br.edu.ifam.tads.dra.finalproject.repository.PrioridadeRepository;
import br.edu.ifam.tads.dra.finalproject.repository.StatusRepository;
import br.edu.ifam.tads.dra.finalproject.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @Autowired
    EntityManager entityManager;

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value ="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaOutputDTO> update(@PathVariable("id") Long id,@RequestBody TarefaImputDTO dto, UriComponentsBuilder uriBuilder){


        Tarefa tarefa = dto.build(statusRepository, categoriaRepository, prioridadeRepository);

        tarefa.setId(id);

        System.out.println(dto);

        tarefaRepository.save(tarefa);

        URI path = uriBuilder.path("/api/cidades/{id}")
                .buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.ok().body(new TarefaOutputDTO(tarefa));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TarefaOutputDTO> creat(@RequestBody TarefaImputDTO dto, UriComponentsBuilder uriBuilder){

        System.out.println(dto);

        Tarefa tarefa = dto.build(statusRepository, categoriaRepository, prioridadeRepository);

        tarefaRepository.save(tarefa);

        URI path = uriBuilder.path("/api/cidades/{id}")
                .buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(path).body(new TarefaOutputDTO(tarefa));
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
//    public String create(@RequestBody Tarefa tarefa){
//
//        tarefaRepository.save(tarefa);
//
//        return "Tarefa Salva com Sucesso!";
//    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Tarefa> list(){

        List<Tarefa> tarefas = new ArrayList<>();

        tarefas = (List<Tarefa>) tarefaRepository.findAll();

        return tarefas;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String delete(@PathVariable Long id){
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);

        if(tarefa.isPresent()){
            tarefaRepository.delete(tarefa.get());
            return "Tarefa deletada com sucesso!";
        }else{
            return "Tarefa não encontrada!";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value ="/categoria/titulo/{titulo}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Tarefa listTarefaByCategoria(@PathVariable String titulo) {

        Optional<Tarefa> tarefa = tarefaRepository.findByCategoria_Titulo(titulo);

        if (tarefa.isPresent()) {
            System.out.println(tarefa);
            return tarefa.get();
        } else {
            System.out.println(tarefa);
            System.out.println("Nada Encontrado");
            return null;
        }
    }
}
