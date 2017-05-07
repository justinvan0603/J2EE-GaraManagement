package viewmodels;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.NhanVien;
import business.entities.PhieuBanLe;
import business.entities.PhieuDichVu;
import business.entities.PhieuThu;
import business.service.NhanVienServiceImpl;
import business.service.PhieuBanLeServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhieuThuServiceImpl;
import utils.SystemParam;

public class PhieuThuAddViewModel {

	@WireVariable
	private PhieuThuServiceImpl phieuThuService;
	private NhanVienServiceImpl nhanVienService;
	private PhieuBanLeServiceImpl phieuBanLeService;
	private PhieuDichVuServiceImpl phieuDichVuService;
	private double tongTien;
	private double tienConLai;
	private PhieuThu phieuThu;
	private String loaiPhieuCanThu;
	private long maPhieuCanThu;
	private long maNV;
	@Init
	public void init() {
		this.phieuThuService = (PhieuThuServiceImpl) SpringUtil.getBean("phieuthu_service");
		this.nhanVienService = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.phieuBanLeService = (PhieuBanLeServiceImpl) SpringUtil.getBean("phieubanle_service");
		this.phieuDichVuService = (PhieuDichVuServiceImpl) SpringUtil.getBean("phieudichvu_service");
		
		this.loaiPhieuCanThu = (String) Sessions.getCurrent().getAttribute(PhieuThuDSViewModel.PDH_TYPE);
		this.maPhieuCanThu = ((Long) Sessions.getCurrent().getAttribute(PhieuThuDSViewModel.PDH_ID)).longValue();
		//this.maNV = ((Long) Sessions.getCurrent().getAttribute("ma_nv")).longValue();
		this.maNV = 1;
		String noiDungThu = "Thu tiền phiếu ";
		this.phieuThu = new PhieuThu();
		maPhieuCanThu = 1;
		
		if (loaiPhieuCanThu.equals("pbl")){
			this.phieuThu.setIdPhieuBanLe(maPhieuCanThu);
			noiDungThu += "bán lẻ. Mã phiếu: ";
			PhieuBanLe pbl = this.phieuBanLeService.findById(maPhieuCanThu, PhieuBanLe.class);
			this.setTongTien(pbl.getTongTien());
			this.setTienConLai(pbl.getSoTienConLai());
			noiDungThu += pbl.getMaPhieuBan();
		} else {
			this.phieuThu.setIdPhieuDichVu(maPhieuCanThu);
			noiDungThu += "dịch vụ. Mã phiếu: ";
			PhieuDichVu pdv = this.phieuDichVuService.findById(maPhieuCanThu, PhieuDichVu.class);
			this.setTongTien(pdv.getTongTien());
			this.setTienConLai(pdv.getSoTienConLai());
			noiDungThu += pdv.getMaPhieuDichVu();
		}
		phieuThu.setMaNV(maNV);
		phieuThu.setNhanVien(nhanVienService.findById(maNV, NhanVien.class));
		phieuThu.setNgayLap(Calendar.getInstance().getTime());
		phieuThu.setNoiDung(noiDungThu);

	}
	
	@Command
	public void saveNewPhieuThu() {
		if (checkValueIsValid()){
			if (this.phieuThuService.save(this.phieuThu)) {
				if (loaiPhieuCanThu.equals("pbl")){
					PhieuBanLe pbl = this.phieuBanLeService.findById(maPhieuCanThu, PhieuBanLe.class);
					pbl.setSoTienConLai(pbl.getSoTienConLai() - phieuThu.getSoTien());
					this.phieuBanLeService.update(pbl.getIdPhieuBanLe(), pbl);
				} else {
					PhieuDichVu pdv = this.phieuDichVuService.findById(maPhieuCanThu, PhieuDichVu.class);
					pdv.setSoTienConLai(pdv.getSoTienConLai() - phieuThu.getSoTien());
					this.phieuDichVuService.update(pdv.getIdPhieuDichVu(), pdv);
				}
				Messagebox.show("Lưu thành công");
				Executions.sendRedirect("./PhieuThu_DS.zul");
			} else {
				Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			}
		}
	}
	
	public boolean checkValueIsValid(){
		Double sotienthutoithieu = Double.valueOf(SystemParam.getValueByKey("SoTienThuToiThieu"));
		
		if (this.phieuThu.getSoTien() > this.getTienConLai()){
			Messagebox.show("Số tiền thu phải bé hơn hoặc bằng số tiền còn lại", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return false;
		} else if (this.phieuThu.getSoTien() < sotienthutoithieu) {
			Messagebox.show("Số tiền thu phải lớn hơn số tiền thu tối thiểu: " + sotienthutoithieu.toString(), "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return false;
		} else if (this.phieuThu.getMaPhieuThu().isEmpty()){
			Messagebox.show("Mã phiếu thu không được để trống", "Lỗi", Messagebox.OK, Messagebox.ERROR);
			return false;
		}
		return true;
	}
	
	@Command
	public void thoat() {
		Executions.sendRedirect("./PhieuThu_DS.zul");
	}
	
	public double getTongTien() {
		return tongTien;
	}
	
	public String getTongTienFormated() {
		NumberFormat formatter = new DecimalFormat("##,###,###");  
		return formatter.format(this.tongTien);
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public double getTienConLai() {
		return tienConLai;
	}
	
	public String getTienConLaiFormated() {
		NumberFormat formatter = new DecimalFormat("##,###,###");  
		return formatter.format(this.tienConLai);
	}

	public void setTienConLai(double tienConLai) {
		this.tienConLai = tienConLai;
	}

	public PhieuThu getPhieuThu() {
		return phieuThu;
	}

	public void setPhieuThu(PhieuThu phieuThu) {
		this.phieuThu = phieuThu;
	}
	
	
}
