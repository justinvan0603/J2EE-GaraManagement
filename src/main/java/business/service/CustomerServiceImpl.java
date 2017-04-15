package business.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.Customer;

import business.persistence.GeneralDao;

@Service
public class CustomerServiceImpl implements GeneralService<Customer> {

	@Autowired
	private GeneralDao<Customer> customerDaoImpl;

	@Override
	@Transactional
	public Customer findById(long id, Class<Customer> entityClass) {
		// TODO Auto-generated method stub
		return customerDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<Customer> getAll(Class<Customer> entityClass) {
		// TODO Auto-generated method stub
		return customerDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<Customer> query(String query, Class<Customer> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, Customer newInfor) {
		return this.customerDaoImpl.update(id, newInfor);
		// TODO Auto-generated method stub
		// return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<Customer> entity) {
		// TODO Auto-generated method stub
		try {
			return this.customerDaoImpl.delete(id, entity);
			// return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean save(Customer newEntity) {
		// TODO Auto-generated method stub
		return this.customerDaoImpl.save(newEntity);
		// return false;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findByName(String name) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.customerDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.like("HoTen", name, MatchMode.ANYWHERE));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findByPhone(String phone) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.customerDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.like("Sdt", phone,MatchMode.ANYWHERE));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findByCMND(String CMND) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.customerDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.add(Restrictions.like("Cmnd",  CMND, MatchMode.ANYWHERE ));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findByAddress(String Address) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.customerDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);

		criteria.add(Restrictions.like("Diachi",  Address, MatchMode.ANYWHERE));
		return criteria.list();
	}

	/**
	 * 
	 * @return
	 */
	public Long getLastedId() {
		SessionFactory sessionFactory = this.customerDaoImpl.getSessionFactory();
		BigInteger result = (BigInteger) sessionFactory.openSession()
				.createSQLQuery("Select count(last_insert_id()) FROM khachhang;").uniqueResult();
		return result.longValue();
	}

}
