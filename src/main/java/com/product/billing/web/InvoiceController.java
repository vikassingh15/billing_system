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

import com.product.billing.dto.InvoiceDTO;
import com.product.billing.model.Invoice;
import com.product.billing.model.Vendor;
import com.product.billing.service.InvoiceService;
import com.product.billing.service.UserService;
import com.product.billing.service.VendorService;
import com.product.billing.validator.InvoiceValidator;


@Controller
@RequestMapping("/invoice")
public class InvoiceController {
	
	final static int DELETED=1;
	final static int NOTDELETED=0;

    
	private static final Logger logger = Logger.getLogger(InvoiceController.class.getName());
   
    @Autowired
    private VendorService vendorService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceValidator invoiceValidator;
    @Autowired
    private UserService userService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @InitBinder
	public void dataBinding(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "invoiceDate", new CustomDateEditor(dateFormat, true));
	}
    
    @GetMapping(value = {"/list"})
    public ModelAndView getInvoices(Model model) {
        ModelAndView view = new ModelAndView("admin/invoices");
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("invoiceList", invoiceService.getInvoices(companyId,NOTDELETED));
        model.addAttribute("active", "invoices");
        return view;
    }

    @GetMapping(value = {"/addInvoice"})
    public ModelAndView openAddInvoice(Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addInvoice");
        view.addObject("invoiceDTO", new InvoiceDTO());
        setListValues(model, "create");
        model.addAttribute("active", "invoices");
        return view;
    }

    @PostMapping(value = {"/submitInvoice"})
    public ModelAndView addInvoice(@Valid @ModelAttribute("invoiceDTO")InvoiceDTO invoiceDTO,
                                    BindingResult result, Model model) {
    	invoiceValidator.validate(invoiceDTO, result);
        ModelAndView view = new ModelAndView("admin/forms/addInvoice");
        if (result.hasErrors()) {
            setListValues(model, "edit");
            logger.info("There are form errors " + result.getFieldErrorCount());
            view = new ModelAndView("admin/forms/addInvoice");
        } else {
        	Invoice invoice = new Invoice(invoiceDTO);
        	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        	invoice.setCompanyId(companyId);
            invoiceService.save(invoice);
            view = new ModelAndView("admin/invoices");
            view.addObject("invoiceList", invoiceService.getInvoices(companyId,NOTDELETED));
        }
        model.addAttribute("active", "invoices");
        return view;
    }

    @GetMapping(value = {"/editInvoice/{id}"})
    public ModelAndView editCustomer(@PathVariable("id") long id, Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addInvoice");
        view.addObject("invoiceDTO", invoiceService.getInvoiceById(id));
        setListValues(model, "edit");
        model.addAttribute("active", "invoices");
        return view;
    }
    
    @GetMapping(value = {"/deleteInvoice/{id}"})
    public ModelAndView deleteInvoice(@PathVariable("id") long id, Model model) {
        Invoice invoice = new Invoice(invoiceService.getInvoiceById(id));
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        invoice.setIsDeleted(DELETED);
        invoiceService.save(invoice);
        ModelAndView view = new ModelAndView("admin/invoices");
        view.addObject("invoiceList", invoiceService.getInvoices(companyId,NOTDELETED));
        model.addAttribute("active", "invoices");
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
        lstproducttype.put(2,"Fix Charge");
        
        Map<Integer, String > lstsubtype = new HashMap<>();
        lstsubtype.put(1,"Select type");
        lstsubtype.put(2,"Molding Charge");
        lstsubtype.put(3,"Polish");
        lstsubtype.put(4,"Hall Charge");
        lstsubtype.put(5,"Transportation");
        
        model.addAttribute("lstsubtype", lstsubtype);
        model.addAttribute("lstproducttype", lstproducttype);
        model.addAttribute("lstdiscount", lstdiscount);
        model.addAttribute("lstvendor", vendors);
        model.addAttribute("optype",optype);
    }
}
