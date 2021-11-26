package repositorio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import modelo.Aluno;
import modelo.Professor;
import modelo.Turno;
import util.PessoaUtil;

import java.util.ArrayList;

public class AlunoRepositorio {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-jpa");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	public AlunoRepositorio() {

	}

	public Aluno obterPorId(Long id) {
		Aluno aluno = entityManager.find(Aluno.class, id);
		return aluno != null ? aluno : null;
	}

	public ArrayList<Aluno> obterTodos() {
		return (ArrayList<Aluno>) entityManager.createQuery("from Aluno").getResultList();
	}

	public ArrayList<Aluno> autenticarAluno(String email, String senha) {
		return (ArrayList<Aluno>) entityManager.createQuery("from Aluno where email = :email and senha = :senha")
				.setParameter("email", email).setParameter("senha", senha).getResultList();
	}



	public Aluno adicionar(String nome, String senha, String email, String cpf, Turno turno) throws Exception {
		
		if(PessoaUtil.existeAlunoEmail(email) || PessoaUtil.existeProfessorEmail(email)) {
			throw new Exception("Email já utilizado.");
		}
		
		Aluno novoAluno = new Aluno(nome, senha, email, cpf, turno);
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(novoAluno);
		this.entityManager.getTransaction().commit();
		System.out.println("Aluno adicionado com sucesso");
		return novoAluno;
	}

	public Aluno atualizar(Long id, String nome, String senha, String email, String cpf, Turno turno) {
		Aluno aluno = this.entityManager.find(Aluno.class, id);
		this.entityManager.getTransaction().begin();
		aluno.setNome(nome);
		aluno.setEmail(email);
		aluno.setCpf(cpf);
		aluno.setTurno(turno);
		aluno.setSenha(senha);
		this.entityManager.getTransaction().commit();
		System.out.println("Aluno atualizado com sucesso");
		return aluno;
	}

	public void remover(Long id) {
		Aluno aluno = this.entityManager.find(Aluno.class, id);
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(aluno);
		this.entityManager.getTransaction().commit();
		System.out.println("aluno removido com sucesso");
	}

}