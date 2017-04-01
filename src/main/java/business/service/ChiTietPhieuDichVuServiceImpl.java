package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.CT_PhieuDichVu;
import business.persistence.GeneralDao;

@Service
public class ChiTietPhieuDichVuServiceImpl implements GeneralService<CT_PhieuDichVu> {

	@Autowired
	private GeneralDao<CT_PhieuDichVu> chiTietPhieuDichVuDaoImpl;

	@Override
	@Transactional
	public CT_PhieuDichVu findById(long id, Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDichVuDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<CT_PhieuDichVu> getAll(Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDichVuDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<CT_PhieuDichVu> query(String query, Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDichVuDaoImpl.query(query, entityClass);
	}

	@Override
	@Transactional
	public boolean update(Long id, CT_PhieuDichVu newInfor) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDichVuDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<CT_PhieuDichVu> entity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDichVuDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(CT_PhieuDichVu newEntity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuDichVuDaoImpl.save(newEntity);
	}

	@Transactional
	public List<CT_PhieuDichVu> getByPhieuDichVuId(Integer phieuDichVuId) {
		Session session = null;
		Transaction transaction = null;
		List<CT_PhieuDichVu> results = null;
		try {
			session = this.chiTietPhieuDichVuDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(CT_PhieuDichVu.class);
			criteria.add(Restrictions.eq("IdPhieuDichVu", phieuDichVuId));
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
