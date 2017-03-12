package business.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.Mechanic;

/**
 * Persistence layer implementation for {@link Mechanic} class
 * 
 * @author TNS
 */
@Repository
public class MechanicDaoImpl implements GeneralDao<Mechanic> {

	// session factory to interact with persistence layer for accessing and
	// transacting with database
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Mechanic findById(long id, Class<Mechanic> entityClass) {
		// TODO Auto-generated method stub
		return (Mechanic) this.sessionFactory.openSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mechanic> getAll(Class<Mechanic> entityClass) {
		List<Mechanic> results = new ArrayList<Mechanic>();
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			Criteria criteria = session.createCriteria(entityClass);
			results = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return results;
	}

	@Override
	public List<Mechanic> query(String query, Class<Mechanic> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, Mechanic newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<Mechanic> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Mechanic newEntity) {
		// TODO Auto-generated method stub
		Transaction transaction = null;
		Session session = null;
		boolean result = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			this.sessionFactory.getCurrentSession().save(newEntity);
			transaction.commit();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			result = false;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return result;

	}

	// get SessionFactory
	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
