package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phutung")
public class PhuTung {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	int Id;
	@Column(name = "MaPhuTung")
	String MaPhuTung;
	@Column(name = "TenPhuTung")
	String TenPhuTung;
	@Column(name = "MaHieuXe")
	String MaHieuXe;
	@Column(name = "SoLuongTon")
	int SoLuongTon;
	@Column(name = "DonGiaXuat")
	Double DonGiaXuat;
	@Column(name = "HanBaoHanh")
	int HanBaoHanh;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getMaPhuTung() {
		return MaPhuTung;
	}
	public void setMaPhuTung(String maPhuTung) {
		MaPhuTung = maPhuTung;
	}
	public String getTenPhuTung() {
		return TenPhuTung;
	}
	public void setTenPhuTung(String tenPhuTung) {
		TenPhuTung = tenPhuTung;
	}
	public String getMaHieuXe() {
		return MaHieuXe;
	}
	public void setMaHieuXe(String maHieuXe) {
		MaHieuXe = maHieuXe;
	}
	public int getSoLuongTon() {
		return SoLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		SoLuongTon = soLuongTon;
	}
	public Double getDonGiaXuat() {
		return DonGiaXuat;
	}
	public void setDonGiaXuat(Double donGiaXuat) {
		DonGiaXuat = donGiaXuat;
	}
	public int getHanBaoHanh() {
		return HanBaoHanh;
	}
	public void setHanBaoHanh(int hanBaoHanh) {
		HanBaoHanh = hanBaoHanh;
	}
}