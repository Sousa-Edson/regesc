package com.edson.regesc.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.edson.regesc.orm.Aluno;
import com.edson.regesc.repository.AlunoRepository;

@Service
public class RelatorioService {
    private AlunoRepository alunoRepository;

    public RelatorioService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
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
        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWithAndIdadeGreaterThanEqual(nome, idade);
        alunos.forEach(System.out::println);
    }

}
