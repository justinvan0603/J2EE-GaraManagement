package business.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.PhieuBaoHanh;
import business.entities.PhieuTiepNhan;
import business.persistence.GeneralDao;

@Service
public class PhieuBaoHanhServiceImpl implements GeneralService<PhieuBaoHanh> {

	@Autowired
	private GeneralDao<PhieuBaoHanh> phieuBaoHanhDaoImpl;

	@Override
	@Transactional(readOnly = true)
	public PhieuBaoHanh findById(long id, Class<PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuBaoHanhDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhieuBaoHanh> getAll(Class<PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuBaoHanhDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhieuBaoHanh> query(String query, Class<PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuBaoHanhDaoImpl.query(query, entityClass);
	}

	@Override
	@Transactional
	public boolean update(Long id, PhieuBaoHanh newInfor) {
		// TODO Auto-generated method stub
		return this.phieuBaoHanhDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhieuBaoHanh> entity) {
		// TODO Auto-generated method stub
		return this.phieuBaoHanhDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhieuBaoHanh newEntity) {
		// TODO Auto-generated method stub
		return this.phieuBaoHanhDaoImpl.save(newEntity);
	}

	// additional methods
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<PhieuBaoHanh> filterByCreationDate(Date value) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuBaoHanh> result = null;
		try {
			session = this.phieuBaoHanhDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuBaoHanh.class);
			criteria.add(Restrictions.eq("ngayLap", value));
			result = criteria.list(); // get all records
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

	/**
	 * Find all receive headers by staff creating (by name)
	 * 
	 * @param staffName
	 *            : name of staff as criteria
	 * @return {@link List} of {@link PhieuTiepNhan}(s) satisfied criteria
	 */
	@SuppressWarnings("unchecked")
	public List<PhieuBaoHanh> filterByCreationStaffName(String staffName) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuBaoHanh> results = null; // returned list
		try {
			session = this.phieuBaoHanhDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuBaoHanh.class);
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
	 * 
	 * @return
	 */
	public Long getLastedId() {
		SessionFactory sessionFactory = this.phieuBaoHanhDaoImpl.getSessionFactory();
		BigInteger result = (BigInteger) sessionFactory.openSession()
				.createSQLQuery("Select count(last_insert_id()) FROM phieubaohanh;").uniqueResult();
		return result.longValue();
	}

}
