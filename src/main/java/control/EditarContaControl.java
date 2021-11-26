package control;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;
import modelo.Turno;
import repositorio.AlunoRepositorio;
import repositorio.ProfessorRepositorio;
import view.AcessoLaboratorioView;
import view.EditarContaForm;

public class EditarContaControl {
	public static void displayView(Pessoa pessoa) {
		try {
			EditarContaForm window = new EditarContaForm();
			window.setPessoa(pessoa);
			window.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void editarPessoa (Pessoa p,String nome,String senha, String email, Turno turno) {
		if(p instanceof Aluno) {
			AlunoRepositorio alunoRepositorio = new AlunoRepositorio();
			Aluno aluno = (Aluno)p;
		
			alunoRepositorio.atualizar(aluno.getId(), nome,senha, email, aluno.getCpf(), turno);
			return;
		} 
			ProfessorRepositorio professorRepositorio = new ProfessorRepositorio();
			Professor professor = (Professor) p;
			professorRepositorio.atualizar(professor.getId(), senha, nome, email, professor.getCpf());
		
	}

}
