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

import business.entities.PhieuTiepNhan;
import business.persistence.GeneralDao;

/**
 * Service to interact to DAO layer for entity {@link PhieuTiepNhan}
 * 
 * @author TNS
 *
 */
@Service
public class PhieuTiepNhanServiceImpl implements GeneralService<PhieuTiepNhan> {

	// DAO object to directly interact to receive header persistence layer
	@Autowired
	private GeneralDao<PhieuTiepNhan> phieuTiepNhanDaoImpl;

	@Override
	@Transactional(readOnly = true)
	public PhieuTiepNhan findById(long id, Class<PhieuTiepNhan> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuTiepNhanDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhieuTiepNhan> getAll(Class<PhieuTiepNhan> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuTiepNhanDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<PhieuTiepNhan> query(String query, Class<PhieuTiepNhan> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuTiepNhanDaoImpl.query(query, entityClass);
	}

	@Override
	@Transactional
	public boolean update(Long id, PhieuTiepNhan newInfor) {
		// TODO Auto-generated method stub
		return this.phieuTiepNhanDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhieuTiepNhan> entity) {
		// TODO Auto-generated method stub
		return this.phieuTiepNhanDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhieuTiepNhan newEntity) {
		// TODO Auto-generated method stub
		return this.phieuTiepNhanDaoImpl.save(newEntity);
	}

	// additional methods

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
	public List<PhieuTiepNhan> findByDate(String dateField, Date value) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuTiepNhan> results = null; // returned list
		try {
			session = this.phieuTiepNhanDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuTiepNhan.class);
			criteria.add(Restrictions.eq(dateField, value));
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

	/**
	 * Find all receive headers by license plate
	 * 
	 * @param value
	 *            : license plate as criteria
	 * @return : {@link List} of {@link PhieuTiepNhan}(s) satisfied criteria
	 */
	@SuppressWarnings("unchecked")
	public List<PhieuTiepNhan> findByLicensePlate(String value) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuTiepNhan> results = null; // returned list
		try {
			session = this.phieuTiepNhanDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuTiepNhan.class);
			criteria.add(Restrictions.like("licensePlate", value, MatchMode.ANYWHERE));
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
