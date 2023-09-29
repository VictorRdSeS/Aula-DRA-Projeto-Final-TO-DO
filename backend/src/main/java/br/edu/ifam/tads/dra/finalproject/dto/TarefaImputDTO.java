package br.edu.ifam.tads.dra.finalproject.dto;

import br.edu.ifam.tads.dra.finalproject.model.Categoria;
import br.edu.ifam.tads.dra.finalproject.model.Prioridade;
import br.edu.ifam.tads.dra.finalproject.model.Status;
import br.edu.ifam.tads.dra.finalproject.model.Tarefa;
import br.edu.ifam.tads.dra.finalproject.repository.CategoriaRepository;
import br.edu.ifam.tads.dra.finalproject.repository.PrioridadeRepository;
import br.edu.ifam.tads.dra.finalproject.repository.StatusRepository;

import java.util.Optional;

public class TarefaImputDTO {

    private String titulo;

    private String descricao;
    private String dataPrevista;

    private String dataConclusao;

    private Status status;

    private Categoria categoria;

    private Prioridade prioridade;

    private Integer duracao;

    public TarefaImputDTO(){

    }

    public TarefaImputDTO(String titulo, String descricao, Status status, Categoria categoria, String dataPrevista, String dataConclusao, Prioridade prioridade, Integer duracao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataPrevista = dataPrevista;
        this.dataConclusao = dataConclusao;
        this.status = status;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.duracao = duracao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataPrevista() {
        return dataPrevista;
    }

    public void setDataPrevista(String dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Tarefa build(StatusRepository statusRepository, CategoriaRepository categoriaRepository, PrioridadeRepository prioridadeRepository){

        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(this.getTitulo());

        tarefa.setDescricao((this.getDescricao()));

        tarefa.setDataPrevista((this.getDataPrevista()));

        tarefa.setDataConclusao((this.getDataConclusao()));

        tarefa.setDuracao((this.getDuracao()));

        Optional<Status> optionalStatus = statusRepository.findOneByTitulo(this.getStatus().getTitulo());


        if(optionalStatus.isPresent()){
            tarefa.setStatus(optionalStatus.get());
        }

        Optional<Categoria> optionalCategoria = categoriaRepository.findOneByTitulo(this.getCategoria().getTitulo());

        if(optionalCategoria.isPresent()){
            tarefa.setCategoria(optionalCategoria.get());
        }
       Optional<Prioridade> optionalPrioridade = prioridadeRepository.findOneByTipo(this.getPrioridade().getTipo());

        if(optionalPrioridade.isPresent()) {
           tarefa.setPrioridade(optionalPrioridade.get());
        }
        return tarefa;
    }


}
