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
import org.springframework.web.servlet.ModelAndView;

import com.product.billing.dto.CustomerDTO;
import com.product.billing.model.Customer;
import com.product.billing.service.CustomerService;
import com.product.billing.service.UserService;
import com.product.billing.validator.CustomerValidator;

@Controller
public class CustomerController {
	
	final static int DELETED=1;
	final static int NOTDELETED=0;
    private static final Logger logger = Logger.getLogger(CustomerController.class.getName());

    @Autowired
	private CustomerValidator customerValidator;
	@Autowired
    private CustomerService customerService;
	@Autowired
	private UserService userService;
	
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = {"/customers"})
    public ModelAndView getCustomers(Model model) {
        ModelAndView view = new ModelAndView("admin/customers");
        long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("customers", customerService.getCustomers(companyId,NOTDELETED));
        model.addAttribute("active", "customers");
        return view;
    }

    @GetMapping(value = {"/addCustomer"})
    public ModelAndView openAddCustomer(Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addCustomer");
        view.addObject("customerDTO", new CustomerDTO());
        model.addAttribute("active", "customers");
        model.addAttribute("optype", "create");
        return view;
    }

    @PostMapping(value = {"/customer"})
    public ModelAndView addCustomer(@Valid @ModelAttribute("customerDTO")CustomerDTO customerDTO,
                                    BindingResult result, Model model) {
    	customerValidator.validate(customerDTO, result);
        ModelAndView view = new ModelAndView();

    	if (result.hasErrors()) {
            logger.info("There are form errors " + result.getFieldErrorCount());
            view = new ModelAndView("admin/forms/addCustomer");
            model.addAttribute("optype", "create");
    	} else {
            Customer customer = new Customer(customerDTO);
            long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
            customer.setCompanyId(companyId);
            customerService.save(customer);
            view = new ModelAndView("admin/customers");
            view.addObject("customers", customerService.getCustomers(companyId,NOTDELETED));
        }
        model.addAttribute("active", "customers");
        return view;
    }

    @GetMapping(value = {"/editCustomer/{id}"})
    public ModelAndView editCustomer(@PathVariable("id") long id, Model model) {
        ModelAndView view = new ModelAndView("admin/forms/addCustomer");
        view.addObject("customerDTO", customerService.getCustomerById(id));
        model.addAttribute("active", "customers");
        model.addAttribute("optype", "edit");
        return view;
    }
    
    @GetMapping(value = {"/deleteCustomer/{id}"})
    public ModelAndView deleteCustomer(@PathVariable("id") long id, Model model) {
        Customer customer = new Customer(customerService.getCustomerById(id));
        customer.setIsDeleted(DELETED);
        customerService.save(customer);
        ModelAndView view = new ModelAndView("admin/customers");
        long companyId=userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getCompany().getId();
        view.addObject("customers", customerService.getCustomers(companyId,NOTDELETED));
        model.addAttribute("active", "customers");
        return view;
    }
}
