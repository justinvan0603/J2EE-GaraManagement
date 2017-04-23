package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.PhieuChi;

@Repository
public class PhieuChiDaoImpl implements GeneralDao<PhieuChi> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PhieuChi findById(long id, Class<PhieuChi> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		PhieuChi result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (PhieuChi) session.get(entityClass, Integer.parseInt(id + ""));
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
	public List<PhieuChi> getAll(Class<PhieuChi> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<PhieuChi> results = null;
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
	public List<PhieuChi> query(String query, Class<PhieuChi> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, PhieuChi newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhieuChi entity = this.findById(id, PhieuChi.class);
			if (entity != null) {
				entity.setGiaTri(newInfor.getGiaTri()); // update gia tri
				entity.setNoiDung(newInfor.getNoiDung()); // update noi dung
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
	public boolean delete(Long id, Class<PhieuChi> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			// find the entity to be deleted
			PhieuChi entity = this.findById(id, entityClass);
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
	public boolean save(PhieuChi newEntity) {
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
