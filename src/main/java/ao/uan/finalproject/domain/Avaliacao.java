package ao.uan.finalproject.domain;

import java.util.Date;
import java.util.Set;

import ao.uan.finalproject.dto.enums.TipoAvaliacao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Avaliacao {
    
    @Id
    @GeneratedValue
    private Long id;

    private TipoAvaliacao tipo;
    private String descricao;
    private Date dataDaAvaliacao;
    private Date dataDeCriacao;
    private int qtdExercicio;
    private int duracaoMinutos;
    
    @JoinColumn(name = "exercicio_id")
    @OneToMany
    private Set<Exercicio> exercicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoAvaliacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoAvaliacao tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataDaAvaliacao() {
        return dataDaAvaliacao;
    }

    public void setDataDaAvaliacao(Date dataDaAvaliacao) {
        this.dataDaAvaliacao = dataDaAvaliacao;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public int getQtdExercicio() {
        return qtdExercicio;
    }

    public void setQtdExercicio(int qtdExercicio) {
        this.qtdExercicio = qtdExercicio;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Set<Exercicio> getExercicio() {
        return exercicio;
    }

    public void setExercicio(Set<Exercicio> exercicio) {
        this.exercicio = exercicio;
    }
}
