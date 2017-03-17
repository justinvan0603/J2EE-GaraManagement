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

import business.entities.Xe;
import business.persistence.GeneralDao;

@Service
public class XeServiceImpl implements GeneralService<Xe> {

	@Autowired
	private GeneralDao<Xe> xeDaoImpl;

	@Override
	@Transactional(readOnly = true)
	public Xe findById(long id, Class<Xe> entityClass) {
		// TODO Auto-generated method stub
		return this.xeDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Xe> getAll(Class<Xe> entityClass) {
		// TODO Auto-generated method stub
		return this.xeDaoImpl.getAll(entityClass);
	}

	@Override
	public List<Xe> query(String query, Class<Xe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, Xe newInfor) {
		// TODO Auto-generated method stub
		return this.xeDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<Xe> entity) {
		// TODO Auto-generated method stub
		return this.xeDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(Xe newEntity) {
		// TODO Auto-generated method stub
		return this.xeDaoImpl.save(newEntity);
	}

	// additional methods

	/**
	 * Filter data by vehical types as criteria
	 * 
	 * 
	 * @param vehicalType
	 *            : Type of vehical.<br>
	 *            Specifically, <code>true</code> is for garage vehical.
	 *            <code>false</code> is for customer vehical
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xe> filterByType(Boolean vehicalType) {
		Session session = null;
		Transaction transaction = null;
		List<Xe> result = null;
		try {
			session = this.xeDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Xe.class);
			criteria.add(Restrictions.eq("hinhThuc", vehicalType));
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
	 * 
	 * @param vehicalType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Xe> filterByDate(Date inDate) {
		Session session = null;
		Transaction transaction = null;
		List<Xe> result = null;
		try {
			session = this.xeDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Xe.class);
			criteria.add(Restrictions.eq("ngayTiepNhan", inDate));
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
	 * 
	 * @param lincensePlate
	 * @return
	 */
	public Xe findByLicensePlate(String lincensePlate) {
		Session session = null;
		Transaction transaction = null;
		Xe result = null;
		try {
			session = this.xeDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Xe.class);
			criteria.add(Restrictions.like("bienSoXe", lincensePlate, MatchMode.ANYWHERE));
			result = (Xe) criteria.uniqueResult(); // get result
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
	 * Update by id String
	 * 
	 * @param bienSo
	 * @param newInfor
	 * @return
	 */
	public boolean update(String bienSo, Xe newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.xeDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Xe xe = this.findByLicensePlate(bienSo);
			if (xe != null) {
				xe.setDoiXe(newInfor.getDoiXe());
				xe.setHieuXeReference(newInfor.getHieuXeReference());
				xe.setNgayTiepNhan(newInfor.getNgayTiepNhan());
				xe.setSoKhung(newInfor.getSoKhung());
				xe.setSoKM(newInfor.getSoKM());
				xe.setSoMay(newInfor.getSoMay());
				xe.setTinhTrang(newInfor.getTinhTrang());
				// start to update
				session.update(xe);
			}
			transaction.commit();
			isSuccess = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	public boolean delete(String bienSo) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.xeDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Xe xe = this.findByLicensePlate(bienSo);
			if (xe != null) {
				session.delete(xe);
			}
			transaction.commit();
			isSuccess = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

}
