package control;

import java.util.ArrayList;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;
import repositorio.AlunoRepositorio;
import repositorio.ProfessorRepositorio;
import view.MenuAcesso;

public class MenuAcessoControl {

	public static void displayView() {
		try {
			MenuAcesso window = new MenuAcesso();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Pessoa entrar(String email, String senha) {
		AlunoRepositorio alunoRepositorio = new AlunoRepositorio();

		ArrayList<Aluno> aluno =  alunoRepositorio.autenticarAluno(email, senha);
		
		if(aluno.size() > 0) {
			return aluno.get(0);
		}
		
		ProfessorRepositorio professorRepositorio = new ProfessorRepositorio();
		
		ArrayList<Professor> professor = professorRepositorio.autenticarProfessor(email, senha);
		
		if(professor.size() > 0) {
			return professor.get(0);
		}
		return null;
		
	}
	
	
}
