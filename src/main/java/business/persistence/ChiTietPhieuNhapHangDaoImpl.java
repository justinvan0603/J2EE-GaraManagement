package business.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.CT_PhieuNhapHang;

@Repository
public class ChiTietPhieuNhapHangDaoImpl implements GeneralDao<CT_PhieuNhapHang> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CT_PhieuNhapHang findById(long id, Class<CT_PhieuNhapHang> entityClass) {
		Session session = null;
		Transaction transaction = null;
		CT_PhieuNhapHang result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (CT_PhieuNhapHang) session.get(entityClass, id);
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

	@Override
	public List<CT_PhieuNhapHang> getAll(Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CT_PhieuNhapHang> query(String query, Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, CT_PhieuNhapHang newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<CT_PhieuNhapHang> entity) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			CT_PhieuNhapHang ct_PhieuNhapHang = this.findById(id, entity);
			session.delete(ct_PhieuNhapHang); // start to delete
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
	public boolean save(CT_PhieuNhapHang newEntity) {
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
