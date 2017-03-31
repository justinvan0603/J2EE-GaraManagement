package business.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.PhieuDatHang;
import business.entities.PhieuNhapHang;

@Repository
public class PhieuNhapHangDaoImpl implements GeneralDao<PhieuNhapHang>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public PhieuNhapHang findById(long id, Class<PhieuNhapHang> entityClass) {
		Session session = null;
		Transaction transaction = null;
		PhieuNhapHang result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (PhieuNhapHang) session.get(entityClass, id);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PhieuNhapHang> getAll(Class<PhieuNhapHang> entityClass) {
		List<PhieuNhapHang> results = new ArrayList<PhieuNhapHang>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(entityClass);
			results = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			results = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return results;
	}

	@Override
	public List<PhieuNhapHang> query(String query, Class<PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, PhieuNhapHang newInfor) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhieuNhapHang pnh = (PhieuNhapHang) session.get(PhieuNhapHang.class, id);
			if (pnh != null) {
				pnh.setMaPhieuNhapHang(newInfor.getMaPhieuNhapHang());
				session.saveOrUpdate(pnh);
				isSuccess = true;
				transaction.commit();
			}
		} catch (Exception e) {
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
	public boolean delete(Long id, Class<PhieuNhapHang> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(PhieuNhapHang newEntity) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(newEntity);
			transaction.commit();
			isSuccess = true;
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
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return sessionFactory;
	}

}
