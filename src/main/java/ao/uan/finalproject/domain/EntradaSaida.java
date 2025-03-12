package ao.uan.finalproject.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class EntradaSaida {
    
    @Id
    @GeneratedValue
    private Long id;

    private String entrada;
    private String saida;

    private String entrada1;
    private String saida1;

    private String entrada2;
    private String saida2;

    private String entrada3;
    private String saida3;

    private String entrada4;
    private String saida4;

    @JoinColumn(referencedColumnName = "id", name = "professor_id")
    @ManyToOne
    private Exercicio exercicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public String getEntrada1() {
        return entrada1;
    }

    public void setEntrada1(String entrada1) {
        this.entrada1 = entrada1;
    }

    public String getSaida1() {
        return saida1;
    }

    public void setSaida1(String saida1) {
        this.saida1 = saida1;
    }

    public String getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(String entrada2) {
        this.entrada2 = entrada2;
    }

    public String getSaida2() {
        return saida2;
    }

    public void setSaida2(String saida2) {
        this.saida2 = saida2;
    }

    public String getEntrada3() {
        return entrada3;
    }

    public void setEntrada3(String entrada3) {
        this.entrada3 = entrada3;
    }

    public String getSaida3() {
        return saida3;
    }

    public void setSaida3(String saida3) {
        this.saida3 = saida3;
    }

    public String getEntrada4() {
        return entrada4;
    }

    public void setEntrada4(String entrada4) {
        this.entrada4 = entrada4;
    }

    public String getSaida4() {
        return saida4;
    }

    public void setSaida4(String saida4) {
        this.saida4 = saida4;
    }
}
