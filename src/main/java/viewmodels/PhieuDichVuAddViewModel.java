package viewmodels;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuDichVu;
import business.service.PhieuDichVuServiceImpl;



public class PhieuDichVuAddViewModel {
	@WireVariable
	private PhieuDichVuServiceImpl phieuDichVuServiceImpl;
	
	private PhieuDichVu currentPhieuDichVu;
	
	
	
	
	public PhieuDichVu getCurrentPhieuDichVu() {
		return currentPhieuDichVu;
	}
	public void setCurrentPhieuDichVu(PhieuDichVu currentPhieuDichVu) {
		this.currentPhieuDichVu = currentPhieuDichVu;
	}
	@Init
	public void init() {
		this.currentPhieuDichVu = new PhieuDichVu();
		this.phieuDichVuServiceImpl = (PhieuDichVuServiceImpl) SpringUtil.getBean("phieudichvu_service");

		
	}

	public PhieuDichVuServiceImpl getPhieuDichVuServiceImpl() {
		return phieuDichVuServiceImpl;
	}
	public void setPhieuDichVuServiceImpl(PhieuDichVuServiceImpl phieuDichVuServiceImpl) {
		this.phieuDichVuServiceImpl = phieuDichVuServiceImpl;
	}
	@Command
	public void SavePhieuDichVu(@BindingParam("mapdv") String mapdv, @BindingParam("tiencong") double tiencong)
	{
		if (!mapdv.isEmpty() && tiencong >=0) {
			PhieuDichVu phieudichvu = new PhieuDichVu();
			
			phieudichvu.setMaPhieuDichVu(mapdv);
			phieudichvu.setTienCong(tiencong);
			
			if (this.phieuDichVuServiceImpl.save(phieudichvu)) {
				Messagebox.show("Lưu Thành công");
				Executions.sendRedirect("./NhanVien_DS.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
			
		}
		else
		{
			Messagebox.show("Vui lòng nhập đầy đủ thông tin!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}
}
