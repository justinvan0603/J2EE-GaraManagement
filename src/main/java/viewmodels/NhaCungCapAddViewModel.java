package viewmodels;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.NhaCungCap;
import business.entities.NhomNhaCungCap;
import business.service.NhaCungCapServiceImpl;
import business.service.NhomNhaCungCapServiceImpl;

public class NhaCungCapAddViewModel {

	@WireVariable
	private NhaCungCapServiceImpl nccService;
	private NhomNhaCungCapServiceImpl nhomNCCService;
	private List<NhomNhaCungCap> nhomNhaCungCap;
	
	@Init
	public void init() {
		// get service bean from Spring
		this.nccService = (NhaCungCapServiceImpl) SpringUtil.getBean("ncc_service");
		this.nhomNCCService = (NhomNhaCungCapServiceImpl) SpringUtil.getBean("nhomncc_service");
		this.setNhomNhaCungCap(this.nhomNCCService.getAll(NhomNhaCungCap.class));
	}
	
	@Command
	public void saveNewNCC(@BindingParam("name") String name, @BindingParam("address") String address,
			@BindingParam("phone_number") String phoneNumber, @BindingParam("type") String type) {
		// Messagebox.show(name + "-" + phoneNumber + "-" + address + "-" +
		// gender);
		// make sure we have valid param
		if (!name.isEmpty() && !address.isEmpty() && !phoneNumber.isEmpty() && !type.isEmpty()) {
			NhaCungCap ncc = new NhaCungCap();
			ncc.setTenNCC(name);
			ncc.setDiaChi(address);
			ncc.setSDT(phoneNumber);
			ncc.setNhomNCC(type);
			// start to save
			if (this.nccService.save(ncc)) {
				Messagebox.show("Lưu thành công");
				Executions.sendRedirect("./NhaCungCap_DS.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
		} else {
			Messagebox.show("Một số thông tin nhập chưa đúng, vui lòng kiểm tra lại", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}

	}

	public List<NhomNhaCungCap> getNhomNhaCungCap() {
		return nhomNhaCungCap;
	}

	public void setNhomNhaCungCap(List<NhomNhaCungCap> nhomNhaCungCap) {
		this.nhomNhaCungCap = nhomNhaCungCap;
	}
	
}
