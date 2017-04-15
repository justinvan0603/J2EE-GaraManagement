package business.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import business.entities.PhieuBanLe;

import business.persistence.GeneralDao;
@Service
public class PhieuBanLeServiceImpl implements GeneralService<PhieuBanLe> {
	@Autowired
	private GeneralDao<PhieuBanLe> phieuBanLeDaoImpl;
	@Override
	public PhieuBanLe findById(long id, Class<PhieuBanLe> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuBanLeDaoImpl.findById(id, entityClass);
	}

	public GeneralDao<PhieuBanLe> getNhanVienDaoImpl() {
		return phieuBanLeDaoImpl;
	}

	public void setNhanVienDaoImpl(GeneralDao<PhieuBanLe> nhanVienDaoImpl) {
		this.phieuBanLeDaoImpl = nhanVienDaoImpl;
	}

	@Override
	public List<PhieuBanLe> getAll(Class<PhieuBanLe> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuBanLeDaoImpl.getAll(entityClass);
	}

	@Override
	public List<PhieuBanLe> query(String query, Class<PhieuBanLe> entityClass) {
		// TODO Auto-generated method stub
		return this.phieuBanLeDaoImpl.query(query, entityClass);
	}

	@Override
	public boolean update(Long id, PhieuBanLe newInfor) {
		// TODO Auto-generated method stub
		return this.update(id, newInfor);
	}

	@Override
	public boolean delete(Long id, Class<PhieuBanLe> entity) {
		// TODO Auto-generated method stub
		return this.delete(id, entity);
	}

	@Override
	public boolean save(PhieuBanLe newEntity) {
		// TODO Auto-generated method stub
		return this.save(newEntity);
	}
	@SuppressWarnings("unchecked")
	public List<PhieuBanLe> findByMaPhieu(String maphieu) {
		// get Session factory from DAO object to interact with persistence
		// layer
		SessionFactory sessionFactory = this.phieuBanLeDaoImpl.getSessionFactory();
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PhieuBanLe.class);
		
		criteria.add(Restrictions.like("MaPhieuBanLe",  maphieu , MatchMode.ANYWHERE));
		return criteria.list();
	}

}
