package business.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phieuthutien")
public class PhieuThu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuThuTien")
	int IdPhieuThuTien;
	@Column(name = "MaPhieuThu")
	String MaPhieuThu;
	@Column(name = "NgayLap")
	Date NgayLap;
	@Column(name = "IdPhieuDichVu")
	int IdPhieuDichVu;
	@Column(name = "IdPhieuBanXe")
	int IdPhieuBanXe;
	@Column(name = "IdPhieuBanLe")
	int IdPhieuBanLe;
	@Column(name = "NoiDung")
	String NoiDung;
	@Column(name = "SoTien")
	Double SoTien;
	@Column(name = "MaNV")
	int MaNV;
	public int getIdPhieuThuTien() {
		return IdPhieuThuTien;
	}
	public void setIdPhieuThuTien(int idPhieuThuTien) {
		IdPhieuThuTien = idPhieuThuTien;
	}
	public String getMaPhieuThu() {
		return MaPhieuThu;
	}
	public void setMaPhieuThu(String maPhieuThu) {
		MaPhieuThu = maPhieuThu;
	}
	public Date getNgayLap() {
		return NgayLap;
	}
	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
	}
	public int getIdPhieuDichVu() {
		return IdPhieuDichVu;
	}
	public void setIdPhieuDichVu(int idPhieuDichVu) {
		IdPhieuDichVu = idPhieuDichVu;
	}
	public int getIdPhieuBanXe() {
		return IdPhieuBanXe;
	}
	public void setIdPhieuBanXe(int idPhieuBanXe) {
		IdPhieuBanXe = idPhieuBanXe;
	}
	public int getIdPhieuBanLe() {
		return IdPhieuBanLe;
	}
	public void setIdPhieuBanLe(int idPhieuBanLe) {
		IdPhieuBanLe = idPhieuBanLe;
	}
	public String getNoiDung() {
		return NoiDung;
	}
	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	public Double getSoTien() {
		return SoTien;
	}
	public void setSoTien(Double soTien) {
		SoTien = soTien;
	}
	public int getMaNV() {
		return MaNV;
	}
	public void setMaNV(int maNV) {
		MaNV = maNV;
	}
	
	
}
