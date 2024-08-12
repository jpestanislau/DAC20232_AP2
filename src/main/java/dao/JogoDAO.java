package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entities.Jogo;
import util.JPAUtil;

public class JogoDAO {
	public static void salvar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void atualizar(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void remover(Jogo jogo) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		jogo = em.find(Jogo.class, jogo.getId());
		em.remove(jogo);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Jogo buscarPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		Jogo jogo = em.find(Jogo.class, id);
		em.close();
		return jogo;
	}
	
	public static List<Jogo> buscarTodos(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select jogo from Jogo jogo");
		List<Jogo> lista = q.getResultList();
		em.close();
		return lista;
	}
	
	//Tentativa utilizando Hibernate Query Language
	public static List<Jogo> buscarComFiltro(Integer idTime){
		String hql = "select jogo from Jogo jogo where jogo.time1_id = :idDoTime";
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery(hql);
		q.setParameter("idDoTime",idTime);
		List<Jogo> lista = q.getResultList();
		em.close();
		return lista;
	}
}
