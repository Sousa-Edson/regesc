package com.edson.regesc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edson.regesc.orm.Professor;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

    // SQL NATIVA
    @Query(nativeQuery = true, value = "SELECT * FROM professores p INNER JOIN disciplinas d ON p.id = d.professor_id WHERE p.nome like :nomeProfessor% AND d.nome like :nomeDisciplina%")
    public abstract List<Professor> findProfessorAtribuido(String nomeProfessor, String nomeDisciplina);
}
