package com.edson.regesc;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edson.regesc.service.CrudAlunoService;
import com.edson.regesc.service.CrudDisciplinaService;
import com.edson.regesc.service.CrudProfessorService;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner {
	private CrudProfessorService professorService;
	private CrudDisciplinaService disciplinaService;
	private CrudAlunoService alunoService;

	public RegescApplication(CrudProfessorService professorService, CrudDisciplinaService disciplinaService,
			CrudAlunoService alunoService) {
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
		this.alunoService = alunoService;
	};

	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scanner = new Scanner(System.in);
		while (isTrue) {
			System.out.println("Qual entidade você deseja interagir?");
			System.out.println("0 - sair");
			System.out.println("1 - professor");
			System.out.println("2 - disciplina");
			System.out.println("3 - aluno");
			int opcao = scanner.nextInt();
			switch (opcao) {
				case 1:
					professorService.menu(scanner);
					break;
				case 2:
					disciplinaService.menu(scanner);
					break;
				case 3:
					alunoService.menu(scanner);
					break;
				default:
					isTrue = false;
					break;
			}
		}
	}

}
