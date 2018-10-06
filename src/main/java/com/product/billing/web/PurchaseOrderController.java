package com.product.billing.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.product.billing.dto.PurchaseOrderDTO;
import com.product.billing.model.PurchaseOrder;
import com.product.billing.model.Vendor;
import com.product.billing.service.PurchaseOrderService;
import com.product.billing.service.UserService;
import com.product.billing.service.VendorService;
import com.product.billing.validator.PurchaseOrderValidator;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {

	final static int DELETED=1;
	final static int NOTDELETED=0;

    private static final Logger logger = Logger.getLogger(PurchaseOrderController.class.getName());
   
	@Autowired
    private VendorService vendorService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private PurchaseOrderValidator purchaseOrderValidator;
    @Autowired
    private UserService userService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "poDate", new CustomDateEditor(dateFormat, true));
	}
    
    @GetMapping(value = {"/list"})
    public ModelAndView getPOs(Model model) {
        ModelAndView view = new ModelAndView("admin/purchaseorders");
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("purchaseOrderList", purchaseOrderService.gePurchaseOrders(companyId,NOTDELETED));
        model.addAttribute("active", "purchaseOrder");
        return view;
    }

    @GetMapping(value = {"/addPO"})
    public ModelAndView openAddPO(Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addPO");
        view.addObject("purchaseOrderDTO", new PurchaseOrderDTO());
        setListValues(model,"create");
        model.addAttribute("active", "purchaseOrder");
        return view;
    }

    @PostMapping(value = {"/submitPO"})
    public ModelAndView addPO(@Valid @ModelAttribute("purchaseOrderDTO")PurchaseOrderDTO purchaseOrderDTO,
                                    BindingResult result, Model model) {
    	purchaseOrderValidator.validate(purchaseOrderDTO, result);
    	ModelAndView view =new ModelAndView();
        if (result.hasErrors()) {
            view = new ModelAndView("admin/forms/addPO");
            setListValues(model,"edit");
            logger.info("There are form errors " + result.getFieldErrorCount());
        } else {
        	PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderDTO);
        	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        	purchaseOrder.setCompanyId(companyId);
        	purchaseOrderService.save(purchaseOrder);
            view = new ModelAndView("admin/purchaseorders");
            view.addObject("purchaseOrderList", purchaseOrderService.gePurchaseOrders(companyId,NOTDELETED));
        }
        model.addAttribute("active", "purchaseOrder");
        return view;
    }

    @GetMapping(value = {"/editPO/{id}"})
    public ModelAndView editPO(@PathVariable("id") long id, Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addPO");
        view.addObject("purchaseOrderDTO", purchaseOrderService.getPurchaseOrderById(id));
        setListValues(model,"edit");
        model.addAttribute("active", "purchaseOrder");
        return view;
    }
    
    @GetMapping(value = {"/deletePO/{id}"})
    public ModelAndView deletePO(@PathVariable("id") long id, Model model) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(purchaseOrderService.getPurchaseOrderById(id));
        purchaseOrder.setIsDeleted(DELETED);
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        purchaseOrderService.save(purchaseOrder);
        ModelAndView view = new ModelAndView("admin/purchaseorders");
        view.addObject("purchaseOrderList", purchaseOrderService.gePurchaseOrders(companyId,NOTDELETED));
        model.addAttribute("active", "purchaseOrder");
        return view;
    }
    
    public void setListValues(Model model,String optype) {
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
    	List<Vendor> lstvendor=vendorService.getVendors(companyId,NOTDELETED);
        Map<Long, String > vendors = new HashMap<>();
        lstvendor.forEach(vend -> vendors.put(vend.getId(), vend.getName()));
        Map<Integer, String > lstdiscount = new HashMap<>();
        lstdiscount.put(1,"Amount");
        lstdiscount.put(2,"Percentage");
        
        Map<Integer, String > lstproducttype = new HashMap<>();
        lstproducttype.put(1,"Product");
        lstproducttype.put(2,"Fix charge");

        Map<Integer, String > lstsubtype = new HashMap<>();
        lstsubtype.put(1,"Molding Charge");
        lstsubtype.put(2,"Polish");
        lstsubtype.put(3,"Hall Charge");
        lstsubtype.put(4,"Transportation");
        
        model.addAttribute("lstsubtype", lstsubtype);
        model.addAttribute("lstproducttype", lstproducttype);
        model.addAttribute("lstdiscount", lstdiscount);
        model.addAttribute("lstvendor", vendors);
        model.addAttribute("optype", optype);
    }
}
