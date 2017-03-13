package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phieunhaphang")
public class PhieuNhapHang {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPhieuNhapHang")
	private long IdPhieuNhapHang;

	@Column(name = "MaPhieuNhapHang")
	private String MaPhieuNhapHang;

	@Column(name = "IdPhieuDatHang")
	private int IdPhieuDatHang;

	@Column(name = "NgayLap")
	private Date NgayLap;

	@Column(name = "MaNV")
	private int MaNV;
	
	@Column(name = "TongTien")
	private Double TongTien;
	
	@Column(name = "MaNhaCungCap")
	private int MaNhaCungCap;
}
