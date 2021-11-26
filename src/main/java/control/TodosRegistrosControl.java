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
import view.TodosRegistrosView;

public class TodosRegistrosControl {
	public static void displayView() {
		try {
			TodosRegistrosView window = new TodosRegistrosView();
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void carregarTabela(Table table) {
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		ArrayList<Registro> registros = registroRepositorio.obterTodos();
		for (int i = 0; i < registros.size(); i++) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(new String[] { registros.get(i).getId() + "", registros.get(i).getAcao(),
					registros.get(i).getHora() + "", registros.get(i).getIdPessoa() + "", registros.get(i).getLaboratorio().getId() +"" });
		}
	}

}
