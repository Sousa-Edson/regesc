package com.edson.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.edson.regesc.orm.Disciplina;
import com.edson.regesc.orm.Professor;
import com.edson.regesc.repository.DisciplinaRepository;
import com.edson.regesc.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {
    private DisciplinaRepository disciplinaRepository;
    private ProfessorRepository professorRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.professorRepository = professorRepository;
    }

    public void menu(Scanner scanner) {
        Boolean isTrue = true;
        while (isTrue) {
            System.out.println("\nQual ação você deseja executar?");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.println("1 - cadastrar nova disciplina");
            System.out.println("2 - atualizar uma disciplina");
            System.out.println("3 - visualizar todas as disciplina");
            System.out.println("4 - deletar um adisciplina");
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
                default:
                    isTrue = false;
                    break;
            }
        }
        System.out.println();
    }

    private void cadastrar(Scanner scanner) {
        System.out.print("Nome da nova disciplina :");
        String nome = scanner.next();
        System.out.print("Semestre :");
        int semestre = scanner.nextInt();
        System.out.print("Professor ID :");
        Long professorId = scanner.nextLong();
        Optional<Professor> optional = professorRepository.findById(professorId);
        if (optional.isPresent()) {
            Professor professor = optional.get();
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            this.disciplinaRepository.save(disciplina);
            System.out.println("\nDisciplina salva no Banco!!\n");
        } else {
            System.out.println("O ID do professor informado: " + professorId + " é inválido\n");
        }

    }

    private void atualizar(Scanner scanner) {
        System.out.print("Digite o ID da disciplina a ser atualizado:");
        Long id = scanner.nextLong();
        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);
        if (optionalDisciplina.isPresent()) {
            Disciplina disciplina = optionalDisciplina.get();
            System.out.print("Nome da nova disciplina :");
            String nome = scanner.next();
            System.out.print("Semestre :");
            int semestre = scanner.nextInt();
            System.out.print("Professor ID :");
            Long professorId = scanner.nextLong();
            Optional<Professor> optionalProfessor = professorRepository.findById(professorId);
            if (optionalProfessor.isPresent()) {
                Professor professor = optionalProfessor.get();
                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);
                this.disciplinaRepository.save(disciplina);

                System.out.println("\nAtualizado!!\n");
            } else {
                System.out.println("O ID do professor informado: " + professorId + " é inválido\n");
            }

        } else {
            System.out.println("O ID da disciplina informado: " + id + " é inválido\n");
        }
    }

    private void visualizar() {
        System.out.println();
        Iterable<Disciplina> disciplina = this.disciplinaRepository.findAll();
        for (Disciplina disciplinas : disciplina) {
            System.out.println(disciplinas.toString());
        }
        System.out.println();
    }

    private void deletar(Scanner scanner) {
        System.out.print("Digite o ID da disciplina a ser deletado:");
        Long id = scanner.nextLong();
        this.disciplinaRepository.deleteById(id);
        System.out.println("\nDeletado\n");
    }
}
