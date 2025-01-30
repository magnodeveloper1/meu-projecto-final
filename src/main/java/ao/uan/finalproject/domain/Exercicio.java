package ao.uan.finalproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Exercicio {
    @Id
    @GeneratedValue
    private Long id;

    private String sequencia;
    private String titulo;
    private String descricao;

    @JoinColumn(referencedColumnName = "id", name = "avaliacao_id")
    @ManyToOne
    private Avaliacao avaliacao;

    private Professor processorCriou;
    private boolean ativo;
    private boolean criadoEmAvaliacao;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSequencia() {
        return sequencia;
    }
    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
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
    public Avaliacao getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }
    public Professor getProcessorCriou() {
        return processorCriou;
    }
    public void setProcessorCriou(Professor processorCriou) {
        this.processorCriou = processorCriou;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    public boolean isCriadoEmAvaliacao() {
        return criadoEmAvaliacao;
    }
    public void setCriadoEmAvaliacao(boolean criadoEmAvaliacao) {
        this.criadoEmAvaliacao = criadoEmAvaliacao;
    }
}
