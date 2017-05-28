package business.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utils.StringFormatUtil;

/**
 * Model Verhical
 * 
 * BienSoXe, MaKH, NgayTiepNhan, HieuXe, TinhTrang, HinhThuc, SoMay, SoKhung,
 * DoiXe, SoKM
 * 
 * @author TNS
 *
 */
@Entity
@Table(name = "xe")
public class Xe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "BienSoXe")
	private String bienSoXe;

	@Column(name = "MAKH")
	private Integer maKH; // ma khach hang

	@Column(name = "NgayTiepNhan")
	@Temporal(TemporalType.DATE)
	private Date ngayTiepNhan;

	@Column(name = "TinhTrang")
	private String tinhTrang;

	@Column(name = "HinhThuc")
	private Boolean hinhThuc;

	@Column(name = "SoMay")
	private String soMay;

	@Column(name = "SoKhung")
	private String soKhung;

	@Column(name = "DoiXe")
	private String doiXe;

	@Column(name = "SoKM")
	private Integer soKM;

	@Column(name = "HieuXe")
	private String hieuXeReference;

	// relationship entities
	@ManyToOne
	@JoinColumn(name = "MaKH", referencedColumnName = "MaKH", insertable = false, updatable = false)
	private Customer customer; // owner of this car

	// default constructor is required
	public Xe() {
		// TODO Auto-generated constructor stub
	}

	// relationships
	@ManyToOne
	@JoinColumn(name = "HieuXe", insertable = false, updatable = false)
	private HieuXe hieuXe;

	public String getBienSoXe() {
		return bienSoXe;
	}

	public void setBienSoXe(String bienSoXe) {
		this.bienSoXe = bienSoXe;
	}

	public Integer getMaKH() {
		return maKH;
	}

	public void setMaKH(Integer maKH) {
		this.maKH = maKH;
	}

	public Date getNgayTiepNhan() {
		return ngayTiepNhan;
	}

	public void setNgayTiepNhan(Date ngayTiepNhan) {
		this.ngayTiepNhan = ngayTiepNhan;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public Boolean getHinhThuc() {
		return hinhThuc;
	}

	public void setHinhThuc(Boolean hinhThuc) {
		this.hinhThuc = hinhThuc;
	}

	public String getSoMay() {
		return soMay;
	}

	public void setSoMay(String soMay) {
		this.soMay = soMay;
	}

	public String getSoKhung() {
		return soKhung;
	}

	public String getHieuXeReference() {
		return hieuXeReference;
	}

	public void setHieuXeReference(String hieuXeReference) {
		this.hieuXeReference = hieuXeReference;
	}

	public void setSoKhung(String soKhung) {
		this.soKhung = soKhung;
	}

	public String getDoiXe() {
		return doiXe;
	}

	public void setDoiXe(String doiXe) {
		this.doiXe = doiXe;
	}

	public Integer getSoKM() {
		return soKM;
	}

	public void setSoKM(Integer soKM) {
		this.soKM = soKM;
	}

	public HieuXe getHieuXe() {
		return hieuXe;
	}

	public void setHieuXe(HieuXe hieuXe) {
		this.hieuXe = hieuXe;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Get type of vehicle in {@link String}
	 * 
	 * @return
	 */
	public String getTypeString() {
		// make sure value is valid
		if (this.hinhThuc == null) {
			return "Xe Gara";
		}
		return this.hinhThuc == true ? "Xe Gara" : "Xe sửa";
	}

	/**
	 * Get in {@link Date} in 'dd/MM/yyyy' String format from
	 * {@link StringFormatUtil} class
	 * 
	 * @return : see {@link StringFormatUtil#shortDateTime(Date)} for more
	 *         detail
	 */
	public String getInDateStringFormat() {
		return StringFormatUtil.shortDateTime(this.ngayTiepNhan);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bienSoXe == null) ? 0 : bienSoXe.hashCode());
		result = prime * result + ((doiXe == null) ? 0 : doiXe.hashCode());
		result = prime * result + ((hieuXeReference == null) ? 0 : hieuXeReference.hashCode());
		result = prime * result + ((hinhThuc == null) ? 0 : hinhThuc.hashCode());
		result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
		result = prime * result + ((ngayTiepNhan == null) ? 0 : ngayTiepNhan.hashCode());
		result = prime * result + ((soKM == null) ? 0 : soKM.hashCode());
		result = prime * result + ((soKhung == null) ? 0 : soKhung.hashCode());
		result = prime * result + ((soMay == null) ? 0 : soMay.hashCode());
		result = prime * result + ((tinhTrang == null) ? 0 : tinhTrang.hashCode());
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
		Xe other = (Xe) obj;
		if (bienSoXe == null) {
			if (other.bienSoXe != null)
				return false;
		} else if (!bienSoXe.equals(other.bienSoXe))
			return false;
		if (doiXe == null) {
			if (other.doiXe != null)
				return false;
		} else if (!doiXe.equals(other.doiXe))
			return false;
		if (hieuXeReference == null) {
			if (other.hieuXeReference != null)
				return false;
		} else if (!hieuXeReference.equals(other.hieuXeReference))
			return false;
		if (hinhThuc == null) {
			if (other.hinhThuc != null)
				return false;
		} else if (!hinhThuc.equals(other.hinhThuc))
			return false;
		if (maKH == null) {
			if (other.maKH != null)
				return false;
		} else if (!maKH.equals(other.maKH))
			return false;
		if (ngayTiepNhan == null) {
			if (other.ngayTiepNhan != null)
				return false;
		} else if (!ngayTiepNhan.equals(other.ngayTiepNhan))
			return false;
		if (soKM == null) {
			if (other.soKM != null)
				return false;
		} else if (!soKM.equals(other.soKM))
			return false;
		if (soKhung == null) {
			if (other.soKhung != null)
				return false;
		} else if (!soKhung.equals(other.soKhung))
			return false;
		if (soMay == null) {
			if (other.soMay != null)
				return false;
		} else if (!soMay.equals(other.soMay))
			return false;
		if (tinhTrang == null) {
			if (other.tinhTrang != null)
				return false;
		} else if (!tinhTrang.equals(other.tinhTrang))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Xe [bienSoXe=" + bienSoXe + ", maKH=" + maKH + ", ngayTiepNhan=" + ngayTiepNhan + ", tinhTrang="
				+ tinhTrang + ", hinhThuc=" + hinhThuc + ", soMay=" + soMay + ", soKhung=" + soKhung + ", doiXe="
				+ doiXe + ", soKM=" + soKM + ", hieuXeReference=" + hieuXeReference + ", hieuXe=" + hieuXe + "]";
	}

}
