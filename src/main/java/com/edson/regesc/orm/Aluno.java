package com.edson.regesc.orm;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private Integer idade;

    @ManyToMany(mappedBy = "alunos", fetch = FetchType.EAGER) // aqui era para ser LAZY mas tava dando erro
    private Set<Disciplina> disciplinas;

    public Aluno() {
    }

    public Aluno(Long id, String nome, Integer idade, Set<Disciplina> disciplinas) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.disciplinas = disciplinas;
    }

    // OBSERVAÇÃO => SET É UM CONJUNTO

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", idade=" + idade + "]";
    }

    public Set<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(Set<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
