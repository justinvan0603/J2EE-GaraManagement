package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.Mechanic;
import business.persistence.GeneralDao;

@Service
public class MechanicServiceImpl implements GeneralService<Mechanic> {

	@Autowired
	private GeneralDao<Mechanic> mechanicDaoImpl; // persistence object

	@Override
	@Transactional(readOnly = true)
	public Mechanic findById(long id, Class<Mechanic> entityClass) {
		// TODO Auto-generated method stub
		return this.mechanicDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Mechanic> getAll(Class<Mechanic> entityClass) {
		// TODO Auto-generated method stub
		return this.mechanicDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<Mechanic> query(String query, Class<Mechanic> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, Mechanic newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<Mechanic> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	// additional methods

	@Override
	@Transactional
	public boolean save(Mechanic newEntity) {
		// TODO Auto-generated method stub
		return mechanicDaoImpl.save(newEntity);
	}

	/**
	 * Find Mechanic by Name
	 * 
	 * @param name
	 *            : name of mechanic to be searched
	 * @return : {@link List} of {@link Mechanic}(s) satisfied
	 */
	@SuppressWarnings("unchecked")
	public List<Mechanic> findByName(String name) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.mechanicDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Mechanic.class);
		criteria.add(Restrictions.like("name", "%" + name + "%"));
		return criteria.list();
	}

}
