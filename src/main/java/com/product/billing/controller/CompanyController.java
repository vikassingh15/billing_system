package com.product.billing.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.product.billing.dto.CompanyDTO;
import com.product.billing.model.Company;
import com.product.billing.service.CompanyService;
import com.product.billing.validator.CompanyValidator;

@Controller
@RequestMapping("/superadmin")
public class CompanyController {
	
	final static int DELETED=1;
	final static int NOTDELETED=0;
    private static final Logger logger = Logger.getLogger(CompanyController.class.getName());

    @Autowired
	private CompanyValidator companyValidator;
	@Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = {"/companies"})
    public ModelAndView getCompanies(Model model) {
        ModelAndView view = new ModelAndView("superadmin/companies");
        view.addObject("companies", companyService.getCompanies(NOTDELETED));
        model.addAttribute("active", "companies");
        return view;
    }

    @GetMapping(value = {"/addCompany"})
    public ModelAndView openAddCompany(Model model) {
        ModelAndView view = new ModelAndView("superadmin/forms/addCompany");
        view.addObject("companyDTO", new CompanyDTO());
        model.addAttribute("active", "companies");
        model.addAttribute("optype", "create");
        return view;
    }

    @PostMapping(value = {"/company"})
    public ModelAndView addCompany(@Valid @ModelAttribute("companyDTO")CompanyDTO companyDTO,
                                    BindingResult result, Model model) {
    	companyValidator.validate(companyDTO, result);
        ModelAndView view = new ModelAndView();

    	if (result.hasErrors()) {
            logger.info("There are form errors " + result.getFieldErrorCount());
            view = new ModelAndView("superadmin/forms/addCompany");
            model.addAttribute("optype", "create");
    	} else {
            Company company = new Company(companyDTO);
            companyService.save(company);
            view = new ModelAndView("superadmin/companies");
            view.addObject("companies", companyService.getCompanies(NOTDELETED));
        }
        model.addAttribute("active", "companies");
        return view;
    }

    @GetMapping(value = {"/editCompany/{id}"})
    public ModelAndView editCompany(@PathVariable("id") long id, Model model) {
        ModelAndView view = new ModelAndView("superadmin/forms/addCompany");
        view.addObject("companyDTO", companyService.getCompanyById(id));
        model.addAttribute("active", "companies");
        model.addAttribute("optype", "edit");
        return view;
    }
    
    @GetMapping(value = {"/deleteCompany/{id}"})
    public ModelAndView deleteCompany(@PathVariable("id") long id, Model model) {
        Company company = new Company(companyService.getCompanyById(id));
        company.setIsDeleted(DELETED);
        companyService.save(company);
        ModelAndView view = new ModelAndView("superadmin/companies");
        view.addObject("companies", companyService.getCompanies(NOTDELETED));
        model.addAttribute("active", "companies");
        return view;
    }
}
