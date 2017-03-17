package business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.entities.HieuXe;
import business.persistence.GeneralDao;

@Service
public class HieuXeServiceImpl implements GeneralService<HieuXe> {

	@Autowired
	private GeneralDao<HieuXe> hieuXeDaoImpl;

	@Override
	public HieuXe findById(long id, Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HieuXe> getAll(Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return this.hieuXeDaoImpl.getAll(entityClass);
	}

	@Override
	public List<HieuXe> query(String query, Class<HieuXe> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, HieuXe newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<HieuXe> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(HieuXe newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

}
