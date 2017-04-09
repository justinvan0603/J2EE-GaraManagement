package business.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhacungcap")
public class NhaCungCap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaNCC")
	private long MaNCC;

	@Column(name = "TenNCC")
	private String TenNCC;

	@Column(name = "DiaChi")
	private String DiaChi;

	@Column(name = "SDT")
	private String SoDienThoai;

	@Column(name = "NhomNCC")
	private String NhomNCC;

	public long getMaNCC() {
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

	public String getNhomNCC() {
		return NhomNCC;
	}

	public void setNhomNCC(String nhomNCC) {
		NhomNCC = nhomNCC;
	}

	public String getSoDienThoai() {
		return SoDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		SoDienThoai = soDienThoai;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (MaNCC ^ (MaNCC >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		if (MaNCC != other.MaNCC)
			return false;
		return true;
	}

}
