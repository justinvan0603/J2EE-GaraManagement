package viewmodels;

import java.util.ArrayList;
import java.util.Iterator;
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

import business.entities.CT_PhieuDichVu;
import business.entities.Customer;
import business.entities.PhieuDichVu;
import business.entities.PhieuTiepNhan;
import business.entities.PhuTung;
import business.service.ChiTietPhieuDichVuServiceImpl;
import business.service.CustomerServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhieuThuServiceImpl;
import business.service.PhieuTiepNhanServiceImpl;
import business.service.PhuTungServiceImpl;
import utils.DateUtil;

public class PhieuDichVuDSViewModel {
	@WireVariable
	private PhieuDichVuServiceImpl phieuDichVuService;
	@WireVariable
	private PhieuThuServiceImpl phieuThuServiceImpl;
	private List<PhieuDichVu> listPhieuDichVu;
	private PhieuDichVu selectedPDV;
	@WireVariable
	private ChiTietPhieuDichVuServiceImpl chiTietPhieuDichVuServiceImpl;
	@WireVariable
	private PhuTungServiceImpl phuTungServiceImpl;
	@WireVariable
	private CustomerServiceImpl customerServiceImpl;
	@WireVariable
	private PhieuTiepNhanServiceImpl phieuTiepNhanServiceImpl;

	private Integer dialogResult;
	public Integer getDialogResult() {
		return dialogResult;
	}
	public void setDialogResult(Integer dialogResult) {
		this.dialogResult = dialogResult;
	}

	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {
		
		SEARCH_TYPES.add("Mã phiếu");
		SEARCH_TYPES.add("Ngày lập");
		
	}
	public static String PDV_ID = "PDV_ID";
	
	public PhieuDichVu getSelectedPDV() {
		return selectedPDV;
	}
	public void setSelectedPDV(PhieuDichVu selectedPDV) {
		this.selectedPDV = selectedPDV;
	}
	public PhieuDichVuServiceImpl getPhieuDichVuService() {
		return phieuDichVuService;
	}
	public void setPhieuDichVuService(PhieuDichVuServiceImpl phieuDichVuService) {
		this.phieuDichVuService = phieuDichVuService;
	}
	public List<PhieuDichVu> getListPhieuDichVu() {
		return listPhieuDichVu;
	}
	public void setListPhieuDichVu(List<PhieuDichVu> listPhieuDichVu) {
		this.listPhieuDichVu = listPhieuDichVu;
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
		this.phuTungServiceImpl = (PhuTungServiceImpl) SpringUtil.getBean("phutung_service");
		this.chiTietPhieuDichVuServiceImpl = (ChiTietPhieuDichVuServiceImpl) SpringUtil.getBean("chitietphieudichvu_service");
		this.phieuThuServiceImpl = (PhieuThuServiceImpl)SpringUtil.getBean("phieuthu_service");
		this.phieuDichVuService = (PhieuDichVuServiceImpl)SpringUtil.getBean("phieudichvu_service");
		this.listPhieuDichVu = this.phieuDichVuService.getAll(PhieuDichVu.class);
		this.customerServiceImpl = (CustomerServiceImpl) SpringUtil.getBean("customer_service");
		this.phieuTiepNhanServiceImpl = (PhieuTiepNhanServiceImpl) SpringUtil.getBean("phieutiepnhan_service");
	}
	@Command
	@NotifyChange("listPhieuDichVu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listPhieuDichVu = this.phieuDichVuService.findByMaPhieu(searchString);
			
		}
		if(selectedIndex == 1)
		{
			this.listPhieuDichVu = this.phieuDichVuService.filterByCreationDate(DateUtil.parseFromStringArray(searchString.split("/")));
		}
//		if (selectedIndex == 1) {
//			this.listNhanVien = this.nhanVienService.findByPhone(searchString);
//			
//		}
//		if (selectedIndex == 2) {
//			this.listNhanVien = this.nhanVienService.findByCMND(searchString);
//			
//		}
//		if (selectedIndex == 3) {
//			this.listNhanVien = this.nhanVienService.findByAddress(searchString);
//			
//		}
//		if (selectedIndex == 4) {
//			this.listNhanVien = this.nhanVienService.findByUsername(searchString);
//			
//		}
	}
	@Command 
	public void editPhieuDichVu(@BindingParam("id") Long id)
	{
		if(this.phieuThuServiceImpl.findByIdPhieuCanThu("pdv", id).size() == 0)
		{
			Sessions.getCurrent().setAttribute(PDV_ID, id);
			Executions.sendRedirect("./PhieuDichVu_Edit.zul");
		}
		else
		{
			Messagebox.show("Không thể thay đổi phiếu đã lập phiếu thu", "Lỗi", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Command 
	@NotifyChange("listPhieuDichVu")
	public void deletePhieuDichVu(@BindingParam("id")final Long id,@BindingParam("ptn") final Long ptn,@BindingParam("tongtien") final Long tongtien)
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
							if(phieuThuServiceImpl.findByIdPhieuCanThu("pdv", id).size() == 0)
							{
								PhieuTiepNhan phieutiepnhan = phieuTiepNhanServiceImpl.findById(ptn, PhieuTiepNhan.class);
								List<CT_PhieuDichVu> listCt = chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(id);
								if(!listCt.isEmpty())
								{ 
									for(Iterator<CT_PhieuDichVu> i = listCt.iterator(); i.hasNext();)
									{
										CT_PhieuDichVu ctPhieu = i.next();
										PhuTung pt = phuTungServiceImpl.findById(ctPhieu.getMaPhuTung(), PhuTung.class);
										pt.setSoLuongTon(pt.getSoLuongTon() + ctPhieu.getSoLuong());
										phuTungServiceImpl.update(pt.getId(), pt);
										chiTietPhieuDichVuServiceImpl.delete((long)ctPhieu.getId(), CT_PhieuDichVu.class);
									}
								}
								if(phieuDichVuService.delete(id, PhieuDichVu.class))
								{
									
									
									Customer kh = customerServiceImpl.findById(phieutiepnhan.getCustomerId(), Customer.class);
									kh.setSoTienNo(kh.getSoTienNo() - tongtien);
									customerServiceImpl.update(kh.getMaKH(), kh);
									listPhieuDichVu.clear();
									setListPhieuDichVu( phieuDichVuService.getAll(PhieuDichVu.class));
									Executions.sendRedirect("./PhieuDichVu_DS.zul");
									
								}
								else
									
								{
									Messagebox.show("Đã có lỗi xảy ra! Vui lòng thử lại sau!", "Lỗi", Messagebox.OK, Messagebox.ERROR);
								}
									
							}
							else
							{
								Messagebox.show("Không thể xóa phiếu đã lập phiếu thu", "Lỗi", Messagebox.OK, Messagebox.ERROR);
							}
							break;
						}
					}
				}
		});

		
	}
	@Command
	public void ThemPhieuDichVu() {
		Executions.sendRedirect("./PhieuDichVu_Add.zul");
	}
	
	@Command
	public void createPhieuThu(@BindingParam("phieuthu_id") long id) {
		// save session the selected id
		Sessions.getCurrent().setAttribute(PhieuThuDSViewModel.PDH_ID, id);
		Sessions.getCurrent().setAttribute(PhieuThuDSViewModel.PDH_TYPE, "pdv");
		Executions.sendRedirect("./PhieuThu_Add.zul");
	}
}
