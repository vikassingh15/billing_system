package com.product.billing.web;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.product.billing.dto.VendorDTO;
import com.product.billing.model.Vendor;
import com.product.billing.service.UserService;
import com.product.billing.service.VendorService;
import com.product.billing.validator.VendorValidator;

@Controller
@RequestMapping("/vendor")
public class VendorController {

	@Autowired
	private VendorValidator vendorValidator;
    @Autowired
    private UserService userService;
	
	final static int DELETED=1;
	final static int NOTDELETED=0;

    private static final Logger logger = Logger.getLogger(VendorController.class.getName());
    
    private VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping(value = {"/list"})
    public ModelAndView getVendors(Model model) {
        ModelAndView view = new ModelAndView("admin/vendors");
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("vendors", vendorService.getVendors(companyId,NOTDELETED));
        model.addAttribute("active", "vendor");
        return view;
    }

    @GetMapping(value = {"/addVendor"})
    public ModelAndView openAddVendor(Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addVendor");
        view.addObject("vendorDTO", new VendorDTO());
        model.addAttribute("active", "vendor");
        model.addAttribute("optype", "create");
        return view;
    }

    @PostMapping(value = {"/submitVendor"})
    public ModelAndView submitVendor(@Valid @ModelAttribute("vendorDTO")VendorDTO vendorDTO,
                                    BindingResult result, Model model) {
    	vendorValidator.validate(vendorDTO, result);
        ModelAndView view = new ModelAndView();
    	if (result.hasErrors()) {
            view = new ModelAndView("admin/forms/addVendor");
            logger.info("There are form errors " + result.getFieldErrorCount());
        } else {
        	Vendor vendor = new Vendor(vendorDTO);
        	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        	vendor.setCompanyId(companyId);
            vendorService.save(vendor);
            view = new ModelAndView("admin/vendors");
            view.addObject("vendors", vendorService.getVendors(companyId,NOTDELETED));
        }
        model.addAttribute("active", "vendor");
        return view;
    }

    @GetMapping(value = {"/editVendor/{id}"})
    public ModelAndView editVendor(@PathVariable("id") long id, Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addVendor");
        view.addObject("vendorDTO", vendorService.getVendorById(id));
        model.addAttribute("active", "vendor");
        model.addAttribute("optype", "edit");
        return view;
    }
    
    @GetMapping(value = {"/deleteVendor/{id}"})
    public ModelAndView deleteVendor(@PathVariable("id") long id, Model model) {
    	Vendor vendor = new Vendor(vendorService.getVendorById(id));
    	vendor.setIsDeleted(DELETED);
        vendorService.save(vendor);
        ModelAndView view = new ModelAndView("admin/vendors");
    	long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("vendors", vendorService.getVendors(companyId,NOTDELETED));
        model.addAttribute("active", "vendor");
        return view;
    }
}
