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

	public String getTenVaMaPT() {
		return MaPhuTung + " - " + TenPhuTung;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DonGiaXuat == null) ? 0 : DonGiaXuat.hashCode());
		result = prime * result + HanBaoHanh;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + ((MaHieuXe == null) ? 0 : MaHieuXe.hashCode());
		result = prime * result + ((MaPhuTung == null) ? 0 : MaPhuTung.hashCode());
		result = prime * result + SoLuongTon;
		result = prime * result + ((TenPhuTung == null) ? 0 : TenPhuTung.hashCode());
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
		PhuTung other = (PhuTung) obj;
		if (DonGiaXuat == null) {
			if (other.DonGiaXuat != null)
				return false;
		} else if (!DonGiaXuat.equals(other.DonGiaXuat))
			return false;
		if (HanBaoHanh != other.HanBaoHanh)
			return false;
		if (Id != other.Id)
			return false;
		if (MaHieuXe == null) {
			if (other.MaHieuXe != null)
				return false;
		} else if (!MaHieuXe.equals(other.MaHieuXe))
			return false;
		if (MaPhuTung == null) {
			if (other.MaPhuTung != null)
				return false;
		} else if (!MaPhuTung.equals(other.MaPhuTung))
			return false;
		if (SoLuongTon != other.SoLuongTon)
			return false;
		if (TenPhuTung == null) {
			if (other.TenPhuTung != null)
				return false;
		} else if (!TenPhuTung.equals(other.TenPhuTung))
			return false;
		return true;
	}

	public String getDonGiaXuatFormated() {
		NumberFormat formatter = new DecimalFormat("##,###,###");
		return formatter.format(this.DonGiaXuat);
	}

}
