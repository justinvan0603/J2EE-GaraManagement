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

import business.entities.Customer;
import business.entities.PhieuBanLe;
import business.service.CustomerServiceImpl;
import business.service.PhieuBanLeServiceImpl;
import business.service.PhieuThuServiceImpl;
import utils.DateUtil;

public class PhieuBanLeDSViewModel {
	@WireVariable
	private PhieuBanLeServiceImpl phieuBanLeServiceImpl;
	@WireVariable
	private PhieuThuServiceImpl phieuThuServiceImpl;
	private PhieuBanLe selectedPhieuBanLe;
	private List<PhieuBanLe> listPhieuBanLe;
	public static String PBL_ID = "PBL_ID";
	@WireVariable
	private CustomerServiceImpl customerServiceImpl;
	private Integer dialogResult;
	public PhieuBanLeServiceImpl getPhieuBanLeServiceImpl() {
		return phieuBanLeServiceImpl;
	}

	public List<PhieuBanLe> getListPhieuBanLe() {
		return listPhieuBanLe;
	}

	public PhieuBanLe getSelectedPhieuBanLe() {
		return selectedPhieuBanLe;
	}

	public void setSelectedPhieuBanLe(PhieuBanLe selectedPhieuBanLe) {
		this.selectedPhieuBanLe = selectedPhieuBanLe;
	}

	public static String getPBL_ID() {
		return PBL_ID;
	}

	public static void setPBL_ID(String pBL_ID) {
		PBL_ID = pBL_ID;
	}

	public void setListPhieuBanLe(List<PhieuBanLe> listPhieuBanLe) {
		this.listPhieuBanLe = listPhieuBanLe;
	}

	public void setPhieuBanLeServiceImpl(PhieuBanLeServiceImpl phieuBanLeServiceImpl) {
		this.phieuBanLeServiceImpl = phieuBanLeServiceImpl;
	}
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {
		
		SEARCH_TYPES.add("Mã phiếu");
		SEARCH_TYPES.add("Ngày lập");
		
	}
	public  List<String> getSearchTypes() {
		return SEARCH_TYPES;
	}
	@Init
	public void init() {
		if((Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERID) == null) || Sessions.getCurrent().getAttribute(LoginViewModel.LOGIN_USERNAME) == null)
		{
			Messagebox.show("Vui lòng đăng nhập!");
			Executions.sendRedirect("./Login.zul");
		}
		this.phieuThuServiceImpl = (PhieuThuServiceImpl) SpringUtil.getBean("phieuthu_service");
		this.phieuBanLeServiceImpl = (PhieuBanLeServiceImpl) SpringUtil.getBean("phieubanle_service");
		this.listPhieuBanLe =  this.phieuBanLeServiceImpl.getAll(PhieuBanLe.class);
		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
	}
	@Command
	@NotifyChange("listPhieuDichVu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listPhieuBanLe = this.phieuBanLeServiceImpl.findByMaPhieu(searchString);
			
		}
		if(selectedIndex == 1)
		{
			this.listPhieuBanLe = this.phieuBanLeServiceImpl.filterByCreationDate(DateUtil.parseFromStringArray(searchString.split("/")));
		}

	}
	@Command 
	public void editPhieuBanLe(@BindingParam("id") Long id)
	{
		if(this.phieuThuServiceImpl.findByIdPhieuCanThu("pbl", id).isEmpty())
		{
			Sessions.getCurrent().setAttribute(PBL_ID, id);
			Executions.sendRedirect("./PhieuBanLe_Edit.zul");
		}
		else
		{
			Messagebox.show("Không thể thay đổi khi đã lập phiếu thu!", "Lỗi", Messagebox.OK,
					Messagebox.ERROR);
		}
		
	}
	@Command 
	@NotifyChange("listPhieuBanLe")
	public void deletePhieuBanLe(@BindingParam("id") final Long id,@BindingParam("makh") final Long makh,@BindingParam("tongtien") final double tongtien)
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
								if(phieuThuServiceImpl.findByIdPhieuCanThu("pbl", id).isEmpty())
								{
									if(phieuBanLeServiceImpl.delete(id, PhieuBanLe.class))
									{
										Customer kh = customerServiceImpl.findById(makh, Customer.class);
										kh.setSoTienNo(kh.getSoTienNo() - tongtien);
										customerServiceImpl.update(kh.getMaKH(), kh);
										listPhieuBanLe.clear();
										setListPhieuBanLe(phieuBanLeServiceImpl.getAll(PhieuBanLe.class));
									}
									else
									{
										Messagebox.show("Đã có lỗi xảy ra!", "Lỗi", Messagebox.OK,
												Messagebox.ERROR);
									}
								}
								else
								{
									Messagebox.show("Không thể xóa khi đã lập phiếu thu!", "Lỗi", Messagebox.OK,
											Messagebox.ERROR);
								}
								break;
							}
						}
					}
			});
//			if(dialogResult != Messagebox.OK)
//			{
//				return;
//			}
		
		
	}
	@Command
	public void ThemPhieuBanLe() {
		Executions.sendRedirect("./PhieuBanLe_Add.zul");
	}
	
	@Command
	public void createPhieuThu(@BindingParam("phieuthu_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(PhieuThuDSViewModel.PDH_ID, id);
		Sessions.getCurrent().setAttribute(PhieuThuDSViewModel.PDH_TYPE, "pbl");
		Executions.sendRedirect("./PhieuThu_Add.zul");
	}
}
