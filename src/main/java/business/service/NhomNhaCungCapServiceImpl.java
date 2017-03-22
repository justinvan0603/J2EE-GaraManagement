package business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.NhomNhaCungCap;
import business.persistence.GeneralDao;

@Service
public class NhomNhaCungCapServiceImpl implements GeneralService<NhomNhaCungCap> {

	@Autowired
	private GeneralDao<NhomNhaCungCap> nhomNhaCungCapDaoImpl;
	
	@Override
	@Transactional
	public NhomNhaCungCap findById(long id, Class<NhomNhaCungCap> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<NhomNhaCungCap> getAll(Class<NhomNhaCungCap> entityClass) {
		// TODO Auto-generated method stub
		return this.nhomNhaCungCapDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<NhomNhaCungCap> query(String query, Class<NhomNhaCungCap> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, NhomNhaCungCap newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<NhomNhaCungCap> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean save(NhomNhaCungCap newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

}
