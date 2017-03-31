package viewmodels;

import java.util.Calendar;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.NhanVien;
import business.entities.PhieuThu;
import business.service.NhanVienServiceImpl;
import business.service.PhieuThuServiceImpl;

public class PhieuThuAddViewModel {

	@WireVariable
	private PhieuThuServiceImpl phieuThuService;
	private NhanVienServiceImpl nhanVienService;
//	private PhieuBanLeServiceImpl phieuBanLeService;
//	private PhieuDichVuServiceImpl phieuDichVuService;
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
		
		this.loaiPhieuCanThu = (String) Sessions.getCurrent().getAttribute("loai_phieu_can_thu");
		this.maPhieuCanThu = ((Long) Sessions.getCurrent().getAttribute("id_phieu_can_thu")).longValue();
		//this.maNV = ((Long) Sessions.getCurrent().getAttribute("ma_nv")).longValue();
		this.maNV = 1;
		String noiDungThu = "Thu tiền phiếu ";
		
		if (loaiPhieuCanThu.equals("pbl")){
			this.phieuThu.setIdPhieuBanLe(maPhieuCanThu);
			noiDungThu += "bán lẻ. Mã phiếu: ";
			//get object PBL
			//Assign tongTien + soTienConLai
		} else {
			this.phieuThu.setIdPhieuDichVu(maPhieuCanThu);
			noiDungThu += "dịch vụ. Mã phiếu: ";
			//get object PDV
			//Assign tongTien + soTienConLai
		}
		noiDungThu += String.valueOf(maPhieuCanThu);
		
		phieuThu.setNhanVien(nhanVienService.findById(maNV, NhanVien.class));
		phieuThu.setNgayLap(Calendar.getInstance().getTime());
		phieuThu.setNoiDung(noiDungThu);

	}
	
	@Command
	public void saveNewPhieuThu() {
		
		//update so tien con lai tren cac phieu tuong ung
		if (this.phieuThuService.save(this.phieuThu)) {
			Messagebox.show("Lưu thành công");
			Executions.sendRedirect("./PhieuThu_DS.zul");
		} else {
			Messagebox.show("Đã có lỗi xảy ra", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public double getTienConLai() {
		return tienConLai;
	}

	public void setTienConLai(double tienConLai) {
		this.tienConLai = tienConLai;
	}
}
