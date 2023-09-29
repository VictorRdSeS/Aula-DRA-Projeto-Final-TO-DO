package br.edu.ifam.tads.dra.finalproject.dto;

import br.edu.ifam.tads.dra.finalproject.model.Tarefa;

public class TarefaOutputDTO {

    private Long id;

    private String titulo;

    private String descricao;

    private String dataPrevista;

    private String dataConclusao;

    private String status;

    private String categoria;

    private String prioridade;

    private Integer duracao;

    public TarefaOutputDTO(){}

    public TarefaOutputDTO(Tarefa tarefa){
        this.id = tarefa.getId();
        this.titulo = tarefa.getTitulo();
        this.descricao = tarefa.getDescricao();
        this.dataPrevista = tarefa.getDataPrevista();
        this.dataConclusao = tarefa.getDataConclusao();
        this.status = tarefa.getStatus()!=null?tarefa.getStatus().getTitulo():"";
        this.categoria = tarefa.getCategoria()!=null?tarefa.getCategoria().getTitulo():"";
        this.prioridade = tarefa.getPrioridade()!=null?tarefa.getPrioridade().getTipo():"";
        this.duracao = tarefa.getDuracao()!=null?tarefa.getDuracao():0;

    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "TarefaOutputDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataPrevista='" + dataPrevista + '\'' +
                ", dataConclusao='" + dataConclusao + '\'' +
                ", status='" + status + '\'' +
                ", categoria='" + categoria + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", duracao=" + duracao +
                '}';
    }
}
