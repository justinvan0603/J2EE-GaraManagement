package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
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

	@Override
	@Transactional
	public List<BangThamSo> getAll(Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		return this.bangThamSoDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<BangThamSo> query(String query, Class<BangThamSo> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, BangThamSo newInfor) {
		// TODO Auto-generated method stub
		return this.bangThamSoDaoImpl.update(id, newInfor);
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
		criteria.add(Restrictions.like("NoiDung", "%" + name + "%"));
		return criteria.list();
	}

}
