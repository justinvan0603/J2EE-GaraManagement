package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.Tho;
import business.persistence.GeneralDao;

@Service
public class ThoServiceImpl implements GeneralService<Tho>{

	@Autowired
	private GeneralDao<Tho> thoDaoImpl;
	
	@Override
	@Transactional(readOnly = true)
	public Tho findById(long id, Class<Tho> entityClass) {
		return this.thoDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tho> getAll(Class<Tho> entityClass) {
		return this.thoDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<Tho> query(String query, Class<Tho> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, Tho newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<Tho> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean save(Tho newEntity) {
		return thoDaoImpl.save(newEntity);
	}

	@SuppressWarnings("unchecked")
	public List<Tho> findByName(String name) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.thoDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tho.class);
		criteria.add(Restrictions.like("name", "%" + name + "%"));
		return criteria.list();
	}
}
