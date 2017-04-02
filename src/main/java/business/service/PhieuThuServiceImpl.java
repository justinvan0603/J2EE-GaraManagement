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

import business.entities.PhieuThu;
import business.persistence.GeneralDao;

@Service
public class PhieuThuServiceImpl implements GeneralService<PhieuThu> {

	@Autowired
	private GeneralDao<PhieuThu> phieuThuDaoImpl;
	
	@Override
	@Transactional
	public PhieuThu findById(long id, Class<PhieuThu> entityClass) {
		return this.phieuThuDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<PhieuThu> getAll(Class<PhieuThu> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuThuDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<PhieuThu> query(String query, Class<PhieuThu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, PhieuThu newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhieuThu> entity) {
		// TODO Auto-generated method stub
		return this.phieuThuDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhieuThu newEntity) {
		// TODO Auto-generated method stub
		return this.phieuThuDaoImpl.save(newEntity);
	}

	@SuppressWarnings("unchecked")
	public List<PhieuThu> find(String maphieu, Date ngaylap, String tennv, String noidungthu) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuThu> result = null;
		try {
			session = this.phieuThuDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuThu.class);
			if (maphieu != null){
				criteria.add(Restrictions.like("MaPhieuThu", maphieu, MatchMode.ANYWHERE));
			} else if (ngaylap != null){
				criteria.add(Restrictions.eq("NgayLap", ngaylap));
			} else if (tennv != null){
				criteria.createAlias("NhanVien", "staff"); // 
				criteria.add(Restrictions.like("staff.HoTen", tennv, MatchMode.ANYWHERE));
			} else {
				criteria.add(Restrictions.like("NoiDung", noidungthu, MatchMode.ANYWHERE));
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
