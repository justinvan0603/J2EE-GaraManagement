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
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Messagebox;

import business.entities.CT_PhieuDichVu;
import business.entities.PhieuDichVu;
import business.entities.PhuTung;
import business.service.ChiTietPhieuDichVuServiceImpl;
import business.service.PhieuDichVuServiceImpl;
import business.service.PhieuThuServiceImpl;
import business.service.PhuTungServiceImpl;

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
	private static final List<String> SEARCH_TYPES = new ArrayList<String>();
	static {
		
		SEARCH_TYPES.add("Mã phiếu");
		
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
	}
	@Command
	@NotifyChange("listPhieuDichVu")
	public void filterData(@BindingParam("search_string") String searchString,
			@BindingParam("combobox_selected_index") int selectedIndex) {
		// if search type is name
		if (selectedIndex == 0) {
			this.listPhieuDichVu = this.phieuDichVuService.findByMaPhieu(searchString);
			
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
	public void deletePhieuDichVu(@BindingParam("id") Long id)
	{
		if(this.phieuThuServiceImpl.findByIdPhieuCanThu("pdv", id).size() == 0)
		{
			if(this.phieuDichVuService.delete(id, PhieuDichVu.class))
			{
				this.listPhieuDichVu.clear();
				this.setListPhieuDichVu( this.phieuDichVuService.getAll(PhieuDichVu.class));
			
				List<CT_PhieuDichVu> listCt = this.chiTietPhieuDichVuServiceImpl.getByPhieuDichVuId(id);
				if(!listCt.isEmpty())
				{ 
					for(Iterator<CT_PhieuDichVu> i = listCt.iterator(); i.hasNext();)
					{
						CT_PhieuDichVu ctPhieu = i.next();
						PhuTung pt = this.phuTungServiceImpl.findById(ctPhieu.getMaPhuTung(), PhuTung.class);
						pt.setSoLuongTon(pt.getSoLuongTon() + ctPhieu.getSoLuong());
						this.phuTungServiceImpl.update(pt.getId(), pt);
						this.chiTietPhieuDichVuServiceImpl.delete((long)ctPhieu.getId(), CT_PhieuDichVu.class);
					}
				}
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
