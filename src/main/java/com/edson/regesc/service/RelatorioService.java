package com.edson.regesc.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.edson.regesc.orm.Aluno;
import com.edson.regesc.orm.Professor;
import com.edson.regesc.repository.AlunoRepository;
import com.edson.regesc.repository.ProfessorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RelatorioService {
    private AlunoRepository alunoRepository;
    private ProfessorRepository professorRepository;

    public RelatorioService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;
        while (isTrue) {
            System.out.println("\nQual relatorio você deseja ?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - aluno por nome");
            System.out.println("2 - aluno que contenha no nome");
            System.out.println("3 - aluno que contenha no nome e idade menor ou igual");
            System.out.println("4 - aluno que contenha no nome e idade maior ou igual");
            System.out.println("5 - aluno que contenha no nome e idade maior ou igual Matricula");
            System.out.println("6 - professores atribuidos");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    this.alunoPorNome(scanner);
                    break;
                case 2:
                    this.alunoQueContenhaNome(scanner);
                    break;
                case 3:
                    this.alunoQueContenhaNomeEIdadeMenorQue(scanner);
                    break;
                case 4:
                    this.alunoQueContenhaNomeEIdadeMaiorQue(scanner);
                    break;
                case 5:
                    this.alunoQueContenhaNomeEIdadeMaiorQueMatricula(scanner);
                    break;
                case 6:
                    this.professoresAtribuidos(scanner);
                    break;

                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void alunoPorNome(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();
        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWith(nome);
        alunos.forEach(System.out::println);
    }

    private void alunoQueContenhaNome(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();
        List<Aluno> alunos = this.alunoRepository.findByNomeContaining(nome);
        alunos.forEach(System.out::println);
    }

    private void alunoQueContenhaNomeEIdadeMenorQue(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Idade: ");
        Integer idade = scanner.nextInt();
        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWithAndIdadeLessThanEqual(nome, idade);
        alunos.forEach(System.out::println);
    }

    private void alunoQueContenhaNomeEIdadeMaiorQue(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Idade: ");
        Integer idade = scanner.nextInt();
        List<Aluno> alunos = this.alunoRepository.findNomeIgualOuMaior(nome, idade);
        alunos.forEach(System.out::println);
    }

    private void alunoQueContenhaNomeEIdadeMaiorQueMatricula(Scanner scanner) {
        System.out.print("Nome: ");
        String nomeAluno = scanner.next();
        System.out.print("Idade: ");
        Integer idadeAluno = scanner.nextInt();
        System.out.print("Nome disciplina: ");
        String nomeDisciplina = scanner.next();
        List<Aluno> alunos = this.alunoRepository.findNomeIgualOuMaiorMatricula(nomeAluno, idadeAluno, nomeDisciplina);
        alunos.forEach(System.out::println);
    }

    private void professoresAtribuidos(Scanner scanner) {
        System.out.print("Nome professor: ");
        String nomeProfessor = scanner.next();

        System.out.print("Nome disciplina: ");
        String nomeDisciplina = scanner.next();

        List<Professor> professor = this.professorRepository.findProfessorAtribuido(nomeProfessor, nomeDisciplina);
        professor.forEach(System.out::println);

    }

}
