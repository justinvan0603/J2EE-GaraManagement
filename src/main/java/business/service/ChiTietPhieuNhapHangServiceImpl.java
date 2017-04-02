package business.service;

import java.util.List;

import org.springframework.stereotype.Service;

import business.entities.CT_PhieuNhapHang;

@Service
public class ChiTietPhieuNhapHangServiceImpl implements GeneralService<CT_PhieuNhapHang> {

	@Override
	public CT_PhieuNhapHang findById(long id, Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CT_PhieuNhapHang> getAll(Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CT_PhieuNhapHang> query(String query, Class<CT_PhieuNhapHang> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, CT_PhieuNhapHang newInfor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id, Class<CT_PhieuNhapHang> entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(CT_PhieuNhapHang newEntity) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
