package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.PhieuTiepNhan;

/**
 * Implementation Dao objet of {@link PhieuTiepNhan} entity
 * 
 * @author TNS
 *
 */
@Repository
public class PhieuTiepNhanDaoImpl implements GeneralDao<PhieuTiepNhan> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PhieuTiepNhan findById(long id, Class<PhieuTiepNhan> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		PhieuTiepNhan result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (PhieuTiepNhan) session.get(entityClass, id);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuTiepNhan> getAll(Class<PhieuTiepNhan> entityClass) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<PhieuTiepNhan> results = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(entityClass);
			results = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return results;
	}

	@Override
	public List<PhieuTiepNhan> query(String query, Class<PhieuTiepNhan> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, PhieuTiepNhan newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhieuTiepNhan entity = this.findById(id, PhieuTiepNhan.class);
			if (entity != null) {
				entity.setCreationDate(newInfor.getCreationDate());
				entity.setGivebackDate(newInfor.getGivebackDate());
				entity.setState(newInfor.getState());
				// more updates here

				session.saveOrUpdate(entity);
			}
			isSuccess = true;
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	@Override
	public boolean delete(Long id, Class<PhieuTiepNhan> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			// find the entity to be deleted
			PhieuTiepNhan entity = this.findById(id, entityClass);
			if (entity != null) {
				session.delete(entity);
			}
			isSuccess = true;
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	@Override
	public boolean save(PhieuTiepNhan newEntity) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			// find the entity to be deleted
			session.save(newEntity);
			transaction.commit();
			isSuccess = true;
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return this.sessionFactory;
	}

}
