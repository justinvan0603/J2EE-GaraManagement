package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.PhieuBaoHanh;

@Repository
public class PhieuBaoHanhDaoImpl implements GeneralDao<PhieuBaoHanh> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PhieuBaoHanh findById(long id, Class<PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		PhieuBaoHanh result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (PhieuBaoHanh) session.get(entityClass, Integer.parseInt(id + ""));
			// start to initialize the inner because we use lazy loading
			// strategy
			// Hibernate.initialize(result.getListOfCT_PhieuBaoHanhs());
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuBaoHanh> getAll(Class<PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<PhieuBaoHanh> results = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(entityClass);
			results = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return results;
	}

	@Override
	public List<PhieuBaoHanh> query(String query, Class<PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, PhieuBaoHanh newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhieuBaoHanh entity = this.findById(id, PhieuBaoHanh.class);
			if (entity != null) {
				entity.setTinhTrang(newInfor.getTinhTrang()); // update tinh
																// trang
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
	public boolean delete(Long id, Class<PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			// find the entity to be deleted
			PhieuBaoHanh entity = this.findById(id, entityClass);
			if (entity != null) {
				session.delete(entity);
			}
			isSuccess = true;
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	@Override
	public boolean save(PhieuBaoHanh newEntity) {
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
