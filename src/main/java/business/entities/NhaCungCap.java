package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaNCC")
	private int MaNCC;
	
	@Column(name = "TenNCC")
	private String TenNCC;
	
	@Column(name = "DiaChi")
	private String DiaChi;
	
	@Column(name = "SDT")
	private String SDT;
	
	@Column(name = "NhomNCC")
	private String NhomNCC;
	
	public int getMaNCC() {
		return MaNCC;
	}
	public void setMaNCC(int maNCC) {
		MaNCC = maNCC;
	}
	public String getTenNCC() {
		return TenNCC;
	}
	public void setTenNCC(String tenNCC) {
		TenNCC = tenNCC;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getNhomNCC() {
		return NhomNCC;
	}
	public void setNhomNCC(String nhomNCC) {
		NhomNCC = nhomNCC;
	}
}
