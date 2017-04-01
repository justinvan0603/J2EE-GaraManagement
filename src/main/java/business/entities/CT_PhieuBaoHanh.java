package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ct_phieubh")
public class CT_PhieuBaoHanh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;

	@Column(name = "MaPhieuDV")
	private Integer maPhieuDichVu;

	@Column(name = "MaPhuTung")
	private Integer maPhuTung;

	@Column(name = "MaPhieuBH")
	private Integer maPhieuBaoHanh;

	@Column(name = "MaPhieuBanLe")
	private Integer maPhieuBanLe;

	@Column(name = "NgayHenTra")
	@Temporal(TemporalType.DATE)
	private Date ngayHenTra;

	@Column(name = "NgayTra")
	@Temporal(TemporalType.DATE)
	private Date ngayTra;

	// relationships
	@OneToOne
	@JoinColumn(name = "MaPhuTung", referencedColumnName = "Id", insertable = false, updatable = false)
	private PhuTung phuTung;

	public CT_PhieuBaoHanh() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMaPhieuDichVu() {
		return maPhieuDichVu;
	}

	public void setMaPhieuDichVu(Integer maPhieuDichVu) {
		this.maPhieuDichVu = maPhieuDichVu;
	}

	public Integer getMaPhuTung() {
		return maPhuTung;
	}

	public void setMaPhuTung(Integer maPhuTung) {
		this.maPhuTung = maPhuTung;
	}

	public Integer getMaPhieuBaoHanh() {
		return maPhieuBaoHanh;
	}

	public void setMaPhieuBaoHanh(Integer maPhieuBaoHanh) {
		this.maPhieuBaoHanh = maPhieuBaoHanh;
	}

	public Integer getMaPhieuBanLe() {
		return maPhieuBanLe;
	}

	public void setMaPhieuBanLe(Integer maPhieuBanLe) {
		this.maPhieuBanLe = maPhieuBanLe;
	}

	public Date getNgayHenTra() {
		return ngayHenTra;
	}

	public void setNgayHenTra(Date ngayHenTra) {
		this.ngayHenTra = ngayHenTra;
	}

	public Date getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}

	public PhuTung getPhuTung() {
		return phuTung;
	}

	public void setPhuTung(PhuTung phuTung) {
		this.phuTung = phuTung;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maPhieuBanLe == null) ? 0 : maPhieuBanLe.hashCode());
		result = prime * result + ((maPhieuBaoHanh == null) ? 0 : maPhieuBaoHanh.hashCode());
		result = prime * result + ((maPhieuDichVu == null) ? 0 : maPhieuDichVu.hashCode());
		result = prime * result + ((maPhuTung == null) ? 0 : maPhuTung.hashCode());
		result = prime * result + ((ngayHenTra == null) ? 0 : ngayHenTra.hashCode());
		result = prime * result + ((ngayTra == null) ? 0 : ngayTra.hashCode());
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
		CT_PhieuBaoHanh other = (CT_PhieuBaoHanh) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maPhieuBanLe == null) {
			if (other.maPhieuBanLe != null)
				return false;
		} else if (!maPhieuBanLe.equals(other.maPhieuBanLe))
			return false;
		if (maPhieuBaoHanh == null) {
			if (other.maPhieuBaoHanh != null)
				return false;
		} else if (!maPhieuBaoHanh.equals(other.maPhieuBaoHanh))
			return false;
		if (maPhieuDichVu == null) {
			if (other.maPhieuDichVu != null)
				return false;
		} else if (!maPhieuDichVu.equals(other.maPhieuDichVu))
			return false;
		if (maPhuTung == null) {
			if (other.maPhuTung != null)
				return false;
		} else if (!maPhuTung.equals(other.maPhuTung))
			return false;
		if (ngayHenTra == null) {
			if (other.ngayHenTra != null)
				return false;
		} else if (!ngayHenTra.equals(other.ngayHenTra))
			return false;
		if (ngayTra == null) {
			if (other.ngayTra != null)
				return false;
		} else if (!ngayTra.equals(other.ngayTra))
			return false;
		return true;
	}

}
