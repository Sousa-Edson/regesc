package com.edson.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.edson.regesc.orm.Professor;
import com.edson.regesc.repository.ProfessorRepository;

@Service
public class CrudProfessorService {
    private ProfessorRepository professorRepository;

    public CrudProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;
        while (isTrue) {
            System.out.println("\nQual ação você deseja executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - cadastrar novo professor");
            System.out.println("2 - atualizar um professor");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    cadastrar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
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
        System.out.println("\nDigite o ID do professor a ser atualizado:\n");
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
}
