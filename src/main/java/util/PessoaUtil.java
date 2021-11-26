package util;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Professor;

public class PessoaUtil {
	public static boolean isProfessor(Pessoa pessoa) {
		if(pessoa instanceof Professor) {
			return true;
		} 
		return false;
	}
	
	public static Aluno converterParaAluno(Pessoa pessoa) {
		if(!isProfessor(pessoa)) {
			return (Aluno) pessoa;
		}
		return null;
	}
	
	public static Professor converterParaProfessor(Pessoa pessoa) {
		if(isProfessor(pessoa)) {
			return (Professor) pessoa;
		}
		return null;
	}
	
	public static boolean existeProfessorEmail(String email) {

		 EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-jpa");
		 EntityManager entityManager = entityManagerFactory.createEntityManager();
		 ArrayList<Professor> professor =  (ArrayList<Professor>) entityManager.createQuery("from Professor where email = :email")
					.setParameter("email", email).getResultList();
		 if(professor.isEmpty()) {
			 return false;
		 }
		 return true;
		
	}
	
	public static boolean existeAlunoEmail(String email) {

		 EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence-jpa");
		 EntityManager entityManager = entityManagerFactory.createEntityManager();
		 ArrayList<Aluno> aluno =  (ArrayList<Aluno>) entityManager.createQuery("from Aluno where email = :email")
					.setParameter("email", email).getResultList();
		 if(aluno.isEmpty()) {
			 return false;
		 }
		 return true;
		
	}
}
