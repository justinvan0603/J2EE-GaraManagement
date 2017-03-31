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

import business.entities.PhieuDatHang;
import business.persistence.GeneralDao;

@Service
public class PhieuDatHangServiceImpl implements GeneralService<PhieuDatHang>{

	@Autowired
	private GeneralDao<PhieuDatHang> phieuDatHangDaoImpl;
	
	@Override
	@Transactional(readOnly = true)
	public PhieuDatHang findById(long id, Class<PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return phieuDatHangDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PhieuDatHang> getAll(Class<PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuDatHangDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<PhieuDatHang> query(String query, Class<PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, PhieuDatHang newInfor) {
		// TODO Auto-generated method stub
		return this.phieuDatHangDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhieuDatHang> entity) {
		// TODO Auto-generated method stub
		return this.phieuDatHangDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhieuDatHang newEntity) {
		// TODO Auto-generated method stub
		return this.phieuDatHangDaoImpl.save(newEntity);
	}
	
	@Transactional
	public long saveAndGetId(PhieuDatHang newEntity) {
		Session session = null;
		Transaction transaction = null;
		long id;
		try {
			session = this.phieuDatHangDaoImpl.getSessionFactory().openSession();
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
	public List<PhieuDatHang> find(String maphieu, String tennv, Date ngaylap, Date ngayhengiao, String tenNCC) {
		Session session = null;
		Transaction transaction = null;
		List<PhieuDatHang> result = null;
		try {
			session = this.phieuDatHangDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(PhieuDatHang.class);
			if (maphieu != null){
				criteria.add(Restrictions.like("MaPhieuDat", "%" + maphieu + "%"));
			} else if (ngaylap != null){
				criteria.add(Restrictions.eq("NgayDat", ngaylap));
			} else if (ngayhengiao != null){
				criteria.add(Restrictions.eq("NgayGiao", ngayhengiao));
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
