package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import control.CadastrarControl;
import control.EditarContaControl;
import modelo.Aluno;
import modelo.Pessoa;
import modelo.Turno;

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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class EditarContaForm {

	protected Shell shlMinhaConta;
	private Text inputEmail;
	private Text inputSenha;
	private Text inputNome;
	private Text inputCpf;
	private Pessoa pessoa;
	private Combo combo; 

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlMinhaConta.open();
		shlMinhaConta.layout();
		shlMinhaConta.setImage(new Image(display, "src//main//java/resources//Science-University-icon.png"));
		while (!shlMinhaConta.isDisposed()) {
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
		shlMinhaConta = new Shell();
		shlMinhaConta.setSize(800, 600);
		shlMinhaConta.setText("Minha conta");
		shlMinhaConta.setLayout(new FormLayout());

		Label lblEditar = new Label(shlMinhaConta, SWT.HORIZONTAL);
		FormData fd_lblEditar = new FormData();
		fd_lblEditar.top = new FormAttachment(0, 23);
		fd_lblEditar.left = new FormAttachment(0, 269);
		lblEditar.setLayoutData(fd_lblEditar);
		lblEditar.setText("Minha conta");
		lblEditar.setFont(SWTResourceManager.getFont("Lucida Sans Unicode", 14, SWT.NORMAL));
		lblEditar.setAlignment(SWT.CENTER);

		Label lblEmail = new Label(shlMinhaConta, SWT.NONE);
		FormData fd_lblEmail = new FormData();
		fd_lblEmail.right = new FormAttachment(0, 113);
		fd_lblEmail.top = new FormAttachment(0, 82);
		fd_lblEmail.left = new FormAttachment(0, 32);
		lblEmail.setLayoutData(fd_lblEmail);
		lblEmail.setText("Email");

		inputEmail = new Text(shlMinhaConta, SWT.BORDER);
		FormData fd_inputEmail = new FormData();
		fd_inputEmail.right = new FormAttachment(0, 746);
		fd_inputEmail.top = new FormAttachment(0, 120);
		fd_inputEmail.left = new FormAttachment(0, 32);
		inputEmail.setLayoutData(fd_inputEmail);
		inputEmail.setText(getPessoa().getEmail());

		Label lblSenha = new Label(shlMinhaConta, SWT.NONE);
		FormData fd_lblSenha = new FormData();
		fd_lblSenha.right = new FormAttachment(0, 113);
		fd_lblSenha.top = new FormAttachment(0, 162);
		fd_lblSenha.left = new FormAttachment(0, 32);
		lblSenha.setLayoutData(fd_lblSenha);
		lblSenha.setText("Senha");

		inputSenha = new Text(shlMinhaConta, SWT.BORDER);
		FormData fd_inputSenha = new FormData();
		fd_inputSenha.right = new FormAttachment(0, 746);
		fd_inputSenha.top = new FormAttachment(0, 200);
		fd_inputSenha.left = new FormAttachment(0, 32);
		inputSenha.setLayoutData(fd_inputSenha);
		inputSenha.setText(getPessoa().getSenha());

		Label lblNome = new Label(shlMinhaConta, SWT.NONE);
		FormData fd_lblNome = new FormData();
		fd_lblNome.right = new FormAttachment(0, 113);
		fd_lblNome.top = new FormAttachment(0, 237);
		fd_lblNome.left = new FormAttachment(0, 32);
		lblNome.setLayoutData(fd_lblNome);
		lblNome.setText("nome");

		inputNome = new Text(shlMinhaConta, SWT.BORDER);
		FormData fd_inputNome = new FormData();
		fd_inputNome.right = new FormAttachment(0, 746);
		fd_inputNome.top = new FormAttachment(0, 275);
		fd_inputNome.left = new FormAttachment(0, 32);
		inputNome.setLayoutData(fd_inputNome);
		inputNome.setText(getPessoa().getNome());

		inputCpf = new Text(shlMinhaConta, SWT.BORDER);
		inputCpf.setEditable(false);
		FormData fd_inputCpf = new FormData();
		fd_inputCpf.right = new FormAttachment(0, 746);
		fd_inputCpf.top = new FormAttachment(0, 350);
		fd_inputCpf.left = new FormAttachment(0, 32);
		inputCpf.setLayoutData(fd_inputCpf);
		inputCpf.setText(getPessoa().getCpf());

		Label lblCpf = new Label(shlMinhaConta, SWT.NONE);
		FormData fd_lblCpf = new FormData();
		fd_lblCpf.right = new FormAttachment(0, 113);
		fd_lblCpf.top = new FormAttachment(0, 312);
		fd_lblCpf.left = new FormAttachment(0, 32);
		lblCpf.setLayoutData(fd_lblCpf);
		lblCpf.setText("CPF");
		setCombo(new Combo(shlMinhaConta, SWT.NONE));
		FormData fd_combo_1 = new FormData();
		fd_combo_1.right = new FormAttachment(inputEmail, 0, SWT.RIGHT);
		fd_combo_1.left = new FormAttachment(lblEmail, 0, SWT.LEFT);
		getCombo().setLayoutData(fd_combo_1);
		if (getPessoa() instanceof Aluno) {
			Aluno aluno = (Aluno) getPessoa();
			Label lblTurno = new Label(shlMinhaConta, SWT.NONE);
			FormData fd_lblTurno = new FormData();
			fd_lblTurno.right = new FormAttachment(0, 113);
			fd_lblTurno.top = new FormAttachment(0, 387);
			fd_lblTurno.left = new FormAttachment(0, 32);
			lblTurno.setLayoutData(fd_lblTurno);
			lblTurno.setText("Turno");

			//Combo combo = new Combo(shlMinhaConta, SWT.NONE);
			CadastrarControl.addTurno(getCombo());
			FormData fd_combo = new FormData();
			fd_combo.right = new FormAttachment(inputEmail, 0, SWT.RIGHT);
			fd_combo.top = new FormAttachment(lblTurno, 12);
			fd_combo.left = new FormAttachment(lblEmail, 0, SWT.LEFT);
			getCombo().setLayoutData(fd_combo);
			getCombo().select(getCombo().indexOf(aluno.getTurno().toString()));
			//String selectedCombo = getCombo().getItem(getCombo().getSelectionIndex()).toString();
		} else {
			getCombo().setVisible(false);
		}

		Button btnEditar = new Button(shlMinhaConta, SWT.NONE);
		fd_combo_1.bottom = new FormAttachment(btnEditar, -33);
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Turno t = null;
				if(getPessoa() instanceof Aluno) {
					t = Turno.valueOf(getCombo().getItem(getCombo().getSelectionIndex()).toString());
				} 		
				
				EditarContaControl.editarPessoa(getPessoa(),inputNome.getText(),inputSenha.getText(),inputEmail.getText(),t);
				JOptionPane.showMessageDialog(null, "Conta atualizada com sucesso");
				shlMinhaConta.close();

			}
		});

		FormData fd_btnEditar = new FormData();
		//fd_btnEditar.top = new FormAttachment(combo, 23);
		fd_btnEditar.bottom = new FormAttachment(100, -22);
		fd_btnEditar.left = new FormAttachment(0, 33);
		fd_btnEditar.right = new FormAttachment(0, 226);
		btnEditar.setLayoutData(fd_btnEditar);
		btnEditar.setText("Salvar");
		
		Label lblTurno = new Label(shlMinhaConta, SWT.NONE);
		lblTurno.setText("Turno");
		FormData fd_lblTurno = new FormData();
		fd_lblTurno.top = new FormAttachment(inputCpf, 6);
		fd_lblTurno.right = new FormAttachment(lblEmail, 34, SWT.RIGHT);
		fd_lblTurno.left = new FormAttachment(lblEmail, 0, SWT.LEFT);
		fd_lblTurno.bottom = new FormAttachment(getCombo(), -6);
		lblTurno.setLayoutData(fd_lblTurno);

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}
	
	
}
