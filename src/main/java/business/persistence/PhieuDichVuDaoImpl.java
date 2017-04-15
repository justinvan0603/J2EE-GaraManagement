package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import business.entities.PhieuDichVu;

@Repository
public class PhieuDichVuDaoImpl implements GeneralDao<PhieuDichVu> {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public PhieuDichVu findById(long id, Class<PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		PhieuDichVu result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();

			result = (PhieuDichVu) session.get(entityClass, id);

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
	public List<PhieuDichVu> getAll(Class<PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<PhieuDichVu> result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(entityClass);
			result = criteria.list(); // get all records
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
	public List<PhieuDichVu> query(String query, Class<PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, PhieuDichVu newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhieuDichVu entity = this.findById(id, PhieuDichVu.class);
			if (entity != null) {
				//entity.setTinhTrang(newInfor.getTinhTrang()); // update tinh
				entity.setMaPhieuDichVu(newInfor.getMaPhieuDichVu());
				entity.setTienCong(newInfor.getTienCong());
				entity.setSoTienConLai(newInfor.getSoTienConLai());
				entity.setTongTien(newInfor.getTongTien());
				//entity.setMaTho(maTho);
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
	public boolean delete(Long id, Class<PhieuDichVu> entity) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			PhieuDichVu pdv = (PhieuDichVu) session.get(entity, id);
			if (pdv != null) {
				session.delete(pdv);
			}
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
	public boolean save(PhieuDichVu newEntity) {
		// TODO Auto-generated method stub
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
