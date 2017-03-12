package business.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Information of a mechanic in garage
 * 
 * @author TNS
 *
 */
@Entity
@Table(name = "tho")
public class Mechanic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaTho")
	private long id;

	@Column(name = "TenTho")
	private String name;

	@Column(name = "Sdt")
	private String phone;

	@Column(name = "DiaChi")
	private String address;

	@Column(name = "GioiTinh")
	private Boolean gender;

	public Mechanic() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	/**
	 * Get gender string
	 * 
	 * @return Nam if gender is 1, otherwise, return Nữ
	 */
	public String getGenderString() {
		return this.gender == true ? "Nam" : "Nữ";
	}

}
