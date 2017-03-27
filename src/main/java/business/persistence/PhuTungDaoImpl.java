package business.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.PhuTung;

@Repository
public class PhuTungDaoImpl implements GeneralDao<PhuTung> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public PhuTung findById(long id, Class<PhuTung> entityClass) {
		Session session = null;
		Transaction transaction = null;
		PhuTung result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (PhuTung) session.get(entityClass, id);
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
	public List<PhuTung> getAll(Class<PhuTung> entityClass) {
		List<PhuTung> results = new ArrayList<PhuTung>();
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
	public List<PhuTung> query(String query, Class<PhuTung> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, PhuTung newInfor) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhuTung phutung = (PhuTung) session.get(PhuTung.class, id);
			if (phutung != null) {
				phutung.setMaPhuTung(newInfor.getMaPhuTung());
				phutung.setTenPhuTung(newInfor.getTenPhuTung());
				phutung.setMaHieuXe(newInfor.getMaHieuXe());
				phutung.setHanBaoHanh(newInfor.getHanBaoHanh());
				session.saveOrUpdate(phutung);
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
	public boolean delete(Long id, Class<PhuTung> entity) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhuTung phutung = (PhuTung) session.get(entity, id);
			if (phutung != null) {
				session.delete(phutung);
			}
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
	public boolean save(PhuTung newEntity) {
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
