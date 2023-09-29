package br.edu.ifam.tads.dra.finalproject.controller;

import br.edu.ifam.tads.dra.finalproject.model.Prioridade;
import br.edu.ifam.tads.dra.finalproject.repository.PrioridadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/prioridade")
public class PrioridadeController {

    @Autowired
    private PrioridadeRepository prioridadeRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String creat(@RequestBody Prioridade prioridade){

        prioridadeRepository.save(prioridade);

        return "Prioridade salva com sucesso!";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Prioridade> list(){

        List<Prioridade> prioridade = (List<Prioridade>) prioridadeRepository.findAll();

        return prioridade;
    }


}
