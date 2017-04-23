package business.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phutung")
public class PhuTung implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	long Id;

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

	public PhuTung() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
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
	
	public String getTenVaMaPT(){
		return MaPhuTung + " - " + TenPhuTung;
	}
	
	public String getDonGiaXuatFormated(){
		NumberFormat formatter = new DecimalFormat("##,###,###");  
		return formatter.format(this.DonGiaXuat);
	}
	
}
