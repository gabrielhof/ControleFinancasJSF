package br.feevale.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import br.feevale.settings.ApplicationConstants;

public abstract class HibernateDAOImpl<T> implements GenericDAO<T> {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(ApplicationConstants.PERSISTENCE_UNIT_NAME);
	
	protected Session getHibernateSession() {
		EntityManager manager = factory.createEntityManager();
		return manager.unwrap(Session.class);
	}
	
	@Override
	public T save(T bean) throws Exception {
		Session session = getHibernateSession();
		session.saveOrUpdate(bean);
		
		session.close();
		
		return bean;
	}

	@Override
	public boolean delete(T bean) throws Exception {
		Session session = getHibernateSession();
		session.delete(bean);
		
		session.close();
		
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(Integer id) throws Exception {
		Session session = getHibernateSession();
		T bean = (T) session.get(getBeanClass(), id);
		
		session.close();
		
		return bean;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> listAll() throws Exception {
		Session session = getHibernateSession();
		Criteria criteria = session.createCriteria(getBeanClass());
		List<T> list = criteria.list();
		
		session.close();
		
		return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> queryByExample(T example) throws Exception {
		Session session = getHibernateSession();
		Criteria criteria = session.createCriteria(getBeanClass());
		criteria.add(Example.create(example));
		
		List<T> list = criteria.list();
		
		session.close();
		
		return list;
	}
	
	protected abstract Class<T> getBeanClass();

}
