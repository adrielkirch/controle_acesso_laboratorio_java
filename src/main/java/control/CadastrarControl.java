package control;

import java.util.Iterator;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Combo;

import modelo.Turno;
import repositorio.AlunoRepositorio;
import repositorio.LaboratorioRepositorio;
import repositorio.ProfessorRepositorio;
import view.CadastrarAlunoForm;
import view.CadastrarLaboratorioForm;
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
	
	public static void displayLaboratorioCadastroView() {
		try {
			CadastrarLaboratorioForm window = new CadastrarLaboratorioForm();
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
		try {
			alunoRepositorio.adicionar(nome, senha, email, cpf, Turno.valueOf(turno));
			return true;
		} catch (Exception e) {
			
			return false;
		}
		
	}

	public static boolean cadastrarProfessor(String email, String senha, String nome, String cpf) {
		if (email.equals("") || senha.equals("") || nome.equals("") || cpf.equals("")) {
			return false;
		}
		ProfessorRepositorio professorRepositorio = new ProfessorRepositorio();
		try {
			professorRepositorio.adicionar(nome, senha, email, cpf);
			return true;
		} catch (Exception e) {

			return false;
		}
		
	}
	public static boolean cadastrarLaboratorio(String codigo, int totalAssentos) {
		if (codigo.equals("") || totalAssentos < 0 ) {
			return false;
		}
		LaboratorioRepositorio laboratorioRepositorio = new LaboratorioRepositorio();
		laboratorioRepositorio.adicionar(codigo, totalAssentos);
		return true;
	}
	

}
