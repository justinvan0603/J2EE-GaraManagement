package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.Xe;

@Repository
public class XeDaoImpl implements GeneralDao<Xe> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Xe findById(long id, Class<Xe> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		Xe result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();

			result = (Xe) session.get(entityClass, id);

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
	public List<Xe> getAll(Class<Xe> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<Xe> result = null;
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
	public List<Xe> query(String query, Class<Xe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, Xe newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Xe xe = (Xe) session.get(Xe.class, id);
			if (xe != null) {
				xe.setDoiXe(newInfor.getDoiXe());
				xe.setHieuXe(newInfor.getHieuXe());
				xe.setHinhThuc(newInfor.getHinhThuc());
				// khach hang
				xe.setNgayTiepNhan(newInfor.getNgayTiepNhan());
				xe.setSoKhung(newInfor.getSoKhung());
				xe.setSoKM(newInfor.getSoKM());
				xe.setSoMay(newInfor.getSoMay());
				xe.setTinhTrang(newInfor.getTinhTrang());
				// start to update
				session.saveOrUpdate(newInfor);
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
	public boolean delete(Long id, Class<Xe> entity) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Xe xe = (Xe) session.get(entity, id);
			if (xe != null) {
				session.delete(xe);
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
	public boolean save(Xe newEntity) {
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
		return this.sessionFactory;
	}

}
