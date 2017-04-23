package business.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.PhieuChi;
import business.entities.PhieuTiepNhan;
import business.persistence.GeneralDao;

@Service
public class PhieuChiServiceImpl implements GeneralService<PhieuChi> {

	@Autowired
	private GeneralDao<PhieuChi> phieuChiDaoImpl;

	@Override
	@Transactional
	public PhieuChi findById(long id, Class<PhieuChi> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuChiDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<PhieuChi> getAll(Class<PhieuChi> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuChiDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<PhieuChi> query(String query, Class<PhieuChi> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuChiDaoImpl.query(query, entityClass);
	}

	@Override
	@Transactional
	public boolean update(Long id, PhieuChi newInfor) {
		// TODO Auto-generated method stub
		return this.phieuChiDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhieuChi> entity) {
		// TODO Auto-generated method stub
		return this.phieuChiDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhieuChi newEntity) {
		// TODO Auto-generated method stub
		return this.phieuChiDaoImpl.save(newEntity);
	}

	/**
	 * Find all receive headers by staff creating (by name)
	 * 
	 * @param staffName
	 *            : name of staff as criteria
	 * @return {@link List} of {@link PhieuTiepNhan}(s) satisfied criteria
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PhieuChi> filterByCreationStaffName(String staffName) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuChi> results = null; // returned list
		try {
			session = this.phieuChiDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuChi.class);
			criteria.createAlias("nhanVien", "staff"); //
			criteria.add(Restrictions.like("staff.HoTen", staffName, MatchMode.ANYWHERE));
			results = criteria.list(); // get result
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return results;
	}

	/**
	 * Find all receive headers by creation date
	 * 
	 * @param creationDate
	 *            : creation date as criteria
	 * @param dateField
	 *            : The value must be 'creationDate' or 'givebackDate'
	 * @return : {@link List} of {@link PhieuTiepNhan}(s) satisfied criteria
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<PhieuChi> findByCreationDate(Date value) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuChi> results = null; // returned list
		try {
			session = this.phieuChiDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuChi.class);
			criteria.add(Restrictions.eq("ngayLap", value));
			results = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return results;
	}

}
