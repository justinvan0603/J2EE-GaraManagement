package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhanvien")
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaNV")
	
	long MaNV;
	@Column(name = "HoTen")
	String HoTen;
	@Column(name = "Username")
	String Username;
	@Column(name = "Password")
	String Password;
	@Column(name = "Sdt")
	String Sdt;
	@Column(name = "Diachi")
	String Diachi;
	@Column(name = "GioiTinh")
	boolean GioiTinh;
	@Column(name = "MaNhomNguoiDung")
	long MaNhomNguoiDung;
	public long getMaNV() {
		return MaNV;
	}
	public void setMaNV(long maNV) {
		MaNV = maNV;
	}
	public String getHoTen() {
		return HoTen;
	}
	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
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
	public boolean getGioiTinh() {
		return GioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		GioiTinh = gioiTinh;
	}
	public long getMaNhomNguoiDung() {
		return MaNhomNguoiDung;
	}
	public void setMaNhomNguoiDung(long maNhomNguoiDung) {
		MaNhomNguoiDung = maNhomNguoiDung;
	}
	
}
