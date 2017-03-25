package business.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.NhaCungCap;
import business.entities.NhomNhaCungCap;
import business.persistence.GeneralDao;

@Service
public class NhaCungCapServiceImpl implements GeneralService<NhaCungCap> {

	@Autowired
	private GeneralDao<NhaCungCap> nhaCungCapDaoImpl;
	
	@Override
	@Transactional(readOnly = true)
	public NhaCungCap findById(long id, Class<NhaCungCap> entityClass) {
		return this.nhaCungCapDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NhaCungCap> getAll(Class<NhaCungCap> entityClass) {
		return this.nhaCungCapDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<NhaCungCap> query(String query, Class<NhaCungCap> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, NhaCungCap newInfor) {
		// TODO Auto-generated method stub
		return this.nhaCungCapDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<NhaCungCap> entity) {
		// TODO Auto-generated method stub
		return this.nhaCungCapDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(NhaCungCap newEntity) {
		// TODO Auto-generated method stub
		return this.nhaCungCapDaoImpl.save(newEntity);
	}
	
	@SuppressWarnings("unchecked")
	public List<NhaCungCap> find(String name, String sdt, String diachi, String tennhom) {
		Session session = null;
		Transaction transaction = null;
		List<NhaCungCap> result = null;
		try {
			session = this.nhaCungCapDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(NhaCungCap.class);
			if (name != null){
				criteria.add(Restrictions.like("TenNCC", "%" + name + "%"));
			} else if (sdt != null){
				criteria.add(Restrictions.like("SoDienThoai", "%" + sdt + "%"));
			} else if (diachi != null){
				criteria.add(Restrictions.like("DiaChi", "%" + diachi + "%"));
			} else {
				criteria.add(Restrictions.like("NhomNCC", "%" + tennhom + "%"));
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
	
	@SuppressWarnings({ "unchecked" })
	public List<String> getAllNhomNCC() {
		Session session = null;
		Transaction transaction = null;
		List<NhomNhaCungCap> result = null;
		List<String> listNCC = new ArrayList<String>();
		try {
			session = this.nhaCungCapDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(NhomNhaCungCap.class);
			result = criteria.list();
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
		for (NhomNhaCungCap nhomncc : result) {
			listNCC.add(nhomncc.getTenNhom());
		}
		return listNCC;
	}

}
