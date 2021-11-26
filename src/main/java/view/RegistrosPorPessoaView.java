package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import control.CadastrarControl;
import control.AcessoLaboratorioControl;
import control.EditarContaControl;
import control.MenuAcessoControl;
import control.RegistrosPorPessoaControl;
import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;
import modelo.Turno;

import org.eclipse.swt.widgets.Menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ArmListener;
import org.eclipse.swt.events.ArmEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.HelpEvent;

public class RegistrosPorPessoaView {

	protected Shell shlRegistroPorPessoa;
	private Table table;
	private Pessoa pessoa;


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlRegistroPorPessoa.open();
		shlRegistroPorPessoa.layout();
		shlRegistroPorPessoa.setImage(new Image(display, "src//main//java/resources//Science-University-icon.png"));
		while (!shlRegistroPorPessoa.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlRegistroPorPessoa = new Shell();
		shlRegistroPorPessoa.setSize(800, 600);
		shlRegistroPorPessoa.setText("Registro por pessoa");
		


		Group group = new Group(shlRegistroPorPessoa, SWT.NONE);
		group.setBounds(112, 32, 560, 463);

		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);

		table.setBounds(0, 27, 560, 408);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(71);
		tblclmnId.setText("id");

		TableColumn tblclmnAcao = new TableColumn(table, SWT.NONE);
		tblclmnAcao.setWidth(67);
		tblclmnAcao.setText("Ação");

		TableColumn tblclmnHora = new TableColumn(table, SWT.NONE);
		tblclmnHora.setWidth(193);
		tblclmnHora.setText("Data e hora");

		TableColumn tblclmnIdPessoa = new TableColumn(table, SWT.NONE);
		tblclmnIdPessoa.setWidth(100);
		tblclmnIdPessoa.setText("ID pessoa");

		
		TableColumn tblclmnIdLaboratrio = new TableColumn(table, SWT.NONE);
		tblclmnIdLaboratrio.setWidth(128);
		tblclmnIdLaboratrio.setText("ID laboratório");
		
		RegistrosPorPessoaControl.carregarTabela(table,getPessoa());
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
}
