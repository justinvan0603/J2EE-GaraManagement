package business.persistence;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import business.entities.CT_PhieuDichVu;

@Repository
public class ChiTietPhieuDichVuDaoImpl implements GeneralDao<CT_PhieuDichVu>{
	
	@Override
	public CT_PhieuDichVu findById(long id, Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CT_PhieuDichVu> getAll(Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CT_PhieuDichVu> query(String query, Class<CT_PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, CT_PhieuDichVu newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<CT_PhieuDichVu> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(CT_PhieuDichVu newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SessionFactory getSessionFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
