package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import entities.Time;
import util.JPAUtil;


public class TimeDAO {
	public static void salvar(Time time) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(time);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void atualizar(Time time) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(time);
		em.getTransaction().commit();
		em.close();
	}
	
	public static void remover(Time time) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		time = em.find(Time.class, time.getId());
		em.remove(time);
		em.getTransaction().commit();
		em.close();
	}
	
	public static Time buscarPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		Time time = em.find(Time.class, id);
		em.close();
		return time;
	}
	
	public static List<Time> buscarTodos(){
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select time from Time time");
		List<Time> lista = q.getResultList();
		em.close();
		return lista;
	}
}
