package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.CT_PhieuDatHang;
import business.persistence.GeneralDao;

@Service
public class ChiTietPhieuDatHangServiceImpl implements GeneralService<CT_PhieuDatHang> {

	@Autowired
	private GeneralDao<CT_PhieuDatHang> chiTietPhieuDatHangDaoImpl;
	
	@Override
	@Transactional(readOnly = true)
	public CT_PhieuDatHang findById(long id, Class<CT_PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDatHangDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<CT_PhieuDatHang> getAll(Class<CT_PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<CT_PhieuDatHang> query(String query, Class<CT_PhieuDatHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, CT_PhieuDatHang newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<CT_PhieuDatHang> entity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDatHangDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(CT_PhieuDatHang newEntity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDatHangDaoImpl.save(newEntity);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CT_PhieuDatHang> getAllByPhieuDatHangId(long id) {
		Session session = null;
		Transaction transaction = null;
		List<CT_PhieuDatHang> results = null;
		try {
			session = this.chiTietPhieuDatHangDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(CT_PhieuDatHang.class);
			criteria.add(Restrictions.eq("IdPhieuDatHang", id));
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
