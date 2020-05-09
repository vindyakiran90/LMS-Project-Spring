package com.tyss.spring_lms.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tyss.spring_lms.beans.UserBean;

@Repository
public class RegisterationLoginDAOImplementation implements RegisterationLoginDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;
	
	@Override
	public UserBean login(String email, String password) {
		EntityManager manager = null;
		try {
			manager = factory.createEntityManager();
			String jpql = "select e from UserBean e where e.email=:email and e.password=:password";
			TypedQuery<UserBean> query = manager.createQuery(jpql, UserBean.class);
			query.setParameter("email", email);
			query.setParameter("password", password);
			UserBean bean = query.getSingleResult();
			return bean;
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public boolean register(UserBean bean) {
		EntityManager manager = null;
		EntityTransaction transaction = null;

		try {
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			manager.close();
		}
	}
}
