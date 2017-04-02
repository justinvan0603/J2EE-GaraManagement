package business.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.CT_PhieuDatHang;

@Repository
public class ChiTietPhieuDatHangDaoImpl implements GeneralDao<CT_PhieuDatHang> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CT_PhieuDatHang findById(long id, Class<CT_PhieuDatHang> entityClass) {
		Session session = null;
		Transaction transaction = null;
		CT_PhieuDatHang result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (CT_PhieuDatHang) session.get(entityClass, id);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

	@Override
	public List<CT_PhieuDatHang> getAll(Class<CT_PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CT_PhieuDatHang> query(String query, Class<CT_PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, CT_PhieuDatHang newInfor) {
		return false;
	}

	@Override
	public boolean delete(Long id, Class<CT_PhieuDatHang> entity) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			CT_PhieuDatHang ct_PhieuDatHang = this.findById(id, entity);
			session.delete(ct_PhieuDatHang); // start to delete
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
	public boolean save(CT_PhieuDatHang newEntity) {
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
		return sessionFactory;
	}

	
}
