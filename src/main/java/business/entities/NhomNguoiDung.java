package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhomnguoidung")
public class NhomNguoiDung {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaNhomNguoiDung")
	
	private long MaNhomNguoiDung;
	@Column(name = "TenNhom")
	private String TenNhom;
	@Column(name = "CapDo")
	private long CapDo;
	public long getMaNhomNguoiDung() {
		return MaNhomNguoiDung;
	}
	public void setMaNhomNguoiDung(long maNhomNguoiDung) {
		MaNhomNguoiDung = maNhomNguoiDung;
	}
	public String getTenNhom() {
		return TenNhom;
	}
	public void setTenNhom(String tenNhom) {
		TenNhom = tenNhom;
	}
	public long getCapDo() {
		return CapDo;
	}
	public void setCapDo(long capDo) {
		CapDo = capDo;
	}
}
