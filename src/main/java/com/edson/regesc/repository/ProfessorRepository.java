package com.edson.regesc.repository;

import org.springframework.data.repository.CrudRepository;

import com.edson.regesc.orm.Professor;

public interface ProfessorRepository  extends CrudRepository<Professor,Long>{
    
}
