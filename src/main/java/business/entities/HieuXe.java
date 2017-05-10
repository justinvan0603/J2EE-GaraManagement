package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hieuxe")
public class HieuXe {

	@Id
	@Column(name = "MaHieuXe")
	private String MaHieuXe;

	public HieuXe() {
		// TODO Auto-generated constructor stub
	}

	public String getMaHieuXe() {
		return MaHieuXe;
	}

	public void setMaHieuXe(String maHieuXe) {
		MaHieuXe = maHieuXe;
	}

	@Override
	public String toString() {
		return "HieuXe [MaHieuXe=" + MaHieuXe + "]";
	}

}
