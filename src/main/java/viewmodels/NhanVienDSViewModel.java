package viewmodels;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.NhanVien;
import business.entities.NhomNguoiDung;

import business.service.NhanVienServiceImpl;
import business.service.NhomNguoiDungServiceImpl;
import utils.PermissionChecker;;

public class NhanVienDSViewModel {
	@WireVariable
	private NhanVienServiceImpl nhanVienService;
	private NhomNguoiDungServiceImpl nhomNguoiDungService;
	private List<NhanVien> listNhanVien;
	private List<NhomNguoiDung> listNhomNguoiDung;
	
	private Integer dialogResult;

	

	private NhanVien selectedNhanVien;
	public static String SELECTED_STAFF = "staff_id";
	
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {
		
		SEARCH_TYPES.add("Họ tên");
		SEARCH_TYPES.add("Số điện thoại");
		SEARCH_TYPES.add("CMND");
		SEARCH_TYPES.add("Địa chỉ");
		SEARCH_TYPES.add("Username");
	}
	// dung static nhe
	public  List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}

	public NhanVien getSelectedNhanVien() {
		return selectedNhanVien;
	}

	public void setSelectedNhanVien(NhanVien selectedNhanVien) {
		this.selectedNhanVien = selectedNhanVien;
	}

	public NhanVienServiceImpl getNhanVienService() {
		return nhanVienService;
	}

	public void setNhanVienService(NhanVienServiceImpl nhanVienService) {
		this.nhanVienService = nhanVienService;
	}
	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		if(!PermissionChecker.isAdministrator((String)Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_PERMISSION)))
		{
			Messagebox.show("Bạn không có quyền truy cập vào chức năng này!");
			Executions.sendRedirect("./PhieuTiepNhan_DS.zul");
		}
		this.nhanVienService = (NhanVienServiceImpl) SpringUtil.getBean("nhanvien_service");
		this.nhomNguoiDungService = (NhomNguoiDungServiceImpl) SpringUtil.getBean("nhomnguoidung_service");
		this.listNhanVien = this.nhanVienService.getAll(NhanVien.class);
		this.listNhomNguoiDung = this.nhomNguoiDungService.getAll(NhomNguoiDung.class);
	}

	public NhomNguoiDungServiceImpl getNhomNguoiDungService() {
		return nhomNguoiDungService;
	}

	public void setNhomNguoiDungService(NhomNguoiDungServiceImpl nhomNguoiDungService) {
		this.nhomNguoiDungService = nhomNguoiDungService;
	}

	public List<NhanVien> getListNhanVien() {
		return listNhanVien;
	}

	public void setListNhanVien(List<NhanVien> listNhanVien) {
		this.listNhanVien = listNhanVien;
	}

	public List<NhomNguoiDung> getListNhomNguoiDung() {
		return listNhomNguoiDung;
	}

	public void setListNhomNguoiDung(List<NhomNguoiDung> listNhomNguoiDung) {
		this.listNhomNguoiDung = listNhomNguoiDung;
	}
	@Command
	public void addNewStaffRedirect() {
		Executions.sendRedirect("./NhanVien_Add.zul");
	}
	@Command 
	public void editNhanVien(@BindingParam("id") Long id)
	{
		Sessions.getCurrent().setAttribute(SELECTED_STAFF, id);
		Executions.sendRedirect("./NhanVien_Edit.zul");
	}
	@Command
	public void deleteNhanVien(@BindingParam("id")final Long id)
	{
		Messagebox.show("Bạn có chắc muốn xóa dữ liệu đã chọn ? ", "Thông báo", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener<Event>() {

					@Override
					public void onEvent(Event event) throws Exception {
						dialogResult =  ((Integer) event.getData()).intValue();
						switch(dialogResult)
						{
							case Messagebox.OK:
							{
								if(nhanVienService.delete(id, NhanVien.class))
								{
									Messagebox.show("Xóa thành công!");
									Executions.sendRedirect("./NhanVien_DS.zul");
								}
								else
								{
									Messagebox.show("Không thể xóa nhân viên!");
								}
								break;
							}
						}
						
					}
			});
//			if(dialogResult == Messagebox.CANCEL)
//			{
//				return;
//			}
			
	}
	@Command
	@NotifyChange("listNhanVien")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listNhanVien = this.nhanVienService.findByName(searchString);
			
		}
		if (selectedIndex == 1) {
			this.listNhanVien = this.nhanVienService.findByPhone(searchString);
			
		}
		if (selectedIndex == 2) {
			this.listNhanVien = this.nhanVienService.findByCMND(searchString);
			
		}
		if (selectedIndex == 3) {
			this.listNhanVien = this.nhanVienService.findByAddress(searchString);
			
		}
		if (selectedIndex == 4) {
			this.listNhanVien = this.nhanVienService.findByUsername(searchString);
			
		}
	}
}
