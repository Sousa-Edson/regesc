package com.edson.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edson.regesc.orm.Disciplina;
import com.edson.regesc.orm.Professor;
import com.edson.regesc.repository.ProfessorRepository;

@Service
public class CrudProfessorService {
    private ProfessorRepository professorRepository;

    public CrudProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Transactional
    public void menu(Scanner scanner) {
    	 
        Boolean isTrue = true;
        while (isTrue) {
            System.out.println("\nQual ação você deseja executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - cadastrar novo professor");
            System.out.println("2 - atualizar um professor");
            System.out.println("3 - visualizar todos os professores");
            System.out.println("4 - deletar um professor");
            System.out.println("5 - visualizar detalhe de um professor");

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
                    visualizarProfessor(scanner);
                    break;
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Digite o nome do professor :");
        String nome = scanner.next();
        System.out.print("Digite o prontuário :");
        String prontuario = scanner.next();
        Professor professor = new Professor(nome, prontuario);
        this.professorRepository.save(professor);
        System.out.println("\nProfessor salvo no Banco!!\n");
    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID do professor a ser atualizado:");
        Long id = scanner.nextLong();
        Optional<Professor> optional = this.professorRepository.findById(id);
        if (optional.isPresent()) {
            System.out.print("Digite o nome do professor :");
            String nome = scanner.next();
            System.out.print("Digite o prontuário :");
            String prontuario = scanner.next();

            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProntuario(prontuario);
            professorRepository.save(professor);

            System.out.println("\nProfessor atualizado no Banco!!\n");

        } else {
            System.out.println("O ID do professor informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        System.out.println();
        Iterable<Professor> professores = this.professorRepository.findAll();
        for (Professor professor : professores) {
            System.out.println(professor.toString());
        }
        // professores.forEach(professor ->{
        // System.out.println(professor.toString());
        // });
        // professores.forEach(System.out::println);
        System.out.println();
    }

    private void deletar(Scanner scanner) {
        System.out.print("Digite o ID do professor :");
        Long id = scanner.nextLong();
        this.professorRepository.deleteById(id);
        System.out.println("\nProfessor deletado\n");
    }

    @Transactional
    private void visualizarProfessor(Scanner scanner) {
        System.out.println();

        System.out.print("Digite o ID do professor :");
        Long id = scanner.nextLong();
        Optional<Professor> optional = this.professorRepository.findById(id);
        System.out.println();
        if (optional.isPresent()) {
            System.out.println("Professor : {");
            Professor professor = optional.get();
            System.out.println("ID: " + professor.getId());
            System.out.println("NOME: " + professor.getNome());
            System.out.println("PRONTUARIO: " + professor.getProntuario());
            // System.out.println("PRONTUARIO: " + professor.getDisciplinas());
            System.out.println("DISCIPLINAS [ ");
            for (Disciplina disciplina : professor.getDisciplinas()) {
                System.out.println();
                System.out.println("\tId: " + disciplina.getId());
                System.out.println("\tNome: " + disciplina.getNome());
                System.out.println("\tSemestre: " + disciplina.getSemestre());
            }
            System.out.println("]\n}");
        }

        System.out.println();
    }

}
