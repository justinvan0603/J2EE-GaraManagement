package business.persistence;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.HieuXe;

@Repository
public class HieuXeDaoImpl implements GeneralDao<HieuXe> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public HieuXe findById(long id, Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HieuXe> getAll(Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		List<HieuXe> results = new ArrayList<HieuXe>();
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(entityClass);
			results = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session.isOpen()) {
				session.close();
			}
		}

		return results;
	}

	@Override
	public List<HieuXe> query(String query, Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, HieuXe newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<HieuXe> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(HieuXe newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
