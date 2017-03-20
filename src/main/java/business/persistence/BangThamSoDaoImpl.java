package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.BangThamSo;




@Repository
public class BangThamSoDaoImpl implements GeneralDao<BangThamSo> {

	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public  BangThamSo findByIdString(String id, Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		BangThamSo result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();

			result = (BangThamSo) session.get(entityClass, id);

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
	public List<BangThamSo> getAll(Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<BangThamSo> result = null;
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
	public List<BangThamSo> query(String query, Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, BangThamSo newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			BangThamSo bangThamSo = (BangThamSo) session.get(BangThamSo.class, id);
			if (bangThamSo != null) {
	
				bangThamSo.setNoiDung(newInfor.getNoiDung());
				bangThamSo.setGiaTri(newInfor.getGiaTri());
				// start to update
				session.saveOrUpdate(bangThamSo);
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
	public boolean delete(Long id, Class<BangThamSo> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(BangThamSo newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BangThamSo findById(long id, Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
