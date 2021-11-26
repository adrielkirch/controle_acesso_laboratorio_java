package control;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import modelo.Laboratorio;
import modelo.Pessoa;
import modelo.Registro;
import repositorio.LaboratorioRepositorio;
import repositorio.RegistroRepositorio;
import view.AcessoLaboratorioView;
import view.RegistrosPorPessoaView;
import view.TodosRegistrosView;

public class RegistrosPorPessoaControl {
	public static void displayView(Pessoa pessoa) {
		try {
			RegistrosPorPessoaView window = new RegistrosPorPessoaView();
			window.setPessoa(pessoa);
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void carregarTabela(Table table,Pessoa pessoa) {
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		ArrayList<Registro> registros = registroRepositorio.obterPorPessoa(pessoa.getId());
		for (int i = 0; i < registros.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { registros.get(i).getId() + "", registros.get(i).getAcao(),
					registros.get(i).getHora() + "", registros.get(i).getIdPessoa() + "", registros.get(i).getLaboratorio().getId() +"" });
		}
	}

}
