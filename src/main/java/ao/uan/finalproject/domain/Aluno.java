package ao.uan.finalproject.domain;

import ao.uan.finalproject.dto.enums.Turno;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Aluno {
    
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String refencia;

    @JoinColumn(referencedColumnName = "id", name = "turma_id")
    @ManyToOne
    private Turma turma;

    private Turno turno;

    @JoinColumn(referencedColumnName = "id", name = "departamento_id")
    @ManyToOne
    private Departamento departamento;

    @JoinColumn(referencedColumnName = "id", name = "utilizador_id")
    @ManyToOne
    private Utilizador utilizador;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefencia() {
        return refencia;
    }

    public void setRefencia(String refencia) {
        this.refencia = refencia;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    } 
}
