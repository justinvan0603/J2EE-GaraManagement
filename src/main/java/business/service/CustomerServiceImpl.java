package business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.Customer;
import business.entities.Mechanic;
import business.persistence.CustomerDaoImpl;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<Customer> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean save(Customer newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

}
