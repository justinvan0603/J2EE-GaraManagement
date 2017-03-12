package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "khachhang")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaKH")
	int MaKH;
	@Column(name = "HoTen")
	String HoTen;
	@Column(name = "Cmnd")
	String Cmnd;
	@Column(name = "Sdt")
	String Sdt;
	@Column(name = "Diachi")
	String Diachi;
	@Column(name = "SoTienNo")
	Double SoTienNo;
	@Column(name = "GioiTinh")
	boolean GioiTinh;

	public Customer() {
		// TODO Auto-generated method stub

	}

	public int getMaKH() {
		return MaKH;
	}

	public void setMaKH(int maKH) {
		MaKH = maKH;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getCmnd() {
		return Cmnd;
	}

	public void setCmnd(String cmnd) {
		Cmnd = cmnd;
	}

	public String getSdt() {
		return Sdt;
	}

	public void setSdt(String sdt) {
		Sdt = sdt;
	}

	public String getDiachi() {
		return Diachi;
	}

	public void setDiachi(String diachi) {
		Diachi = diachi;
	}

	public Double getSoTienNo() {
		return SoTienNo;
	}

	public void setSoTienNo(Double soTienNo) {
		SoTienNo = soTienNo;
	}

	public boolean isGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

}
