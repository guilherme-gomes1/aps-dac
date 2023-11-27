package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Lancamento;
import util.JPAUtil;

public class LancamentoDao {
	public static void salvar(Lancamento lanc) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(lanc);
		em.getTransaction().commit();
		em.close();
	}

	public static void editar(Lancamento lanc) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(lanc);
		em.getTransaction().commit();
		em.close();
	}

	public static void remover(Lancamento lanc) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		lanc = em.find(Lancamento.class, lanc.getId());
		em.remove(lanc);
		em.getTransaction().commit();
		em.close();
	}

	public static Lancamento acharPorId(Integer id) {
		EntityManager em = JPAUtil.criarEntityManager();
		Lancamento lanc = em.find(Lancamento.class, id);
		em.close();
		return lanc;
	}

	public static List<Lancamento> acharTodos() {
		EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select lanc from Lancamento lanc");
		List<Lancamento> lista = q.getResultList();
		em.close();
		return lista;
	}
}
