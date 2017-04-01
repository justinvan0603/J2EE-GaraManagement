package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import business.entities.CT_PhieuBaoHanh;
import business.entities.PhieuTiepNhan;

@Repository
public class CTPhieuBaoHanhDaoImpl implements GeneralDao<CT_PhieuBaoHanh> {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CT_PhieuBaoHanh findById(long id, Class<CT_PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		CT_PhieuBaoHanh result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			result = (CT_PhieuBaoHanh) session.get(entityClass, Integer.parseInt(id + ""));
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CT_PhieuBaoHanh> getAll(Class<CT_PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<CT_PhieuBaoHanh> results = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(entityClass);
			results = criteria.list();
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return results;
	}

	@Override
	public List<CT_PhieuBaoHanh> query(String query, Class<CT_PhieuBaoHanh> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, CT_PhieuBaoHanh newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			CT_PhieuBaoHanh entity = this.findById(id, CT_PhieuBaoHanh.class);
			if (entity != null) {
				entity.setNgayHenTra(newInfor.getNgayHenTra());
				entity.setNgayTra(newInfor.getNgayTra());
				// more updates here
				session.saveOrUpdate(entity);
				isSuccess = true;
			} else {
				isSuccess = false;
			}
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	@Override
	public boolean delete(Long id, Class<CT_PhieuBaoHanh> entityClas) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			CT_PhieuBaoHanh ct_PhieuBaoHanh = this.findById(id, entityClas);
			session.delete(ct_PhieuBaoHanh); // start to delete
			isSuccess = true;
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	@Override
	public boolean save(CT_PhieuBaoHanh newEntity) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(newEntity);
			isSuccess = true;
			transaction.commit();
		} catch (Exception e) {
			// TODO: handle exception
			isSuccess = false;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return isSuccess;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return this.sessionFactory;
	}

}
