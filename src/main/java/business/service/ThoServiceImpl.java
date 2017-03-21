package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		return thoDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<Tho> entity) {
		return thoDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(Tho newEntity) {
		return thoDaoImpl.save(newEntity);
	}

	@SuppressWarnings("unchecked")
	public List<Tho> find(String name, String sdt, String diachi) {
		Session session = null;
		Transaction transaction = null;
		List<Tho> result = null;
		try {
			session = this.thoDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Tho.class);
			if (name != null){
				criteria.add(Restrictions.like("name", "%" + name + "%"));
			} else if (sdt != null){
				criteria.add(Restrictions.like("phone", "%" + sdt + "%"));
			} else {
				criteria.add(Restrictions.like("address", "%" + diachi + "%"));
			}
			result = criteria.list(); // get all records
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}
	
	
}
