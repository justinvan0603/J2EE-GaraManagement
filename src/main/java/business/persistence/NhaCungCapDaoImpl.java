package business.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.NhaCungCap;

@Repository
public class NhaCungCapDaoImpl implements GeneralDao<NhaCungCap> {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public NhaCungCap findById(long id, Class<NhaCungCap> entityClass) {
		Session session = null;
		Transaction transaction = null;
		NhaCungCap result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (NhaCungCap) session.get(entityClass, id);
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
	public List<NhaCungCap> getAll(Class<NhaCungCap> entityClass) {
		List<NhaCungCap> results = new ArrayList<NhaCungCap>();
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
	public List<NhaCungCap> query(String query, Class<NhaCungCap> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, NhaCungCap newInfor) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			NhaCungCap ncc = (NhaCungCap) session.get(NhaCungCap.class, id);
			if (ncc != null) {
				ncc.setTenNCC(newInfor.getTenNCC());
				ncc.setSDT(newInfor.getSDT());
				ncc.setDiaChi(newInfor.getDiaChi());
				ncc.setNhomNCC(newInfor.getNhomNCC());
				session.saveOrUpdate(ncc);
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
	public boolean delete(Long id, Class<NhaCungCap> entity) {
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			NhaCungCap ncc = (NhaCungCap) session.get(entity, id);
			if (ncc != null) {
				session.delete(ncc);
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
	public boolean save(NhaCungCap newEntity) {
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
		return null;
	}
	
}
