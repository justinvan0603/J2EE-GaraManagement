package business.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import business.entities.NhanVien;


@Repository
public class NhanVienDaoImpl implements GeneralDao<NhanVien> {
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public NhanVien findById(long id, Class<NhanVien> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		NhanVien result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();

			result = (NhanVien) session.get(entityClass, id);

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
	@SuppressWarnings("unchecked")
	@Override
	public List<NhanVien> getAll(Class<NhanVien> entityClass) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		List<NhanVien> result = null;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(entityClass);
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

	@Override
	public List<NhanVien> query(String query, Class<NhanVien> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, NhanVien newInfor) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			NhanVien nhanvien = (NhanVien) session.get(NhanVien.class, id);
			if (nhanvien != null) {
				nhanvien.setUsername(newInfor.getUsername());
				nhanvien.setPassword(newInfor.getPassword());
				nhanvien.setMaNhomNguoiDung(newInfor.getMaNhomNguoiDung());
				nhanvien.setDiachi(newInfor.getDiachi());
				nhanvien.setHoTen(newInfor.getHoTen());
				nhanvien.setGioiTinh(newInfor.getGioiTinh());
				nhanvien.setSdt(newInfor.getSdt());
				// start to update
				session.saveOrUpdate(nhanvien);
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

	@Override
	public boolean delete(Long id, Class<NhanVien> entity) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			NhanVien nhanvien = (NhanVien) session.get(entity, id);
			if (nhanvien != null) {
				session.delete(nhanvien);
			}
			transaction.commit();
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

	@Override
	public boolean save(NhanVien newEntity) {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction transaction = null;
		boolean isSuccess = false;
		try {
			session = this.sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(newEntity);
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

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return sessionFactory;
	}

}
