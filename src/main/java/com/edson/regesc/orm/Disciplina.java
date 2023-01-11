package com.edson.regesc.orm;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private Integer semestre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id", nullable = true)
    private Professor professor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "disciplinas_alunos",
    joinColumns = @JoinColumn(name = "disciplina_fk"),inverseJoinColumns = @JoinColumn(name = "aluno_fk"))
    List<Aluno> alunos;
 
    @Deprecated
    public Disciplina() {
    }

    public Disciplina(String nome, Integer semestre, Professor professorId) {
        this.nome = nome;
        this.semestre = semestre;
        this.professor = professorId;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public String toString() {
        return "Disciplina [id=" + id + ", nome=" + nome + ", semestre=" + semestre + ", professor=" + professor
                + ", alunos=" + alunos + "]";
    }

}
