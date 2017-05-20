package viewmodels;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.HieuXe;
import business.entities.PhuTung;
import business.service.HieuXeServiceImpl;
import business.service.PhuTungServiceImpl;

public class PhuTungAddViewModel {

	@WireVariable
	private PhuTungServiceImpl phuTungService;
	private HieuXeServiceImpl hieuXeService;
	private List<HieuXe> dsHieuXe;
	
	@Init
	public void init() {
		// get service bean from Spring
		
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		
		this.phuTungService = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.hieuXeService = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		dsHieuXe =  this.hieuXeService.getAll(HieuXe.class);
	}
	
	@Command
	public void saveNewPhuTung(@BindingParam("mapt") String mapt, @BindingParam("tenpt") String tenpt,
			@BindingParam("hieuxe") String hieuxe, @BindingParam("tgbh") String tgbh) {
		// Messagebox.show(name + "-" + phoneNumber + "-" + address + "-" +
		// gender);
		// make sure we have valid param
		if (!mapt.isEmpty() && !tenpt.isEmpty() && !hieuxe.isEmpty() && !tgbh.isEmpty()) {
			PhuTung pt = new PhuTung();
			pt.setMaPhuTung(mapt);
			pt.setTenPhuTung(tenpt);
			pt.setMaHieuXe(hieuxe);
			pt.setHanBaoHanh(Integer.valueOf(tgbh));
			pt.setDonGiaXuat(0.0);
			// start to save
			if (this.phuTungService.save(pt)) {
				Messagebox.show("Lưu thành công");
				Executions.sendRedirect("./PhuTung_DS.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
		} else {
			Messagebox.show("Một số thông tin nhập chưa đúng, vui lòng kiểm tra lại", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
	}

	public List<HieuXe> getDsHieuXe() {
		return dsHieuXe;
	}

	public void setDsHieuXe(List<HieuXe> dsHieuXe) {
		this.dsHieuXe = dsHieuXe;
	}
	
}
