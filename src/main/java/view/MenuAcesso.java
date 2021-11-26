package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import control.CadastrarControl;
import control.AcessoLaboratorioControl;
import control.MenuAcessoControl;
import modelo.Pessoa;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class MenuAcesso {

	protected Shell shlMenuprincipal;
	private Text inputEmail;
	private Text inputSenha;


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		
		createContents();
		shlMenuprincipal.open();
		shlMenuprincipal.layout();
		shlMenuprincipal.setImage(new Image(display,"src//main//java/resources//Science-University-icon.png"));
		while (!shlMenuprincipal.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shlMenuprincipal = new Shell();
		shlMenuprincipal.setSize(800, 600);
		
		shlMenuprincipal.setText("MenuPrincipal");
		shlMenuprincipal.setLayout(null);
		
		
		Label titleLabel = new Label(shlMenuprincipal, SWT.HORIZONTAL);
		titleLabel.setBounds(0, 24, 803, 47);
		titleLabel.setAlignment(SWT.CENTER);
		titleLabel.setFont(SWTResourceManager.getFont("Lucida Sans Unicode", 14, SWT.NORMAL));
		titleLabel.setText("Controle de acesso ao laboratório de informática");
		
		Label lblEmail = new Label(shlMenuprincipal, SWT.NONE);
		lblEmail.setBounds(82, 143, 81, 25);
		lblEmail.setText("Email");
		
		inputEmail = new Text(shlMenuprincipal, SWT.BORDER);
		inputEmail.setBounds(82, 186, 644, 31);
		
		Label lblSenha = new Label(shlMenuprincipal, SWT.NONE);
		lblSenha.setText("Senha");
		lblSenha.setBounds(82, 234, 81, 25);
		
		inputSenha = new Text(shlMenuprincipal, SWT.BORDER);
		inputSenha.setBounds(82, 277, 644, 31);
		
		Button btnEntrar = new Button(shlMenuprincipal, SWT.NONE);
		btnEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Pessoa pessoa = MenuAcessoControl.entrar(inputEmail.getText(), inputSenha.getText());
				if(pessoa != null) {
					 JOptionPane.showMessageDialog(null, "Login feito com sucesso");
					 shlMenuprincipal.close();
					 AcessoLaboratorioControl.displayView(pessoa);
					
				} else {
					 JOptionPane.showMessageDialog(null,
							    "Email e/ou senha incorreto(s).",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnEntrar.setBounds(82, 342, 150, 47);
		btnEntrar.setText("Entrar");
		
		Button btnCadastrarProfessor = new Button(shlMenuprincipal, SWT.NONE);
		btnCadastrarProfessor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				CadastrarControl.displayProfessorCadastroView();
			}
		});
		btnCadastrarProfessor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCadastrarProfessor.setText("Cadastrar professor");
		btnCadastrarProfessor.setBounds(258, 476, 175, 47);
		
		Label lblNoPossuCadastro = new Label(shlMenuprincipal, SWT.NONE);
		lblNoPossuCadastro.setBounds(82, 426, 259, 25);
		lblNoPossuCadastro.setText("Não possuí cadastro ?");
		
		Button btnCadastrarAluno = new Button(shlMenuprincipal, SWT.NONE);
		btnCadastrarAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				CadastrarControl.displayAlunoCadastroView();
			}
		});
		btnCadastrarAluno.setText("Cadastrar aluno");
		btnCadastrarAluno.setBounds(82, 476, 150, 47);

	}
}
