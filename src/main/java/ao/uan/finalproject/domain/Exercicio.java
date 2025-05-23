package ao.uan.finalproject.domain;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Exercicio {
    @Id
    @GeneratedValue
    private Long id;

    private float score;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private String[] categorias;
    private float maxTempo;

    @JoinColumn(referencedColumnName = "id", name = "avaliacao_id")
    @ManyToOne
    private Avaliacao avaliacao;

    @JoinColumn(referencedColumnName = "id", name = "professor_id")
    @ManyToOne
    private Professor professor;
    
    @JoinColumn(name = "entradasaida_id")
    @OneToMany
    private List<EntradaSaida> entradaSaida;

    private boolean ativo;
    private boolean criadoEmAvaliacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public List<EntradaSaida> getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(List<EntradaSaida> entradaSaida) {
        this.entradaSaida = entradaSaida;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
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

    public float getMaxTempo() {
        return maxTempo;
    }

    public void setMaxTempo(float maxTempo) {
        this.maxTempo = maxTempo;
    }
}
