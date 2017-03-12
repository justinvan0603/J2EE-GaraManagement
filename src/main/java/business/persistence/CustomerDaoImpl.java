package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import business.entities.Customer;

@Repository
public class CustomerDaoImpl implements GeneralDao<Customer> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Customer findById(long id, Class<Customer> entityClass) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(entityClass);
		criteria.add(Restrictions.eq("MaKH", id));
		return (Customer) criteria.list().get(0);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getAll(Class<Customer> entityClass) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.openSession();
		Criteria criteria = session.createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public List<Customer> query(String query, Class<Customer> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, Customer newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<Customer> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Customer newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
