package com.edson.regesc.repository;

import org.springframework.data.repository.CrudRepository;

import com.edson.regesc.orm.Aluno;

public interface AlunoRepository extends CrudRepository <Aluno, Long>{
    
}
