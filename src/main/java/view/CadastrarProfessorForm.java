package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import control.CadastrarControl;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CadastrarProfessorForm {

	protected Shell shlCadastrarProfessor;
	private Text inputEmail;
	private Text inputSenha;
	private Text inputNome;
	private Text inputCpf;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		CadastrarControl.displayProfessorCadastroView();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCadastrarProfessor.open();
		shlCadastrarProfessor.layout();
		while (!shlCadastrarProfessor.isDisposed()) {
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
		shlCadastrarProfessor = new Shell();
		shlCadastrarProfessor.setSize(800, 600);
		shlCadastrarProfessor.setText("Cadastrar professor");
		shlCadastrarProfessor.setLayout(new FormLayout());
		
		Label lblCadastrarProfessor = new Label(shlCadastrarProfessor, SWT.HORIZONTAL);
		FormData fd_lblCadastrarProfessor = new FormData();
		fd_lblCadastrarProfessor.top = new FormAttachment(0, 23);
		fd_lblCadastrarProfessor.left = new FormAttachment(0, 269);
		lblCadastrarProfessor.setLayoutData(fd_lblCadastrarProfessor);
		lblCadastrarProfessor.setText("Cadastrar professor");
		lblCadastrarProfessor.setFont(SWTResourceManager.getFont("Lucida Sans Unicode", 14, SWT.NORMAL));
		lblCadastrarProfessor.setAlignment(SWT.CENTER);
		
		Label lblEmail = new Label(shlCadastrarProfessor, SWT.NONE);
		FormData fd_lblEmail = new FormData();
		fd_lblEmail.right = new FormAttachment(0, 113);
		fd_lblEmail.top = new FormAttachment(0, 82);
		fd_lblEmail.left = new FormAttachment(0, 32);
		lblEmail.setLayoutData(fd_lblEmail);
		lblEmail.setText("Email");
		
		inputEmail = new Text(shlCadastrarProfessor, SWT.BORDER);
		FormData fd_inputEmail = new FormData();
		fd_inputEmail.right = new FormAttachment(0, 746);
		fd_inputEmail.top = new FormAttachment(0, 120);
		fd_inputEmail.left = new FormAttachment(0, 32);
		inputEmail.setLayoutData(fd_inputEmail);
		
		Label lblSenha = new Label(shlCadastrarProfessor, SWT.NONE);
		FormData fd_lblSenha = new FormData();
		fd_lblSenha.right = new FormAttachment(0, 113);
		fd_lblSenha.top = new FormAttachment(0, 162);
		fd_lblSenha.left = new FormAttachment(0, 32);
		lblSenha.setLayoutData(fd_lblSenha);
		lblSenha.setText("Senha");
		
		inputSenha = new Text(shlCadastrarProfessor, SWT.BORDER);
		FormData fd_inputSenha = new FormData();
		fd_inputSenha.right = new FormAttachment(0, 746);
		fd_inputSenha.top = new FormAttachment(0, 200);
		fd_inputSenha.left = new FormAttachment(0, 32);
		inputSenha.setLayoutData(fd_inputSenha);
		
		Label lblNome = new Label(shlCadastrarProfessor, SWT.NONE);
		FormData fd_lblNome = new FormData();
		fd_lblNome.right = new FormAttachment(0, 113);
		fd_lblNome.top = new FormAttachment(0, 237);
		fd_lblNome.left = new FormAttachment(0, 32);
		lblNome.setLayoutData(fd_lblNome);
		lblNome.setText("nome");
		
		inputNome = new Text(shlCadastrarProfessor, SWT.BORDER);
		FormData fd_inputNome = new FormData();
		fd_inputNome.right = new FormAttachment(0, 746);
		fd_inputNome.top = new FormAttachment(0, 275);
		fd_inputNome.left = new FormAttachment(0, 32);
		inputNome.setLayoutData(fd_inputNome);
		
		inputCpf = new Text(shlCadastrarProfessor, SWT.BORDER);
		FormData fd_inputCpf = new FormData();
		fd_inputCpf.right = new FormAttachment(0, 746);
		fd_inputCpf.top = new FormAttachment(0, 350);
		fd_inputCpf.left = new FormAttachment(0, 32);
		inputCpf.setLayoutData(fd_inputCpf);
		
		Label lblCpf = new Label(shlCadastrarProfessor, SWT.NONE);
		FormData fd_lblCpf = new FormData();
		fd_lblCpf.right = new FormAttachment(0, 113);
		fd_lblCpf.top = new FormAttachment(0, 312);
		fd_lblCpf.left = new FormAttachment(0, 32);
		lblCpf.setLayoutData(fd_lblCpf);
		lblCpf.setText("CPF");
		
		Button btnCadastrar = new Button(shlCadastrarProfessor, SWT.NONE);
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
		 
				 boolean casdastrou = CadastrarControl.cadastrarProfessor(inputEmail.getText(), inputSenha.getText(), inputNome.getText(), inputCpf.getText());
				 if(casdastrou) {
					 JOptionPane.showMessageDialog(null, "Professor, " + inputEmail.getText() + " cadastrado com sucesso.");
				 } else {
					 JOptionPane.showMessageDialog(null,
							    "Valores das entradas, vazios ou incorretos, tente novamente.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
				 }
				 
				 shlCadastrarProfessor.close();
				
			}
		});
		
		FormData fd_btnCadastrar = new FormData();
		fd_btnCadastrar.top = new FormAttachment(inputCpf, 32);
		fd_btnCadastrar.left = new FormAttachment(0, 36);
		fd_btnCadastrar.bottom = new FormAttachment(100, -89);
		fd_btnCadastrar.right = new FormAttachment(0, 229);
		btnCadastrar.setLayoutData(fd_btnCadastrar);
		btnCadastrar.setText("Cadastrar");

	}
}
