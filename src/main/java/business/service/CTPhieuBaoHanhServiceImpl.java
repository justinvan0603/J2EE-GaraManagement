package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.CT_PhieuBaoHanh;
import business.persistence.GeneralDao;

@Service
public class CTPhieuBaoHanhServiceImpl implements GeneralService<CT_PhieuBaoHanh> {

	@Autowired
	private GeneralDao<CT_PhieuBaoHanh> CTPhieuBaoHanhDaoImpl;

	@Override
	@Transactional
	public CT_PhieuBaoHanh findById(long id, Class<CT_PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return this.CTPhieuBaoHanhDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<CT_PhieuBaoHanh> getAll(Class<CT_PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return this.CTPhieuBaoHanhDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<CT_PhieuBaoHanh> query(String query, Class<CT_PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return this.CTPhieuBaoHanhDaoImpl.query(query, entityClass);
	}

	@Override
	@Transactional
	public boolean update(Long id, CT_PhieuBaoHanh newInfor) {
		// TODO Auto-generated method stub
		return this.CTPhieuBaoHanhDaoImpl.update(id, newInfor);
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<CT_PhieuBaoHanh> entity) {
		// TODO Auto-generated method stub
		return this.CTPhieuBaoHanhDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(CT_PhieuBaoHanh newEntity) {
		// TODO Auto-generated method stub
		return this.CTPhieuBaoHanhDaoImpl.save(newEntity);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CT_PhieuBaoHanh> getAllByPhieuBaoHanhId(Integer id) {
		Session session = null;
		Transaction transaction = null;
		List<CT_PhieuBaoHanh> results = null;
		try {
			session = this.CTPhieuBaoHanhDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(CT_PhieuBaoHanh.class);
			criteria.add(Restrictions.eq("maPhieuBaoHanh", id));
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
