package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hieuxe")
public class HieuXe {

	@Column(name = "MaHieuXe")
	private String MaHieuXe;

	public String getMaHieuXe() {
		return MaHieuXe;
	}

	public void setMaHieuXe(String maHieuXe) {
		MaHieuXe = maHieuXe;
	}
	
}
