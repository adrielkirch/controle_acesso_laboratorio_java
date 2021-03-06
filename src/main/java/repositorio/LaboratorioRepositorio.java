package repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import modelo.Aluno;
import modelo.Laboratorio;
import modelo.Professor;
import modelo.Turno;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LaboratorioRepositorio {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	public LaboratorioRepositorio() {

	}

	public Laboratorio obterPorId(Long id) {
		Laboratorio laboratorio = entityManager.find(Laboratorio.class, id);
		return laboratorio != null ? laboratorio : null;
	}
	

	public ArrayList<Laboratorio> obterTodos() {
		return (ArrayList<Laboratorio>) entityManager.createQuery("from Laboratorio order by id asc").getResultList();
	}
	
	public boolean existeProfessorLabId(Professor professor, Long id) {
		Laboratorio laboratorio = obterPorId(id);
		if(laboratorio == null) {
			return false;
		}
		List<Professor> professores =  laboratorio.getProfessores();
		for (int i = 0; i < professores.size(); i++) {
			if(professores.get(i).getId() == professor.getId()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean existeAlunoLabId(Aluno aluno, Long id) {
		Laboratorio laboratorio = obterPorId(id);
		if(laboratorio == null) {
			return false;
		}
		List<Aluno> alunos =  laboratorio.getAlunos();
		for (int i = 0; i < alunos.size(); i++) {
			if(alunos.get(i).getId() == aluno.getId()) {
				return true;
			}
		}
		return false;
	}

	public Laboratorio adicionar(String codigo, int totalAssentos) {
		Laboratorio novoLaboratorio = new Laboratorio(codigo, totalAssentos);
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(novoLaboratorio);
		this.entityManager.getTransaction().commit();
		System.out.println("Laboratorio adicionado com sucesso");
		return novoLaboratorio;
	}

	public Laboratorio atualizar(Long id, String codigo, int totalAssentos) {
		Laboratorio laboratorio = this.entityManager.find(Laboratorio.class, id);
		this.entityManager.getTransaction().begin();
		laboratorio.setCodigo(codigo);
		laboratorio.setTotalAssentos(totalAssentos);
		this.entityManager.getTransaction().commit();
		System.out.println("Laboratorio atualizado com sucesso");
		return laboratorio;
	}

	public void autenticarAluno(Long idLaboratorio, Aluno aluno) throws Exception {
		Laboratorio laboratorio = this.entityManager.find(Laboratorio.class, idLaboratorio);

		this.entityManager.getTransaction().begin();
		List<Aluno> alunos = laboratorio.getAlunos();
		if(existeAlunoLabId(aluno, idLaboratorio) == true) {
			throw new Exception("Aluno j?? autenticado");
		} else if (laboratorio.getAssentosDisponiveis() == 0) {
			throw new Exception("Laborat??rio est?? totalmente ocupado.");
		}
		alunos.add(aluno);
		laboratorio.setAlunos(alunos);
		this.entityManager.getTransaction().commit();
		System.out.println("Aluno autenticado com sucesso no lab: " + idLaboratorio);

		this.entityManager.getTransaction().begin();
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		this.entityManager.getTransaction().commit();
		registroRepositorio.adicionar(entityManagerFactory,entityManager,aluno.getId(), laboratorio, "entrar", dtf.format(now));
		
	}

	public void desautenticarAluno(Long idLaboratorio, Aluno aluno) throws Exception {
		Laboratorio laboratorio = this.entityManager.find(Laboratorio.class, idLaboratorio);

		this.entityManager.getTransaction().begin();
		List<Aluno> alunos = laboratorio.getAlunos();
		if(existeAlunoLabId(aluno, idLaboratorio) == false) {
			throw new Exception("Aluno n??o est?? autenticado");
		}
		for (int i = 0; i < alunos.size(); i++) {
			if(alunos.get(i).getId() == aluno.getId()) {
				alunos.remove(i);
			}
		}
		
		laboratorio.setAlunos(alunos);
		this.entityManager.getTransaction().commit();
		System.out.println("Aluno desautenticado  com sucesso no lab: " + idLaboratorio);

		this.entityManager.getTransaction().begin();
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		this.entityManager.getTransaction().commit();
		
		registroRepositorio.adicionar(entityManagerFactory,entityManager,aluno.getId(), laboratorio, "sair", dtf.format(now));
	
	}

	public void autenticarProfessor(Long idLaboratorio, Professor professor) throws Exception {
		Laboratorio laboratorio = this.entityManager.find(Laboratorio.class, idLaboratorio);

		this.entityManager.getTransaction().begin();
		List<Professor> professores = laboratorio.getProfessores();
		if(existeProfessorLabId(professor, idLaboratorio) == true) {
			throw new Exception("Professor j?? autenticado");
		}
		professores.add(professor);
		laboratorio.setProfessores(professores);
		this.entityManager.getTransaction().commit();
		System.out.println("Professor autenticado com sucesso no lab: " + idLaboratorio);

		this.entityManager.getTransaction().begin();
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.entityManager.getTransaction().commit();
		
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		registroRepositorio.adicionar(entityManagerFactory,entityManager,professor.getId(), laboratorio, "entrar", dtf.format(now));
	}

	public void desautenticarProfessor(Long idLaboratorio, Professor professor) throws Exception {
		Laboratorio laboratorio = this.entityManager.find(Laboratorio.class, idLaboratorio);

		this.entityManager.getTransaction().begin();
		List<Professor> professores = laboratorio.getProfessores();
		if(existeProfessorLabId(professor, idLaboratorio) == false) {
			throw new Exception("Professor n??o est?? autenticado");
		}
		for (int i = 0; i < professores.size(); i++) {
			if(professores.get(i).getId() == professor.getId()) {
				professores.remove(i);
			}
		}
		laboratorio.setProfessores(professores);
		this.entityManager.getTransaction().commit();
		System.out.println("Professor desautenticado  com sucesso no lab: " + idLaboratorio);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
	
		RegistroRepositorio registroRepositorio = new RegistroRepositorio();
		registroRepositorio.adicionar(entityManagerFactory,entityManager,professor.getId(), laboratorio, "sair", dtf.format(now));
		
	}

}