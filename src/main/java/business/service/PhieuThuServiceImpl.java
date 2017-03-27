package business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.PhieuThu;
import business.persistence.GeneralDao;

@Service
public class PhieuThuServiceImpl implements GeneralService<PhieuThu> {

	@Autowired
	private GeneralDao<PhieuThu> phieuThuDaoImpl;
	
	@Override
	@Transactional
	public PhieuThu findById(long id, Class<PhieuThu> entityClass) {
		return this.phieuThuDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<PhieuThu> getAll(Class<PhieuThu> entityClass) {
		// TODO Auto-generated method stub
		return this.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<PhieuThu> query(String query, Class<PhieuThu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, PhieuThu newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<PhieuThu> entity) {
		// TODO Auto-generated method stub
		return this.phieuThuDaoImpl.delete(id, entity);
	}

	@Override
	@Transactional
	public boolean save(PhieuThu newEntity) {
		// TODO Auto-generated method stub
		return this.phieuThuDaoImpl.save(newEntity);
	}

	
}
