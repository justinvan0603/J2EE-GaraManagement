package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.entities.PhieuDichVu;
import business.persistence.GeneralDao;

@Service
public class PhieuDichVuServiceImpl implements GeneralService<PhieuDichVu> {
	@Autowired
	private GeneralDao<PhieuDichVu> phieuDichVuDaoImpl;

	public GeneralDao<PhieuDichVu> getPhieuDichVuDaoImpl() {
		return phieuDichVuDaoImpl;
	}

	public void setPhieuDichVuDaoImpl(GeneralDao<PhieuDichVu> phieuDichVuDaoImpl) {
		this.phieuDichVuDaoImpl = phieuDichVuDaoImpl;
	}

	@Override
	public PhieuDichVu findById(long id, Class<PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuDichVuDaoImpl.findById(id, entityClass);
	}

	@Override
	public List<PhieuDichVu> getAll(Class<PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuDichVuDaoImpl.getAll(entityClass);
	}

	@Override
	public List<PhieuDichVu> query(String query, Class<PhieuDichVu> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Long id, PhieuDichVu newInfor) {
		// TODO Auto-generated method stub
		return this.phieuDichVuDaoImpl.update(id, newInfor);
	}

	@Override
	public boolean delete(Long id, Class<PhieuDichVu> entity) {
		// TODO Auto-generated method stub
		return this.phieuDichVuDaoImpl.delete(id, entity);
	}

	@Override
	public boolean save(PhieuDichVu newEntity) {
		// TODO Auto-generated method stub
		return this.phieuDichVuDaoImpl.save(newEntity);
	}

	@SuppressWarnings("unchecked")
	public List<PhieuDichVu> findByMaPhieu(String maphieu) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.phieuDichVuDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PhieuDichVu.class);
		criteria.add(Restrictions.like("MaPhieuDichVu",  maphieu,MatchMode.ANYWHERE));
		return criteria.list();
	}

}
