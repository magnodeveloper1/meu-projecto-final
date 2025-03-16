package ao.uan.finalproject.domain;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import ao.uan.finalproject.handler.SolucaoEventHandler;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@EntityListeners(SolucaoEventHandler.class)
public class Solucao {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String code;
    
    @ManyToOne
    @JoinColumn
    private Utilizador utilizador;
    
    @ManyToOne
    @JoinColumn
    private Exercicio exercicio;

    private Date dataSubmissao;

    private boolean ok;

    private String linguagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Date getDataSubmissao() {
        return dataSubmissao;
    }

    public void setDataSubmissao(Date dataSubmissao) {
        this.dataSubmissao = dataSubmissao;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getLinguagem() {
        return linguagem;
    }

    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

}
