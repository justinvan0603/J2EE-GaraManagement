package business.entities;

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
public class Xe {
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
	public String toString() {
		return "Xe [bienSoXe=" + bienSoXe + ", maKH=" + maKH + ", ngayTiepNhan=" + ngayTiepNhan + ", tinhTrang="
				+ tinhTrang + ", hinhThuc=" + hinhThuc + ", soMay=" + soMay + ", soKhung=" + soKhung + ", doiXe="
				+ doiXe + ", soKM=" + soKM + ", hieuXeReference=" + hieuXeReference + ", hieuXe=" + hieuXe + "]";
	}

}
