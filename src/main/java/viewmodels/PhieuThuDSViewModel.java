package viewmodels;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.PhieuBanLe;
import business.entities.PhieuDichVu;
import business.entities.PhieuThu;
import business.service.PhieuBanLeServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhieuThuServiceImpl;
import utils.DateUtil;

public class PhieuThuDSViewModel {

	public static final String PDH_ID = "phieuthu_id";
	public static final String PDH_TYPE = "phieuthu_type";
	private static final String[] SEARCH_TYPES = new String[] {

			"Tất cả", "Mã phiếu", "Ngày lập phiếu", "Nhân viên lập", "Nội dung" };

	@WireVariable
	private PhieuThuServiceImpl phieuThuService;
	private List<PhieuThu> listOfPhieuThu;
	private PhieuBanLeServiceImpl phieuBanLeService;
	private PhieuDichVuServiceImpl phieuDichVuService;

	@Init
	public void init() {
		// get bean from Spring, no need to create new object, Spring framework
		// will manage this bean
		this.phieuThuService = (PhieuThuServiceImpl) SpringUtil.getBean("phieuthu_service");
		if (this.phieuThuService != null) {
			this.listOfPhieuThu = this.phieuThuService.getAll(PhieuThu.class);
		} else {
			throw new NullPointerException("GeneralService is NULL");
		}
	}

	@Command
	@NotifyChange("listOfPhieuThu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		switch (selectedIndex) {
		case 0: // search by id of receive header
			this.listOfPhieuThu = this.phieuThuService.getAll(PhieuThu.class);
			break;
		case 1: // search by id of receive header
			if (!searchString.isEmpty()) {
				this.listOfPhieuThu.clear(); // clear all items
				this.listOfPhieuThu = this.phieuThuService.find(searchString, null, null, null);
			}
			break;
		case 2: //
			if (!searchString.isEmpty()) {
				Date searchDate = null;
				try {
					String[] dateSplit = searchString.split("/");
					searchDate = DateUtil.parseFromStringArray(dateSplit);
					System.out.println(searchString);
				} catch (Exception e) {
					// TODO: handle exception
					Messagebox.show("Vui lòng nhập định dạng ngày với format 'dd/MM/yyyy'", "Lỗi", Messagebox.OK,
							Messagebox.ERROR);
				}
				this.listOfPhieuThu.clear(); // clear all items
				this.listOfPhieuThu = this.phieuThuService.find(null, searchDate, null, null);
			}
			break;
		case 3: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuThu.clear(); // clear all items
				this.listOfPhieuThu = this.phieuThuService.find(null, null, searchString, null);
			}
			break;
		case 4: //
			if (!searchString.isEmpty()) {
				this.listOfPhieuThu.clear(); // clear all items
				this.listOfPhieuThu = this.phieuThuService.find(null, null, null, searchString);
			}
			break;
		default:
			break;
		}
	}

	@Command
	@NotifyChange("listOfPhieuThu")
	public void deletePhieuThu(@BindingParam("phieuthu_id") long id) {
		
		this.phieuBanLeService = (PhieuBanLeServiceImpl) SpringUtil.getBean("phieubanle_service");
		this.phieuDichVuService = (PhieuDichVuServiceImpl) SpringUtil.getBean("phieudichvu_service");
		
		PhieuThu pt = this.phieuThuService.findById(id, PhieuThu.class);
		String loaiPhieuCanThu;
		long maPhieuCanThu;
		if (pt.getIdPhieuBanLe() != null){
			loaiPhieuCanThu = "pbl";
			maPhieuCanThu = pt.getIdPhieuBanLe();
		} else {
			loaiPhieuCanThu = "pdv";
			maPhieuCanThu = pt.getIdPhieuDichVu();
		}
		if (this.phieuThuService.delete(id, PhieuThu.class)) {
			
			if (loaiPhieuCanThu.equals("pbl")) {
				PhieuBanLe pbl = this.phieuBanLeService.findById(maPhieuCanThu, PhieuBanLe.class);
				pbl.setSoTienConLai(pbl.getSoTienConLai() + pt.getSoTien());
				this.phieuBanLeService.update(pbl.getIdPhieuBanLe(), pbl);
			} else {
				PhieuDichVu pdv = this.phieuDichVuService.findById(maPhieuCanThu, PhieuDichVu.class);
				pdv.setSoTienConLai(pdv.getSoTienConLai() + pt.getSoTien());
				this.phieuDichVuService.update(pdv.getIdPhieuDichVu(), pdv);
			}
			this.listOfPhieuThu = this.phieuThuService.getAll(PhieuThu.class);
		} else {
			Messagebox.show("Lỗi khi xoá");
		}
	}

	public String[] getSearchTypes() {
		return SEARCH_TYPES;
	}

	public PhieuThuServiceImpl getPhieuThuService() {
		return phieuThuService;
	}

	public void setPhieuThuService(PhieuThuServiceImpl phieuThuService) {
		this.phieuThuService = phieuThuService;
	}

	public List<PhieuThu> getListOfPhieuThu() {
		return listOfPhieuThu;
	}

	public void setListOfPhieuThu(List<PhieuThu> listOfPhieuThu) {
		this.listOfPhieuThu = listOfPhieuThu;
	}

}
