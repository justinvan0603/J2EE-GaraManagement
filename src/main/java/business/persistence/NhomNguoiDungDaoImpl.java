package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.NhomNguoiDung;
import business.entities.Xe;;
@Repository
public class NhomNguoiDungDaoImpl  implements GeneralDao<NhomNguoiDung>{
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public NhomNguoiDung findById(long id, Class<NhomNguoiDung> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		NhomNguoiDung result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();

			result = (NhomNguoiDung) session.get(entityClass, id);

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
	public List<NhomNguoiDung> getAll(Class<NhomNguoiDung> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<NhomNguoiDung> result = null;
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
	public List<NhomNguoiDung> query(String query, Class<NhomNguoiDung> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, NhomNguoiDung newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<NhomNguoiDung> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(NhomNguoiDung newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return sessionFactory;
	}
	

}
