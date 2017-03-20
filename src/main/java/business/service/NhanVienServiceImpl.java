package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import business.entities.NhanVien;

import business.persistence.GeneralDao;

@Service
public class NhanVienServiceImpl implements GeneralService<NhanVien> {
	@Autowired
	private GeneralDao<NhanVien> nhanVienDaoImpl;
	@Override
	public NhanVien findById(long id, Class<NhanVien> entityClass) {
		// TODO Auto-generated method stub
		return this.nhanVienDaoImpl.findById(id, entityClass);
	}

	@Override
	public List<NhanVien> getAll(Class<NhanVien> entityClass) {
		// TODO Auto-generated method stub
		return this.nhanVienDaoImpl.getAll(entityClass);
	}

	@Override
	public List<NhanVien> query(String query, Class<NhanVien> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, NhanVien newInfor) {
		// TODO Auto-generated method stub
		return this.update(id, newInfor);
	}

	@Override
	public boolean delete(Long id, Class<NhanVien> entity) {
		// TODO Auto-generated method stub
		return this.nhanVienDaoImpl.delete(id, entity);
	}

	@Override
	public boolean save(NhanVien newEntity) {
		// TODO Auto-generated method stub
		return this.nhanVienDaoImpl.save(newEntity);
	}

	public GeneralDao<NhanVien> getNhanVienDaoImpl() {
		return nhanVienDaoImpl;
	}

	public void setNhanVienDaoImpl(GeneralDao<NhanVien> nhanVienDaoImpl) {
		this.nhanVienDaoImpl = nhanVienDaoImpl;
	}
	@SuppressWarnings("unchecked")
	public List<NhanVien> findByName(String name) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.nhanVienDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NhanVien.class);
		criteria.add(Restrictions.like("HoTen", "%" + name + "%"));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<NhanVien> findByPhone(String phone) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.nhanVienDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NhanVien.class);
		criteria.add(Restrictions.like("Sdt", "%" + phone + "%"));
		
		
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<NhanVien> findByCMND(String CMND) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.nhanVienDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NhanVien.class);
		criteria.add(Restrictions.like("Cmnd", "%" + CMND + "%"));
		
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<NhanVien> findByAddress(String Address) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.nhanVienDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NhanVien.class);
		
		criteria.add(Restrictions.like("Diachi", "%" + Address + "%"));
		return criteria.list();
	}
	@SuppressWarnings("unchecked")
	public List<NhanVien> findByUsername(String Username) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.nhanVienDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(NhanVien.class);
		
		criteria.add(Restrictions.like("Username", "%" + Username + "%"));
		return criteria.list();
	}
}
