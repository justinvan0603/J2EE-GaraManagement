package business.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.Tho;;

@Repository
public class ThoDaoImpl implements GeneralDao<Tho> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Tho findById(long id, Class<Tho> entityClass) {
		return (Tho) this.sessionFactory.openSession().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tho> getAll(Class<Tho> entityClass) {
		List<Tho> results = new ArrayList<Tho>();
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.getTransaction();
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
	public List<Tho> query(String query, Class<Tho> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, Tho newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<Tho> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Tho newEntity) {
		Transaction transaction = null;
		Session session = null;
		boolean result = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.getTransaction();
			transaction.begin();
			session.save(newEntity);
			if (!transaction.wasCommitted())
				transaction.commit();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = false;
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return result;
	}

	@Override
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
