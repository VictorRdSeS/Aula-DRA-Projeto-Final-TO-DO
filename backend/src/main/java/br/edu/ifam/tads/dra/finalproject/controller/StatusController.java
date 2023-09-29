package br.edu.ifam.tads.dra.finalproject.controller;
import br.edu.ifam.tads.dra.finalproject.model.Status;
import br.edu.ifam.tads.dra.finalproject.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusRepository statusRepository;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String creat(@RequestBody Status status){

       statusRepository.save(status);

        return "Status Salvo com Sucesso!";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Status> list(){

        List<Status> status = (List<Status>) statusRepository.findAll();

        return status;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value ="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String delete(@PathVariable Long id){
        Optional<Status> status = statusRepository.findById(id);

        if(status.isPresent()){
            statusRepository.delete(status.get());
            return "Status deletado com Sucesso!";
        }else {
            return "Status não encontrado!";
        }
    }
}
