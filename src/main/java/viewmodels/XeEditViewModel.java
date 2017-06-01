package viewmodels;

import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.HieuXe;
import business.entities.Xe;
import business.service.HieuXeServiceImpl;
import business.service.XeServiceImpl;

/**
 * View model for vehicle edit view page
 * 
 * @author TNS
 *
 */
public class XeEditViewModel {

	@WireVariable
	private XeServiceImpl xeServiceImpl;
	private HieuXeServiceImpl hieuXeServiceImpl;
	private Xe currentVehicle; // hold the current vehicle information
	private List<HieuXe> listOfHieuXe;
	private HieuXe selectedHieuXe;

	@Init
	public void init() {

		if ((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null)
				|| Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null) {
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}

		this.xeServiceImpl = (XeServiceImpl) SpringUtil.getBean("xe_service");
		this.hieuXeServiceImpl = (HieuXeServiceImpl) SpringUtil.getBean("hieuxe_service");
		// get selected vehicle id from list page
		String vehicelId = (String) Sessions.getCurrent().getAttribute(XeDSViewModel.SELECTED_VEHICLE_ID);
		// make sure session value is valid
		if (vehicelId == null) {
			Executions.sendRedirect("./Xe_DS.zul");
		} else {
			// make sure this is a valid id
			if (!vehicelId.isEmpty()) {
				this.currentVehicle = this.xeServiceImpl.findByLicensePlate(vehicelId);
				this.listOfHieuXe = this.hieuXeServiceImpl.getAll(HieuXe.class);
				this.selectedHieuXe = this.currentVehicle.getHieuXe();
			}
		}
	}

	/**
	 * Update information of a specified vehicle
	 * 
	 * @param inDate
	 * @param maHieuXe
	 * @param soKhung
	 * @param soMay
	 * @param soKm
	 * @param tinhTrang
	 */
	@Command
	public void updateVehicle(@BindingParam("in_date") Date inDate, @BindingParam("hieu_xe") String maHieuXe,
			@BindingParam("so_khung") String soKhung, @BindingParam("so_may") String soMay,
			@BindingParam("so_km") int soKm, @BindingParam("tinh_trang") String tinhTrang,
			@BindingParam("doi_xe") String doiXe) {
		// hold these information into temporary variable
		this.currentVehicle.setNgayTiepNhan(inDate);
		this.currentVehicle.setHieuXeReference(maHieuXe);
		this.currentVehicle.setHieuXe(this.currentVehicle.getHieuXe());
		this.currentVehicle.setSoKhung(soKhung);
		this.currentVehicle.setSoMay(soMay);
		this.currentVehicle.setSoKM(soKm);
		this.currentVehicle.setTinhTrang(tinhTrang);
		this.currentVehicle.setDoiXe(doiXe);
		if (this.xeServiceImpl.update(this.currentVehicle.getBienSoXe(), this.currentVehicle)) {
			Messagebox.show("Thành công");
			Executions.sendRedirect("./Xe_DS.zul"); // redirect to list page
		} else {
			Messagebox.show("Không thành công");
		}

	}

	@Command
	@NotifyChange("selectedHieuXe")
	public void onComboboxVehicleTypeChanges(@BindingParam("hieuxe") String hieuXe) {
		//this.selectedHieuXe = this.hieuXeServiceImpl.findByIdString(hieuXe);
	}

	public Xe getCurrentVehicle() {
		return currentVehicle;
	}

	public void setCurrentVehicle(Xe currentVehicle) {
		this.currentVehicle = currentVehicle;
	}

	public List<HieuXe> getListOfHieuXe() {
		return listOfHieuXe;
	}

	public void setListOfHieuXe(List<HieuXe> listOfHieuXe) {
		this.listOfHieuXe = listOfHieuXe;
	}

	public HieuXe getSelectedHieuXe() {
		return selectedHieuXe;
	}

	public void setSelectedHieuXe(HieuXe selectedHieuXe) {
		this.selectedHieuXe = selectedHieuXe;
	}

}
