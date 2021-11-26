package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import control.CadastrarControl;
import control.AcessoLaboratorioControl;
import control.EditarContaControl;
import control.MenuAcessoControl;
import control.RegistrosPorPessoaControl;
import control.TodosRegistrosControl;
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
import org.eclipse.wb.swt.SWTResourceManager;

public class AcessoLaboratorioView {

	protected Shell shell;
	private Table table;
	private int laboratorioSelecionadoId;
	private Pessoa pessoa;
	private boolean sessaoAtiva;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Aluno a = new Aluno("test", "234", "test@test", "123", Turno.MATUTINO);
		AcessoLaboratorioControl.displayView(a);
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		shell.setImage(new Image(display, "src//main//java/resources//Science-University-icon.png"));
		while (!shell.isDisposed()) {
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
		shell = new Shell();
		shell.setSize(800, 620);
		shell.setText("Controle de acesso laboratório");

		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		MenuItem mntmItem = new MenuItem(menu, SWT.CASCADE);
		mntmItem.setText("Menu");

		Menu menu_1 = new Menu(mntmItem);
		mntmItem.setMenu(menu_1);

		MenuItem mntmTeste = new MenuItem(menu_1, SWT.NONE);
		mntmTeste.setText("Acessar laboratório");

		MenuItem mntmAcessarRegistros = new MenuItem(menu_1, SWT.NONE);
		mntmAcessarRegistros.setText("Acessar meus registros");
		
		mntmAcessarRegistros.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				RegistrosPorPessoaControl.displayView(getPessoa());
			}
		});
		
		if(pessoa instanceof Professor) {
			MenuItem mntmAcessarTodosRegistros = new MenuItem(menu_1, SWT.NONE);
			mntmAcessarTodosRegistros.setText("Acessar todos registros");
			
			mntmAcessarTodosRegistros.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
				
					TodosRegistrosControl.displayView();
				}
			});
		}
		
		MenuItem mntmEditar = new MenuItem(menu_1, SWT.NONE);
		mntmEditar.setText("Minha conta");

		mntmEditar.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				EditarContaControl.displayView(getPessoa());
			}
		});
		
		if(pessoa instanceof Professor) {
			
			MenuItem mntmCadastrarLab = new MenuItem(menu_1, SWT.NONE);
			mntmCadastrarLab.setText("Cadastrar laboratório");
			
			mntmCadastrarLab.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
				
					CadastrarControl.displayLaboratorioCadastroView();
				}
			});
				
		}
		

		MenuItem mntmSair = new MenuItem(menu_1, SWT.NONE);

		mntmSair.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				shell.close();
				MenuAcessoControl.displayView();
			}
		});

		mntmSair.setText("Sair");

		Group group = new Group(shell, SWT.NONE);
		group.setBounds(120, 60, 546, 463);

		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		table.setBounds(20, 25, 526, 358);

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(76);
		tblclmnId.setText("id");

		TableColumn tblclmnCodigo = new TableColumn(table, SWT.NONE);
		tblclmnCodigo.setWidth(100);
		tblclmnCodigo.setText("codigo");

		TableColumn tblclmnTotalAssentos = new TableColumn(table, SWT.NONE);
		tblclmnTotalAssentos.setWidth(135);
		tblclmnTotalAssentos.setText("Total assentos");

		TableColumn tblclmnAssentosDisponveis = new TableColumn(table, SWT.NONE);
		tblclmnAssentosDisponveis.setWidth(211);
		tblclmnAssentosDisponveis.setText("Assentos disponíveis");

		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {

				TableItem[] selection = table.getSelection();
				String selectedItem = selection[0] + " ";
				selectedItem = selectedItem.replaceAll("[a-zA-Z\\\" ]*", "");
				selectedItem = selectedItem.replace("{", "");
				selectedItem = selectedItem.replace("}", "");
				setLaboratorioSelecionadoId(Integer.parseInt(selectedItem));

			}
		});

		AcessoLaboratorioControl.carregarTabela(table);

	

		
		Group group_2 = new Group(shell, SWT.NONE);
		group_2.setBounds(120, 60, 546, 411);
		
		Label lblSessoAtiva = new Label(group_2, SWT.NONE);
		lblSessoAtiva.setText("Sessão ativa");
		lblSessoAtiva.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblSessoAtiva.setBounds(167, 22, 179, 63);
		
		Label lblCodigoLaboratrio = new Label(group_2, SWT.NONE);
		lblCodigoLaboratrio.setText("Código do Lab:");
		lblCodigoLaboratrio.setBounds(10, 137, 131, 41);
		
		Label lblhoraEntrada = new Label(group_2, SWT.NONE);
		lblhoraEntrada.setText("Data/hora de entrada:");
		lblhoraEntrada.setBounds(10, 228, 179, 25);
		
		Label lblCodLabVal = new Label(group_2, SWT.NONE);
		lblCodLabVal.setBounds(147, 137, 155, 41);
		
		Label lblDataVal = new Label(group_2, SWT.NONE);
		lblDataVal.setBounds(216, 228, 240, 41);
		
		Button btnEncerrar = new Button(group_2, SWT.NONE);
		btnEncerrar.setText("Encerrar sessão");
		btnEncerrar.setBounds(10, 341, 147, 46);
		btnEncerrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					AcessoLaboratorioControl.encerrarSessao(getLaboratorioSelecionadoId(), getPessoa());
					JOptionPane.showMessageDialog(null, "Sessão finalizada com sucesso.");
					AcessoLaboratorioControl.alterarVisualização(group, group_2);
					AcessoLaboratorioControl.carregarTabela(table);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
		});
		
		Button btnAcessarLab = new Button(group, SWT.NONE);
		btnAcessarLab.setBounds(184, 401, 182, 52);
		btnAcessarLab.setText("Acessar laboratório");
		
		Label lblAcessoAoLaboratrio = new Label(shell, SWT.NONE);
		lblAcessoAoLaboratrio.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		lblAcessoAoLaboratrio.setBounds(128, 10, 529, 45);
		lblAcessoAoLaboratrio.setText("Acesso ao laboratório de informática");
		btnAcessarLab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				try {
					AcessoLaboratorioControl.acessarLaboratorio(getLaboratorioSelecionadoId(), getPessoa());
					JOptionPane.showMessageDialog(null, "Acesso feito com sucesso.");
					AcessoLaboratorioControl.alterarVisualização(group, group_2);
					int idLab = AcessoLaboratorioControl.carregarSessao(getPessoa(), lblCodLabVal, lblDataVal);
					setLaboratorioSelecionadoId(idLab);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.toString(), "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		if(sessaoAtiva) {
			group.setVisible(false);
			group_2.setVisible(true);
			int idLab = AcessoLaboratorioControl.carregarSessao(getPessoa(), lblCodLabVal, lblDataVal);
			setLaboratorioSelecionadoId(idLab);
		} else {
			group.setVisible(true);
			group_2.setVisible(false);
		}

	}

	public int getLaboratorioSelecionadoId() {
		return laboratorioSelecionadoId;
	}

	public void setLaboratorioSelecionadoId(int laboratorioSelecionadoId) {
		this.laboratorioSelecionadoId = laboratorioSelecionadoId;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public boolean isSessaoAtiva() {
		return sessaoAtiva;
	}

	public void setSessaoAtiva(boolean sessaoAtiva) {
		this.sessaoAtiva = sessaoAtiva;
	}
	
	
	
}
