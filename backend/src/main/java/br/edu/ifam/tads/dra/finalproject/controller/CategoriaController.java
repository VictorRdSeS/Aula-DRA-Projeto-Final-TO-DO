package br.edu.ifam.tads.dra.finalproject.controller;

import br.edu.ifam.tads.dra.finalproject.model.Categoria;
import br.edu.ifam.tads.dra.finalproject.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestBody Categoria categoria){

        categoriaRepository.save(categoria);

        return "Categoria Salva com Sucesso!";
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Categoria> list(){

        List<Categoria> categorias = new ArrayList<>();

        categorias = (List<Categoria>) categoriaRepository.findAll();

        return categorias;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String delete(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isPresent()){
            categoriaRepository.delete(categoria.get());
            return "Categoria deletada com sucesso!";
        }else{
            return "Categoria não encontrada!";
        }
    }


}
