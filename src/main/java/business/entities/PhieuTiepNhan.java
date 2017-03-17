package business.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utils.StringFormatUtil;

/**
 * Represent information of an Receive Header "phiếu tiếp nhận" from database
 * MaPhieuTiepNhan, MaKH, NgayLap, BienSoXe, MaSoCho, NgayTraXe, TinhTrang, MaNV
 * 
 * @author TNS
 *
 */
@Entity
@Table(name = "phieutiepnhan")
public class PhieuTiepNhan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaPhieuTiepNhan")
	private Long id;

	@Column(name = "MaKH")
	private Long customerId;

	@Column(name = "NgayLap")
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@Column(name = "BienSoXe")
	private String licensePlate;

	@Column(name = "MaSoCho")
	private Integer queueNumber;

	@Column(name = "NgayTraXe")
	@Temporal(TemporalType.DATE)
	private Date givebackDate;

	@Column(name = "TinhTrang")
	private String state;

	@Column(name = "MaNV")
	private Integer staffId;

	// Relationships

	@ManyToOne
	@JoinColumn(name = "MaKH", referencedColumnName = "MaKH", insertable = false, updatable = false)
	private Customer customer; // customer of this receive header

	public PhieuTiepNhan() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Integer getQueueNumber() {
		return queueNumber;
	}

	public void setQueueNumber(Integer queueNumber) {
		this.queueNumber = queueNumber;
	}

	public Date getGivebackDate() {
		return givebackDate;
	}

	public void setGivebackDate(Date givebackDate) {
		this.givebackDate = givebackDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	// additional methods

	/**
	 * Get creation date time in format string 'dd/MM/yyyy'
	 */
	public String getShortCreationDate() {
		return StringFormatUtil.shortDateTime(this.creationDate);
	}

	/**
	 * Get give back date time in format string 'dd/MM/yyyy'
	 * 
	 * @return
	 */
	public String getShortGiveBackDate() {
		return StringFormatUtil.shortDateTime(this.givebackDate);
	}

	@Override
	public String toString() {
		return "ReceiveHeader [id=" + id + ", customerId=" + customerId + ", creationDate=" + creationDate
				+ ", licensePlate=" + licensePlate + ", queueNumber=" + queueNumber + ", givebackDate=" + givebackDate
				+ ", state=" + state + ", staffId=" + staffId + ", customer=" + customer + "]";
	}

}
