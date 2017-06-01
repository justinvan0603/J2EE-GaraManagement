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
@Table(name = "khachhang")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaKH")
	Long MaKH;

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
	Boolean GioiTinh;

	public Customer() {
		// TODO Auto-generated method stub

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Cmnd == null) ? 0 : Cmnd.hashCode());
		result = prime * result + ((Diachi == null) ? 0 : Diachi.hashCode());
		result = prime * result + ((GioiTinh == null) ? 0 : GioiTinh.hashCode());
		result = prime * result + ((HoTen == null) ? 0 : HoTen.hashCode());
		result = prime * result + ((MaKH == null) ? 0 : MaKH.hashCode());
		result = prime * result + ((Sdt == null) ? 0 : Sdt.hashCode());
		result = prime * result + ((SoTienNo == null) ? 0 : SoTienNo.hashCode());
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
		Customer other = (Customer) obj;
		if (Cmnd == null) {
			if (other.Cmnd != null)
				return false;
		} else if (!Cmnd.equals(other.Cmnd))
			return false;
		if (Diachi == null) {
			if (other.Diachi != null)
				return false;
		} else if (!Diachi.equals(other.Diachi))
			return false;
		if (GioiTinh == null) {
			if (other.GioiTinh != null)
				return false;
		} else if (!GioiTinh.equals(other.GioiTinh))
			return false;
		if (HoTen == null) {
			if (other.HoTen != null)
				return false;
		} else if (!HoTen.equals(other.HoTen))
			return false;
		if (MaKH == null) {
			if (other.MaKH != null)
				return false;
		} else if (!MaKH.equals(other.MaKH))
			return false;
		if (Sdt == null) {
			if (other.Sdt != null)
				return false;
		} else if (!Sdt.equals(other.Sdt))
			return false;
		if (SoTienNo == null) {
			if (other.SoTienNo != null)
				return false;
		} else if (!SoTienNo.equals(other.SoTienNo))
			return false;
		return true;
	}

	public Long getMaKH() {
		return MaKH;
	}

	public void setMaKH(Long maKH) {
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

	public String getSoTienNoFormated() {
		NumberFormat formatter = new DecimalFormat("##,###,###");
		if (this.SoTienNo != null) {

			return formatter.format(this.SoTienNo);
		} else {
			return formatter.format(0.0D);
		}
	}
}
