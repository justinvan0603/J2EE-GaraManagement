package business.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.NhomNhaCungCap;

@Repository
public class NhomNhaCungCapDaoImpl implements GeneralDao<NhomNhaCungCap>{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public NhomNhaCungCap findById(long id, Class<NhomNhaCungCap> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NhomNhaCungCap> getAll(Class<NhomNhaCungCap> entityClass) {
		List<NhomNhaCungCap> results = new ArrayList<NhomNhaCungCap>();
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
	public List<NhomNhaCungCap> query(String query, Class<NhomNhaCungCap> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, NhomNhaCungCap newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<NhomNhaCungCap> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(NhomNhaCungCap newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return sessionFactory;
	}

}
