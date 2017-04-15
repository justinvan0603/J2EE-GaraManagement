package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.CT_PhieuDichVu;

@Repository
public class ChiTietPhieuDichVuDaoImpl implements GeneralDao<CT_PhieuDichVu> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CT_PhieuDichVu findById(long id, Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		CT_PhieuDichVu result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (CT_PhieuDichVu) session.get(entityClass, Integer.parseInt(id + ""));
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
	public List<CT_PhieuDichVu> getAll(Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<CT_PhieuDichVu> results = null;
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
	public List<CT_PhieuDichVu> query(String query, Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, CT_PhieuDichVu newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			CT_PhieuDichVu entity = this.findById(id, CT_PhieuDichVu.class);
			if (entity != null) {
				// entity.set

				// more updates here
				session.saveOrUpdate(entity);
				isSuccess = true;
			} else {
				isSuccess = false;
			}
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
	public boolean delete(Long id, Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			CT_PhieuDichVu ct_PhieuDV = this.findById(id, entityClass);
			session.delete(ct_PhieuDV); // start to delete
			
			transaction.commit();
			isSuccess = true;
		} catch (Exception e) {
			e.printStackTrace();
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
	public boolean save(CT_PhieuDichVu newEntity) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(newEntity);
			isSuccess = true;
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
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
