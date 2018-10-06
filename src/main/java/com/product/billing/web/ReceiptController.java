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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.product.billing.dto.ReceiptDTO;
import com.product.billing.model.Invoice;
import com.product.billing.model.Receipt;
import com.product.billing.model.Vendor;
import com.product.billing.service.InvoiceService;
import com.product.billing.service.ReceiptService;
import com.product.billing.service.UserService;
import com.product.billing.service.VendorService;
import com.product.billing.validator.ReceiptValidator;

@Controller
@RequestMapping("/receipt")
public class ReceiptController {
	
	final static int DELETED=1;
	final static int NOTDELETED=0;

	private static final Logger logger = Logger.getLogger(ReceiptController.class.getName());
   
	@Autowired
    private VendorService vendorService;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ReceiptValidator receiptValidator;
    @Autowired
    private UserService userService;
    
    
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "receiptDate", new CustomDateEditor(dateFormat, true));
	}
    
    @GetMapping(value = {"/list"})
    public ModelAndView getReceipt(Model model) {
        ModelAndView view = new ModelAndView("admin/receipts");
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("receiptlist", receiptService.getReceipts(companyId,NOTDELETED));
        model.addAttribute("active", "receipt");
        return view;
    }

    @GetMapping(value = {"/addReceipt"})
    public ModelAndView openAddRceipt(Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addReceipt");
        view.addObject("receiptDTO", new ReceiptDTO());
        setListValues(model,"create");
        model.addAttribute("active", "receipt");
        model.addAttribute("optype","create");
        return view;
    }

    @PostMapping(value = {"/submitReceipt"})
    public ModelAndView addReceipt(@Valid @ModelAttribute("receiptDTO")ReceiptDTO receiptDTO,
                                    BindingResult result, Model model) {
    	receiptValidator.validate(receiptDTO, result);
    	ModelAndView view=new ModelAndView();

        if (result.hasErrors()) {
            setListValues(model,"edit");
            view = new ModelAndView("admin/forms/addReceipt");
            logger.info("There are form errors " + result.getFieldErrorCount());
        } else {
        	Receipt receipt = new Receipt(receiptDTO);
        	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        	receipt.setCompanyId(companyId);
        	receiptService.save(receipt);
            view = new ModelAndView("admin/receipts");
            view.addObject("receiptlist", receiptService.getReceipts(companyId,NOTDELETED));
        }
        model.addAttribute("active", "receipt");
        return view;
    }

    @GetMapping(value = {"/editReceipt/{id}"})
    public ModelAndView editReceipt(@PathVariable("id") long id, Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addReceipt");
        view.addObject("receiptDTO", receiptService.getReceiptById(id));
        setListValues(model,"edit");
        model.addAttribute("active", "receipt");
        return view;
    }
    
    @GetMapping(value = {"/deleteReceipt/{id}"})
    public ModelAndView deleteReceipt(@PathVariable("id") long id, Model model) {
        Receipt receipt = new Receipt(receiptService.getReceiptById(id));
        receipt.setIsDeleted(DELETED);
        receiptService.save(receipt);
        ModelAndView view = new ModelAndView("admin/receipts");
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("receiptlist", receiptService.getReceipts(companyId,NOTDELETED));
        model.addAttribute("active", "receipt");
        return view;
    }
    
    @PostMapping(value = {"/ajax/getInvoiceList"})
    @ResponseBody
    public Map<Long, String> getInvoiceList(@RequestParam("vendorId") long vendorId) {
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
    	List<Invoice> lstinvoice=invoiceService.getInvoices(companyId,NOTDELETED);
        Map<Long, String > invoices = new HashMap<>();
    	lstinvoice.forEach(inv -> invoices.put(inv.getId(),"Rs. "+inv.getInvoiceNo()+" ( "+inv.getPayableTotal()+" dated "+inv.getInvoiceDate()+")"));
        return invoices; 
    }
    
    public void setListValues(Model model,String optype) {
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        List<Vendor> lstvendor=vendorService.getVendors(companyId,NOTDELETED);
        Map<Long, String > vendors = new HashMap<>();
        lstvendor.forEach(vend -> vendors.put(vend.getId(), vend.getName()));
        List<Invoice> lstinvoice=invoiceService.getInvoices(companyId,NOTDELETED);
        Map<Long, String > invoices = new HashMap<>();
        lstinvoice.forEach(invoice -> invoices.put(invoice.getId(), invoice.getInvoiceNo()+" (Rs. "+invoice.getPayableTotal()+" Dated "+invoice.getInvoiceDate()+" )"));

        Map<Integer, String > lstpaymentmodetype = new HashMap<Integer, String>();
        lstpaymentmodetype.put(1,"None");
        lstpaymentmodetype.put(2,"Cash");
        lstpaymentmodetype.put(3,"Cheque");
        lstpaymentmodetype.put(4,"Demand Draft");
        lstpaymentmodetype.put(5,"Direct Transfer");
        
        Map<Integer, String > lststatus = new HashMap<Integer, String>();
        lststatus.put(1,"Part Payment");
        lststatus.put(2,"Full Payment");
        
        model.addAttribute("lstinvoices", invoices);
        model.addAttribute("lststatus", lststatus);
        model.addAttribute("lstpaymentmodetype", lstpaymentmodetype);
        model.addAttribute("lstvendor", vendors);
        model.addAttribute("optype", optype);

    }

}
