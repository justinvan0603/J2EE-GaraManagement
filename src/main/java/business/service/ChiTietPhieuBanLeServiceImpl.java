package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import business.entities.CT_PhieuBanLe;

import business.persistence.GeneralDao;

@Service
public class ChiTietPhieuBanLeServiceImpl implements GeneralService<CT_PhieuBanLe> {

	@Autowired
	private GeneralDao<CT_PhieuBanLe> chiTietPhieuBanLeDaoImpl;
	@Override
	public CT_PhieuBanLe findById(long id, Class<CT_PhieuBanLe> entityClass) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuBanLeDaoImpl.findById(id, entityClass);
	}

	@Override
	public List<CT_PhieuBanLe> getAll(Class<CT_PhieuBanLe> entityClass) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuBanLeDaoImpl.getAll(entityClass);
	}

	@Override
	public List<CT_PhieuBanLe> query(String query, Class<CT_PhieuBanLe> entityClass) {
		// TODO Auto-generated method stub
		return this.query(query, entityClass);
	}

	public GeneralDao<CT_PhieuBanLe> getCtPhieuBanLeDaoImpl() {
		return chiTietPhieuBanLeDaoImpl;
	}

	public void setCtPhieuBanLeDaoImpl(GeneralDao<CT_PhieuBanLe> ctPhieuBanLeDaoImpl) {
		this.chiTietPhieuBanLeDaoImpl = ctPhieuBanLeDaoImpl;
	}

	@Override
	public boolean update(Long id, CT_PhieuBanLe newInfor) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuBanLeDaoImpl.update(id, newInfor);
	}

	@Override
	public boolean delete(Long id, Class<CT_PhieuBanLe> entity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuBanLeDaoImpl.delete(id, entity);
	}

	@Override
	public boolean save(CT_PhieuBanLe newEntity) {
		// TODO Auto-generated method stub
		return this.chiTietPhieuBanLeDaoImpl.save(newEntity);
	}
	@SuppressWarnings("unchecked")
	public List<CT_PhieuBanLe> getByIdPhieuBanLe (Long id,Class<CT_PhieuBanLe> entity)
	{
		Session session = null;
		Transaction transaction = null;
		List<CT_PhieuBanLe> result = null;
		try {
			session = this.chiTietPhieuBanLeDaoImpl.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(CT_PhieuBanLe.class);
			criteria.add(Restrictions.eq("IdPhieuBanLe", id));
			result = (List<CT_PhieuBanLe>) criteria.list(); // get result
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

}
