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

public class CadastrarAlunoForm {

	protected Shell shell;
	private Text inputEmail;
	private Text inputSenha;
	private Text inputNome;
	private Text inputCpf;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		CadastrarControl.displayAlunoCadastroView();
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
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
		shell = new Shell();
		shell.setSize(800, 600);
		shell.setText("Cadastrar aluno");
		shell.setLayout(new FormLayout());
		
		Label lblCadastrarAluno = new Label(shell, SWT.HORIZONTAL);
		FormData fd_lblCadastrarAluno = new FormData();
		fd_lblCadastrarAluno.top = new FormAttachment(0, 23);
		fd_lblCadastrarAluno.left = new FormAttachment(0, 269);
		lblCadastrarAluno.setLayoutData(fd_lblCadastrarAluno);
		lblCadastrarAluno.setText("Cadastrar aluno");
		lblCadastrarAluno.setFont(SWTResourceManager.getFont("Lucida Sans Unicode", 14, SWT.NORMAL));
		lblCadastrarAluno.setAlignment(SWT.CENTER);
		
		Label lblEmail = new Label(shell, SWT.NONE);
		FormData fd_lblEmail = new FormData();
		fd_lblEmail.right = new FormAttachment(0, 113);
		fd_lblEmail.top = new FormAttachment(0, 82);
		fd_lblEmail.left = new FormAttachment(0, 32);
		lblEmail.setLayoutData(fd_lblEmail);
		lblEmail.setText("Email");
		
		inputEmail = new Text(shell, SWT.BORDER);
		FormData fd_inputEmail = new FormData();
		fd_inputEmail.right = new FormAttachment(0, 746);
		fd_inputEmail.top = new FormAttachment(0, 120);
		fd_inputEmail.left = new FormAttachment(0, 32);
		inputEmail.setLayoutData(fd_inputEmail);
		
		Label lblSenha = new Label(shell, SWT.NONE);
		FormData fd_lblSenha = new FormData();
		fd_lblSenha.right = new FormAttachment(0, 113);
		fd_lblSenha.top = new FormAttachment(0, 162);
		fd_lblSenha.left = new FormAttachment(0, 32);
		lblSenha.setLayoutData(fd_lblSenha);
		lblSenha.setText("Senha");
		
		inputSenha = new Text(shell, SWT.BORDER);
		FormData fd_inputSenha = new FormData();
		fd_inputSenha.right = new FormAttachment(0, 746);
		fd_inputSenha.top = new FormAttachment(0, 200);
		fd_inputSenha.left = new FormAttachment(0, 32);
		inputSenha.setLayoutData(fd_inputSenha);
		
		Label lblNome = new Label(shell, SWT.NONE);
		FormData fd_lblNome = new FormData();
		fd_lblNome.right = new FormAttachment(0, 113);
		fd_lblNome.top = new FormAttachment(0, 237);
		fd_lblNome.left = new FormAttachment(0, 32);
		lblNome.setLayoutData(fd_lblNome);
		lblNome.setText("nome");
		
		inputNome = new Text(shell, SWT.BORDER);
		FormData fd_inputNome = new FormData();
		fd_inputNome.right = new FormAttachment(0, 746);
		fd_inputNome.top = new FormAttachment(0, 275);
		fd_inputNome.left = new FormAttachment(0, 32);
		inputNome.setLayoutData(fd_inputNome);
		
		inputCpf = new Text(shell, SWT.BORDER);
		FormData fd_inputCpf = new FormData();
		fd_inputCpf.right = new FormAttachment(0, 746);
		fd_inputCpf.top = new FormAttachment(0, 350);
		fd_inputCpf.left = new FormAttachment(0, 32);
		inputCpf.setLayoutData(fd_inputCpf);
		
		Label lblCpf = new Label(shell, SWT.NONE);
		FormData fd_lblCpf = new FormData();
		fd_lblCpf.right = new FormAttachment(0, 113);
		fd_lblCpf.top = new FormAttachment(0, 312);
		fd_lblCpf.left = new FormAttachment(0, 32);
		lblCpf.setLayoutData(fd_lblCpf);
		lblCpf.setText("CPF");
		
		Label lblTurno = new Label(shell, SWT.NONE);
		FormData fd_lblTurno = new FormData();
		fd_lblTurno.right = new FormAttachment(0, 113);
		fd_lblTurno.top = new FormAttachment(0, 387);
		fd_lblTurno.left = new FormAttachment(0, 32);
		lblTurno.setLayoutData(fd_lblTurno);
		lblTurno.setText("Turno");
		
		Combo combo = new Combo(shell, SWT.NONE);
		CadastrarControl.addTurno(combo);
		FormData fd_combo = new FormData();
		fd_combo.right = new FormAttachment(inputEmail, 0, SWT.RIGHT);
		fd_combo.top = new FormAttachment(lblTurno, 12);
		fd_combo.left = new FormAttachment(lblEmail, 0, SWT.LEFT);
		combo.setLayoutData(fd_combo);
		
		Button btnCadastrar = new Button(shell, SWT.NONE);
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
		
				 String selectedCombo = combo.getItem(combo.getSelectionIndex()).toString();
				 
				 boolean casdastrou = CadastrarControl.cadastrarAluno(inputEmail.getText(), inputSenha.getText(), inputNome.getText(), inputCpf.getText(), selectedCombo);
				 if(casdastrou) {
					 JOptionPane.showMessageDialog(null, "Aluno, " + inputEmail.getText() + " cadastrado com sucesso.");
				 } else {
					 JOptionPane.showMessageDialog(null,
							    "Valores das entradas, vazios ou incorretos, tente novamente.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
				 }
				 
				shell.close();
				
			}
		});
		
		FormData fd_btnCadastrar = new FormData();
		fd_btnCadastrar.top = new FormAttachment(combo, 23);
		fd_btnCadastrar.bottom = new FormAttachment(100, -22);
		fd_btnCadastrar.left = new FormAttachment(0, 33);
		fd_btnCadastrar.right = new FormAttachment(0, 226);
		btnCadastrar.setLayoutData(fd_btnCadastrar);
		btnCadastrar.setText("Cadastrar");

	}
}
