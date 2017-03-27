package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.PhuTung;
import business.persistence.GeneralDao;

@Service
public class PhuTungServiceImpl implements GeneralService<PhuTung>{

	@Autowired
	private GeneralDao<PhuTung> phuTungDaoImpl;
	
	@Override
	@Transactional(readOnly = true)
	public PhuTung findById(long id, Class<PhuTung> entityClass) {
		// TODO Auto-generated method stub
		return this.phuTungDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhuTung> getAll(Class<PhuTung> entityClass) {
		// TODO Auto-generated method stub
		return this.phuTungDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhuTung> query(String query, Class<PhuTung> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, PhuTung newInfor) {
		// TODO Auto-generated method stub
		return this.phuTungDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhuTung> entity) {
		// TODO Auto-generated method stub
		return this.phuTungDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhuTung newEntity) {
		// TODO Auto-generated method stub
		return this.phuTungDaoImpl.save(newEntity);
	}

	@SuppressWarnings("unchecked")
	public List<PhuTung> find(String mapt, String tenpt, String hieuxe) {
		Session session = null;
		Transaction transaction = null;
		List<PhuTung> result = null;
		try {
			session = this.phuTungDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhuTung.class);
			if (mapt != null){
				criteria.add(Restrictions.like("MaPhuTung", "%" + mapt + "%"));
			} else if (tenpt != null){
				criteria.add(Restrictions.like("TenPhuTung", "%" + tenpt + "%"));
			} else {
				criteria.add(Restrictions.like("MaHieuXe", "%" + hieuxe + "%"));
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
