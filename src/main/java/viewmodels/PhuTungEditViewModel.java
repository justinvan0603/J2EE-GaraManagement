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

public class PhuTungEditViewModel {

	@WireVariable
	private PhuTungServiceImpl phuTungService;
	private HieuXeServiceImpl hieuXeService;
	private List<HieuXe> dsHieuXe;
	private PhuTung currentPhuTung;
	
	@Init
	public void init() {

		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		
		this.phuTungService = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		long id = ((Long) Sessions.getCurrent().getAttribute(PhuTungDSViewModel.SELECTED_PT_ID)).longValue();
		if (id != 0) {
			this.setCurrentPhuTung(this.phuTungService.findById(id, PhuTung.class));
		}
		this.hieuXeService = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		dsHieuXe =  this.hieuXeService.getAll(HieuXe.class);
	}

	@Command
	public void updatePhuTung(@BindingParam("id") long id, @BindingParam("mapt") String mapt, @BindingParam("tenpt") String tenpt,
			@BindingParam("hieuxe") String hieuxe, @BindingParam("tgbh") String tgbh) {
		// Messagebox.show(name + "-" + phoneNumber + "-" + address + "-" +
		// gender);
		// make sure we have valid param
		if (!mapt.isEmpty() && !tenpt.isEmpty() && !hieuxe.isEmpty() && !tgbh.isEmpty()) {
			PhuTung pt = this.phuTungService.findById(id, PhuTung.class);
			pt.setMaPhuTung(mapt);
			pt.setTenPhuTung(tenpt);
			pt.setMaHieuXe(hieuxe);
			pt.setHanBaoHanh(Integer.valueOf(tgbh));
			// start to save
			if (this.phuTungService.update(id,pt)) {
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
	
	public HieuXeServiceImpl getHieuXeService() {
		return hieuXeService;
	}

	public void setHieuXeService(HieuXeServiceImpl hieuXeService) {
		this.hieuXeService = hieuXeService;
	}

	public List<HieuXe> getDsHieuXe() {
		return dsHieuXe;
	}

	public void setDsHieuXe(List<HieuXe> dsHieuXe) {
		this.dsHieuXe = dsHieuXe;
	}

	public PhuTung getCurrentPhuTung() {
		return currentPhuTung;
	}

	public void setCurrentPhuTung(PhuTung currentPhuTung) {
		this.currentPhuTung = currentPhuTung;
	}
	
	
}
