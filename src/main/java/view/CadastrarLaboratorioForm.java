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
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class CadastrarLaboratorioForm {

	protected Shell shlCadastrarProfessor;
	private Text inputCodigo;
	private Text inputAssentos;


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlCadastrarProfessor.open();
		shlCadastrarProfessor.layout();
		shlCadastrarProfessor.setImage(new Image(display,"src//main//java/resources//Science-University-icon.png"));
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
		shlCadastrarProfessor.setText("Cadastrar laboratorio");
		shlCadastrarProfessor.setLayout(new FormLayout());
		
		Label lblCadastrarLab = new Label(shlCadastrarProfessor, SWT.HORIZONTAL);
		FormData fd_lblCadastrarLab = new FormData();
		fd_lblCadastrarLab.top = new FormAttachment(0, 23);
		fd_lblCadastrarLab.left = new FormAttachment(0, 269);
		lblCadastrarLab.setLayoutData(fd_lblCadastrarLab);
		lblCadastrarLab.setText("Cadastrar laboratório");
		lblCadastrarLab.setFont(SWTResourceManager.getFont("Lucida Sans Unicode", 14, SWT.NORMAL));
		lblCadastrarLab.setAlignment(SWT.CENTER);
		
		Label lblCodigo = new Label(shlCadastrarProfessor, SWT.NONE);
		FormData fd_lblCodigo = new FormData();
		fd_lblCodigo.right = new FormAttachment(0, 266);
		fd_lblCodigo.top = new FormAttachment(0, 82);
		fd_lblCodigo.left = new FormAttachment(0, 32);
		lblCodigo.setLayoutData(fd_lblCodigo);
		lblCodigo.setText("Código do laboratório");
		
		inputCodigo = new Text(shlCadastrarProfessor, SWT.BORDER);
		FormData fd_inputCodigo = new FormData();
		fd_inputCodigo.right = new FormAttachment(0, 746);
		fd_inputCodigo.top = new FormAttachment(0, 120);
		fd_inputCodigo.left = new FormAttachment(0, 32);
		inputCodigo.setLayoutData(fd_inputCodigo);
		
		Label lblAssentos = new Label(shlCadastrarProfessor, SWT.NONE);
		FormData fd_lblAssentos = new FormData();
		fd_lblAssentos.right = new FormAttachment(0, 244);
		fd_lblAssentos.top = new FormAttachment(0, 162);
		fd_lblAssentos.left = new FormAttachment(0, 32);
		lblAssentos.setLayoutData(fd_lblAssentos);
		lblAssentos.setText("Total de assentos");
		
		inputAssentos = new Text(shlCadastrarProfessor, SWT.BORDER);
		FormData fd_inputAssentos = new FormData();
		fd_inputAssentos.right = new FormAttachment(0, 746);
		fd_inputAssentos.top = new FormAttachment(0, 200);
		fd_inputAssentos.left = new FormAttachment(0, 32);
		inputAssentos.setLayoutData(fd_inputAssentos);
		
		Button btnCadastrar = new Button(shlCadastrarProfessor, SWT.NONE);
		btnCadastrar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
		 
				 boolean casdastrou = CadastrarControl.cadastrarLaboratorio(inputCodigo.getText(),Integer.parseInt(inputAssentos.getText()));
				 if(casdastrou) {
					 JOptionPane.showMessageDialog(null, "Laboratório, " + inputCodigo.getText() + " cadastrado com sucesso.");
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
		fd_btnCadastrar.top = new FormAttachment(inputAssentos, 22);
		fd_btnCadastrar.left = new FormAttachment(0, 32);
		fd_btnCadastrar.bottom = new FormAttachment(100, -238);
		fd_btnCadastrar.right = new FormAttachment(0, 230);
		btnCadastrar.setLayoutData(fd_btnCadastrar);
		btnCadastrar.setText("Cadastrar");

	}
}
