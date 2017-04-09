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

import business.entities.PhieuNhapHang;
import business.persistence.GeneralDao;

@Service
public class PhieuNhapHangServiceImpl implements GeneralService<PhieuNhapHang> {

	@Autowired
	private GeneralDao<PhieuNhapHang> phieuNhapHangDaoImpl;
	
	@Override
	@Transactional(readOnly = true)
	public PhieuNhapHang findById(long id, Class<PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuNhapHangDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhieuNhapHang> getAll(Class<PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuNhapHangDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<PhieuNhapHang> query(String query, Class<PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, PhieuNhapHang newInfor) {
		// TODO Auto-generated method stub
		return this.phieuNhapHangDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhieuNhapHang> entity) {
		// TODO Auto-generated method stub
		return this.phieuNhapHangDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhieuNhapHang newEntity) {
		// TODO Auto-generated method stub
		return this.phieuNhapHangDaoImpl.save(newEntity);
	}
	
	@Transactional
	public long saveAndGetId(PhieuNhapHang newEntity) {
		Session session = null;
		Transaction transaction = null;
		long id;
		try {
			session = this.phieuNhapHangDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			id = (Long) session.save(newEntity);
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			id = -1;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<PhieuNhapHang> find(String maphieu, String tennv, Date ngaylap, String maphieudat, String tenNCC) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuNhapHang> result = null;
		try {
			session = this.phieuNhapHangDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuNhapHang.class);
			if (maphieu != null){
				criteria.add(Restrictions.like("MaPhieuNhapHang", maphieu, MatchMode.ANYWHERE));
			} else if (ngaylap != null){
				criteria.add(Restrictions.eq("NgayLap", ngaylap));
			} else if (maphieudat != null){
				criteria.add(Restrictions.like("IdPhieuDatHang", maphieudat, MatchMode.ANYWHERE));
			} else if (tennv != null){
				criteria.createAlias("NhanVien", "staff"); // 
				criteria.add(Restrictions.like("staff.HoTen", tennv, MatchMode.ANYWHERE));
			} else {
				criteria.createAlias("NhaCungCap", "supplier"); // 
				criteria.add(Restrictions.like("supplier.TenNCC", tenNCC, MatchMode.ANYWHERE));
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
