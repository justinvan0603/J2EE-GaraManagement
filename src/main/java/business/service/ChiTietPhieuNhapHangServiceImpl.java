package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.CT_PhieuNhapHang;
import business.persistence.GeneralDao;

@Service
public class ChiTietPhieuNhapHangServiceImpl implements GeneralService<CT_PhieuNhapHang> {

	@Autowired
	private GeneralDao<CT_PhieuNhapHang> chiTietPhieuNhapHangDaoImpl;
	
	@Override
	@Transactional(readOnly = true)
	public CT_PhieuNhapHang findById(long id, Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuNhapHangDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CT_PhieuNhapHang> getAll(Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CT_PhieuNhapHang> query(String query, Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, CT_PhieuNhapHang newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<CT_PhieuNhapHang> entity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuNhapHangDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(CT_PhieuNhapHang newEntity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuNhapHangDaoImpl.save(newEntity);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CT_PhieuNhapHang> getAllByPhieuNhapHangId(long id) {
		Session session = null;
		Transaction transaction = null;
		List<CT_PhieuNhapHang> results = null;
		try {
			session = this.chiTietPhieuNhapHangDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(CT_PhieuNhapHang.class);
			criteria.add(Restrictions.eq("IdPhieuNhapHang", id));
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
