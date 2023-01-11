package com.edson.regesc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.edson.regesc.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
  // busca por atrinuto nome , montando uma busca simples
  // aqui que começa com ...
  public abstract List<Aluno> findByNomeStartingWith(String nome);

  // aqui que contenha no nome ...
  public abstract List<Aluno> findByNomeContaining(String nome);

  // aqui que começa com ... e idade menor que
  public abstract List<Aluno> findByNomeStartingWithAndIdadeLessThanEqual(String nome, Integer idade);

  // aqui que começa com ... e idade maior que
  public abstract List<Aluno> findByNomeStartingWithAndIdadeGreaterThanEqual(String nome, Integer idade);

  /* AQUI COM JPQL E NATIVE SQL */

  // JPQL
  @Query("SELECT a FROM Aluno a WHERE a.nome like :nome% AND a.idade >= :idade")
  public abstract List<Aluno> findNomeIgualOuMaior(String nome, Integer idade);

  // JPQL
  @Query("SELECT a FROM Aluno a  INNER JOIN a.disciplinas d WHERE a.nome like :nomeAluno% AND a.idade >= :idadeAluno AND d.nome like :nomeDisciplina% ")
  public abstract List<Aluno> findNomeIgualOuMaiorMatricula(String nomeAluno, Integer idadeAluno,
      String nomeDisciplina);

}
