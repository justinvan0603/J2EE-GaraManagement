package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.HieuXe;
import business.persistence.GeneralDao;

@Service
public class HieuXeServiceImpl implements GeneralService<HieuXe> {

	@Autowired
	private GeneralDao<HieuXe> hieuXeDaoImpl;

	@Override
	@Transactional
	public HieuXe findById(long id, Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<HieuXe> getAll(Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return this.hieuXeDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<HieuXe> query(String query, Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, HieuXe newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<HieuXe> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean save(HieuXe newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional
	public HieuXe findByIdString(String hieuXe) {
		Session session = null;
		Transaction transaction = null;
		HieuXe result = null;
		try {
			session = this.hieuXeDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(HieuXe.class);
			criteria.add(Restrictions.like("MaHieuXe", hieuXe, MatchMode.ANYWHERE));
			result = (HieuXe) criteria.uniqueResult(); // get result
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return result;
	}

}
