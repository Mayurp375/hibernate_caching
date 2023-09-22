package org.example.connection;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StudentDao implements UserDao {
	private SessionFactory factory = HibernateConf.getFactory();
	

	public void saveUser(Student user) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}
	
	@Override
	public Student getUserById(long id) {
		Transaction transaction = null;
		Student user = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			user = session.get(Student.class, id);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
		return user;
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Student> getAllUsers() {
		Transaction transaction = null;
		List<Student> users = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			users = session.createQuery("from User").list();
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
		return users;
	}
	
	@Override
	public void updateUser(Student user) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.saveOrUpdate(user);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}
	
	@Override
	public void deleteUserById(long id) {
		Transaction transaction = null;
		try(Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			Student user = session.get(Student.class, id);
			session.delete(user);
			transaction.commit();
		} catch (Exception e) {
			if(transaction != null)
				transaction.rollback();
		}
	}
}