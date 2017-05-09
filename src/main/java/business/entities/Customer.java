package business.entities;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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

	long MaKH;
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

	public long getMaKH() {
		return MaKH;
	}

	public void setMaKH(long maKH) {
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

	public boolean getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}

	@Override
	public String toString() {
		return "Customer [MaKH=" + MaKH + ", HoTen=" + HoTen + ", Cmnd=" + Cmnd + ", Sdt=" + Sdt + ", Diachi=" + Diachi
				+ ", SoTienNo=" + SoTienNo + ", GioiTinh=" + GioiTinh + "]";
	}
	public String getSoTienNoFormated()
	{
		NumberFormat formatter = new DecimalFormat("##,###,###");  
		return formatter.format(this.SoTienNo);
	}
}
