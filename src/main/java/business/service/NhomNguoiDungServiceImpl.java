package business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import business.entities.NhomNguoiDung;
import business.persistence.GeneralDao;;
@Service
public class NhomNguoiDungServiceImpl implements GeneralService<NhomNguoiDung> {
	@Autowired
	private GeneralDao<NhomNguoiDung> nhomNguoiDungDaoImpl;
	
	public GeneralDao<NhomNguoiDung> getNhomNguoiDungDaoImpl() {
		return nhomNguoiDungDaoImpl;
	}

	public void setNhomNguoiDungDaoImpl(GeneralDao<NhomNguoiDung> nhomNguoiDungDaoImpl) {
		this.nhomNguoiDungDaoImpl = nhomNguoiDungDaoImpl;
	}

	@Override
	@Transactional
	public NhomNguoiDung findById(long id, Class<NhomNguoiDung> entityClass) {
		// TODO Auto-generated method stub
		return this.nhomNguoiDungDaoImpl.findById(id, entityClass);
	}

	@Override
	@Transactional
	public List<NhomNguoiDung> getAll(Class<NhomNguoiDung> entityClass) {
		// TODO Auto-generated method stub
		return this.nhomNguoiDungDaoImpl.getAll(entityClass);
	}

	@Override
	@Transactional
	public List<NhomNguoiDung> query(String query, Class<NhomNguoiDung> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean update(Long id, NhomNguoiDung newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean delete(Long id, Class<NhomNguoiDung> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Transactional
	public boolean save(NhomNguoiDung newEntity) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
