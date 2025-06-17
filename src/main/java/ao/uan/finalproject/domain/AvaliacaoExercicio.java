package ao.uan.finalproject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "avaliacao_exercicio")
public class AvaliacaoExercicio {

    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(referencedColumnName = "id", name = "avaliacao_id")
    @ManyToOne
    private Avaliacao avaliacao;

    @JoinColumn(referencedColumnName = "id", name = "exercicio_id")
    @ManyToOne
    private Exercicio exercicio;

    @Column
    private Float valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Exercicio getExercicio() {
        return exercicio;
    }

    public void setExercicio(Exercicio exercicio) {
        this.exercicio = exercicio;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}

