package control;

import java.util.Iterator;

import org.eclipse.swt.widgets.Combo;

import modelo.Turno;
import repositorio.AlunoRepositorio;
import repositorio.ProfessorRepositorio;
import view.CadastrarAlunoForm;
import view.CadastrarProfessorForm;
import view.MenuAcesso;

public class CadastrarControl {
	public static void displayAlunoCadastroView() {
		try {
			CadastrarAlunoForm window = new CadastrarAlunoForm();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void displayProfessorCadastroView() {
		try {
			CadastrarProfessorForm window = new CadastrarProfessorForm();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addTurno(Combo combo) {
		Turno turno;
		for (Turno periodo : Turno.values()) {
			combo.add(periodo.toString());
		}
	}

	public static boolean cadastrarAluno(String email, String senha, String nome, String cpf, String turno) {
		if (email.equals("") || senha.equals("") || nome.equals("") || cpf.equals("") || turno.equals("")) {
			return false;
		}
		AlunoRepositorio alunoRepositorio = new AlunoRepositorio();
		alunoRepositorio.adicionar(nome, senha, email, cpf, Turno.valueOf(turno));
		return true;
	}

	public static boolean cadastrarProfessor(String email, String senha, String nome, String cpf) {
		if (email.equals("") || senha.equals("") || nome.equals("") || cpf.equals("")) {
			return false;
		}
		ProfessorRepositorio professorRepositorio = new ProfessorRepositorio();
		professorRepositorio.adicionar(nome, senha, email, cpf);
		return true;
	}

}
