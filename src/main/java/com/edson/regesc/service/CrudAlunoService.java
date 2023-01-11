package com.edson.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.edson.regesc.orm.Aluno;
import com.edson.regesc.orm.Disciplina;
import com.edson.regesc.repository.AlunoRepository;
import com.edson.regesc.repository.DisciplinaRepository;

import jakarta.transaction.Transactional;

@Service
public class CrudAlunoService {
    private AlunoRepository alunoRepository;
    private DisciplinaRepository disciplinaRepository;

    public CrudAlunoService(AlunoRepository alunoRepository , DisciplinaRepository disciplinaRepository) {
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;
        while (isTrue) {
            System.out.println("\nQual ação você deseja executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - cadastrar nova aluno");
            System.out.println("2 - atualizar uma aluno");
            System.out.println("3 - visualizar todas as aluno");
            System.out.println("4 - deletar um aluno");
            System.out.println("5 - visualizar um aluno");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    deletar(scanner);
                    break;
                case 5:
                    visualizarAluno(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.next();
        System.out.print("Idade: ");
        Integer idade = scanner.nextInt();
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        this.alunoRepository.save(aluno);
        System.out.println("\nSalvo!!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID do aluno a ser atualizado: ");
        Long id = scanner.nextLong();
        Optional<Aluno> optional = this.alunoRepository.findById(id);
        if (optional.isPresent()) {
            System.out.print("Nome: ");
            String nome = scanner.next();
            System.out.print("Idade: ");
            Integer idade = scanner.nextInt();
            Aluno aluno = optional.get();
            aluno.setNome(nome);
            aluno.setIdade(idade);
            this.alunoRepository.save(aluno);
            System.out.println("\n Aluno atualizado!!\n");
        } else {
            System.out.println("O ID do aluno informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        System.out.println();
        Iterable<Aluno> alunos = this.alunoRepository.findAll();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
        System.out.println();
    }

    private void deletar(Scanner scanner) {
        System.out.print("ID: ");
        Long id = scanner.nextLong();
        this.alunoRepository.deleteById(id);
        System.out.println("\n Aluno deletado\n");
    }

    @Transactional
    private void visualizarAluno(Scanner scanner) {
        System.out.println();

        System.out.print("ID: ");
        Long id = scanner.nextLong();
        System.out.println("O ID [E => "+id);
        Optional<Aluno> optional = this.alunoRepository.findById(id);
        System.out.println();
        if (optional.isPresent()) {
            Aluno aluno = optional.get();
            System.out.println("ID: " + aluno.getId());
            System.out.println("NOME: " + aluno.getNome());
            System.out.println("IDADE: " + aluno.getIdade());
            // System.out.println("PRONTUARIO: " + professor.getDisciplinas());
            System.out.println("DISCIPLINAS [ ");
            if (aluno.getDisciplinas() != null) {
                try {
                    for (Disciplina disciplina : aluno.getDisciplinas()) {
                        System.out.println();
                        System.out.println("\tId: " + disciplina.getId());
                        System.out.println("\tNome: " + disciplina.getNome());
                        System.out.println("\tSemestre: " + disciplina.getSemestre());
                    }
                } catch (Exception e) {
                   System.out.println("\n\n########################### "+e+"\n\n\n######################");
                }
                
                System.out.println("]\n");
            }
        } else {
            System.out.println("O ID do aluno informado: " + id + " é inválido\n");
        }

        System.out.println();
    }

}
