package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phieudichvu")
public class PhieuDichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuDichVu")
	private long IdPhieuDichVu;
	@Column(name = "MaPhieuDichVu")
	private String MaPhieuDichVu;
	@Column(name = "NgayLap")
	private Date NgayLap;
	@Column(name = "HanChotThanhToan")
	private Date HanChotThanhToan;
	@Column(name = "MaPhieuTiepNhan")
	private long MaPhieuTiepNhan;
	@Column(name = "TongTien")
	private double TongTien;
	@Column(name = "SoTienConLai")
	private double SoTienConLai;
	@Column(name = "MaTho")
	private long MaTho;
	@Column(name = "MaNV")
	private long MaNV;
	@Column(name = "TienCong")
	private double TienCong;
	public double getTienCong() {
		return TienCong;
	}
	public void setTienCong(double tienCong) {
		TienCong = tienCong;
	}
	public long getIdPhieuDichVu() {
		return IdPhieuDichVu;
	}
	public void setIdPhieuDichVu(long idPhieuDichVu) {
		IdPhieuDichVu = idPhieuDichVu;
	}
	public String getMaPhieuDichVu() {
		return MaPhieuDichVu;
	}
	public void setMaPhieuDichVu(String maPhieuDichVu) {
		MaPhieuDichVu = maPhieuDichVu;
	}
	public Date getNgayLap() {
		return NgayLap;
	}
	public void setNgayLap(Date ngayLap) {
		NgayLap = ngayLap;
	}
	public Date getHanChotThanhToan() {
		return HanChotThanhToan;
	}
	public void setHanChotThanhToan(Date hanChotThanhToan) {
		HanChotThanhToan = hanChotThanhToan;
	}
	public long getMaPhieuTiepNhan() {
		return MaPhieuTiepNhan;
	}
	public void setMaPhieuTiepNhan(long maPhieuTiepNhan) {
		MaPhieuTiepNhan = maPhieuTiepNhan;
	}
	public double getTongTien() {
		return TongTien;
	}
	public void setTongTien(double tongTien) {
		TongTien = tongTien;
	}
	public double getSoTienConLai() {
		return SoTienConLai;
	}
	public void setSoTienConLai(double soTienConLai) {
		SoTienConLai = soTienConLai;
	}
	public long getMaTho() {
		return MaTho;
	}
	public void setMaTho(long maTho) {
		MaTho = maTho;
	}
	public long getMaNV() {
		return MaNV;
	}
	public void setMaNV(long maNV) {
		MaNV = maNV;
	}
	
	
	
}
