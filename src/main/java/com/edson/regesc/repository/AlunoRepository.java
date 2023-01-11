package com.edson.regesc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.edson.regesc.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    // busca por atrinuto nome , montando uma busca simples
    // aqui que começa com ...
    public abstract List<Aluno> findByNomeStartingWith(String nome);

    // aqui que contenha no nome ...
    public abstract List<Aluno> findByNomeContaining(String nome);

     // aqui que começa com ... e idade menor que 
     public abstract List<Aluno> findByNomeStartingWithAndIdadeLessThanEqual(String nome,Integer idade);

       // aqui que começa com ... e idade maior que 
       public abstract List<Aluno> findByNomeStartingWithAndIdadeGreaterThanEqual(String nome,Integer idade);
    

    
}
