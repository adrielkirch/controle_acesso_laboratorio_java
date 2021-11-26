package control;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.swt.widgets.Label;

import modelo.Aluno;
import modelo.Laboratorio;
import modelo.Pessoa;
import modelo.Professor;
import modelo.Registro;
import repositorio.LaboratorioRepositorio;
import repositorio.RegistroRepositorio;
import util.PessoaUtil;
import view.AcessoLaboratorioView;
import org.eclipse.swt.widgets.Group;
public class AcessoLaboratorioControl {
	
	public static void displayView(Pessoa pessoa) {
		try {
			AcessoLaboratorioView window = new AcessoLaboratorioView();
			window.setPessoa(pessoa);
			boolean sessaoAtiva = isSessaoAtiva(pessoa);
			window.setSessaoAtiva(sessaoAtiva);
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void carregarTabela(Table table) {
		LaboratorioRepositorio laboratorioRepositorio = new LaboratorioRepositorio();
		ArrayList<Laboratorio> labs = laboratorioRepositorio.obterTodos();
		for (int i = 0; i < labs.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { labs.get(i).getId() + "", labs.get(i).getCodigo(),
					labs.get(i).getTotalAssentos() + "", labs.get(i).getAssentosDisponiveis() + "" });
		}

	}
	
	public static void acessarLaboratorio(int id, Pessoa pessoa) throws Exception {
		LaboratorioRepositorio laboratorioRepositorio = new LaboratorioRepositorio();
		
		if(PessoaUtil.isProfessor(pessoa)) {
			Professor professor = PessoaUtil.converterParaProfessor(pessoa);
			laboratorioRepositorio.autenticarProfessor((long) id, professor);
			
		} else {
			Aluno aluno = PessoaUtil.converterParaAluno(pessoa);
			laboratorioRepositorio.autenticarAluno((long) id,aluno);
		}
		
	}
	
	public static void encerrarSessao(int id,Pessoa pessoa) throws Exception {
		LaboratorioRepositorio laboratorioRepositorio = new LaboratorioRepositorio();
		if(PessoaUtil.isProfessor(pessoa)) {
			Professor professor = PessoaUtil.converterParaProfessor(pessoa);
			laboratorioRepositorio.desautenticarProfessor((long) id, professor);
			
		} else {
			Aluno aluno = PessoaUtil.converterParaAluno(pessoa);
			laboratorioRepositorio.desautenticarAluno((long) id,aluno);
		}
	}
	
	public static boolean isSessaoAtiva(Pessoa pessoa) {
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		ArrayList <Registro> registros = registroRepositorio.obterUltimoRegistroPessoa(pessoa.getId());
		
		if(registros.isEmpty()) {
			return false;
		}
		
		Registro registro = registros.get(0);
		
		if(registro.getAcao().equals("entrar")) {
			return true;
		}
		return false;
		
	}
	
	public static void alterarVisualização(Group group1,Group group2) {
		group1.setVisible(!group1.isVisible());
		group2.setVisible(!group2.isVisible());
		
	}
	public static int carregarSessao(Pessoa pessoa,Label labelCodLaboratorio,Label labelDataHora) {
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		ArrayList <Registro> registros = registroRepositorio.obterUltimoRegistroPessoa(pessoa.getId());
		if(registros.isEmpty()) {
			return -1;
		}
		Registro registro = registros.get(0);
		labelCodLaboratorio.setText(registro.getLaboratorio().getCodigo()+"");
		labelDataHora.setText(registro.getHora());
		return (int) registro.getLaboratorio().getId();
		
	}

}
