package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PhieuBanXe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuBanXe")
	public Integer idPHieuBanXe;

	@Column(name = "MaPhieuBanXe")
	public String maPhieuBanXe;

	@Column(name = "NgayLap")
	@Temporal(TemporalType.DATE)
	public Date ngayLap;

	@Column(name = "HanChotThanhToan")
	@Temporal(TemporalType.DATE)
	public Date hanChotThanhToan;

	@Column(name = "HanBaoHanh")
	public Integer hanBaoHanh;

	@Column(name = "BienSoXe")
	public String bienSoXe;

	@Column(name = "TriGia")
	public Double triGia;

	@Column(name = "SoTienConLai")
	public Double soTienConLai;

	@Column(name = "MAKH")
	public Integer maKH;

	@Column(name = "MaNV")
	public Integer maNV;

	// relationships

	@ManyToOne
	@JoinColumn(name = "MaNV", referencedColumnName = "MaNV", insertable = false, updatable = false)
	public NhanVien nhanVien;

}
