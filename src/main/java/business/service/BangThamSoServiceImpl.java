package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.BangThamSo;

import business.persistence.GeneralDao;

@Service
public class BangThamSoServiceImpl implements GeneralService<BangThamSo> {

	
	@Autowired
	private GeneralDao<BangThamSo> bangThamSoDaoImpl;
	public GeneralDao<BangThamSo> getBangThamSoDaoImpl() {
		return bangThamSoDaoImpl;
	}

	public void setBangThamSoDaoImpl(GeneralDao<BangThamSo> bangThamSoDaoImpl) {
		this.bangThamSoDaoImpl = bangThamSoDaoImpl;
	}

	@Override
	@Transactional
	public BangThamSo findById(long id, Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	public BangThamSo findByIdThamSoString (String id, Class<BangThamSo> entityClass)
	{
		Session session = null;
		Transaction transaction = null;
		BangThamSo result = null;
		try {
			session = this.bangThamSoDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(BangThamSo.class);
			criteria.add(Restrictions.eq("TenThamSo", id));
			result = (BangThamSo) criteria.uniqueResult(); // get result
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
	@Transactional
	public List<BangThamSo> getAll(Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		return this.bangThamSoDaoImpl.getAll(entityClass);
	}
	@Override
	@Transactional
	public boolean update(Long id, BangThamSo newInfor) {
		// TODO Auto-generated method stub
		return this.bangThamSoDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public List<BangThamSo> query(String query, Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Transactional
	public boolean update(String id, BangThamSo newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.bangThamSoDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(newInfor);
			transaction.commit();
			isSuccess = true;
		}
		catch (Exception e) {
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
	@Transactional
	public boolean delete(Long id, Class<BangThamSo> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean save(BangThamSo newEntity) {
		// TODO Auto-generated method stub
		return false;
	}
	@SuppressWarnings("unchecked")
	public List<BangThamSo> findByName(String name) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.bangThamSoDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(BangThamSo.class);
		criteria.add(Restrictions.like("NoiDung", name, MatchMode.ANYWHERE));
		return criteria.list();
	}

}
