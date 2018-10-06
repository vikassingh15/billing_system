package com.product.billing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.product.billing.dto.UserDTO;
import com.product.billing.model.Company;
import com.product.billing.model.Role;
import com.product.billing.model.User;
import com.product.billing.service.CompanyService;
import com.product.billing.service.RoleService;
import com.product.billing.service.UserService;
import com.product.billing.validator.UserValidator;

@Controller
@RequestMapping("/superadmin")
public class UserDetailController {

	
	final static int DELETED=1;
	final static int NOTDELETED=0;

    private static final Logger logger = Logger.getLogger(UserDetailController.class.getName());

    @Autowired
	private UserValidator userValidator;
	@Autowired
    private UserService userService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private RoleService roleService;
	
    public UserDetailController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = {"/users"})
    public ModelAndView getUser(Model model) {
        ModelAndView view = new ModelAndView("superadmin/users");
        view.addObject("users", userService.getUsers(NOTDELETED));
        model.addAttribute("active", "users");
        return view;
    }

    @GetMapping(value = {"/addUser"})
    public ModelAndView openAddUser(Model model) {
        ModelAndView view = new ModelAndView("superadmin/forms/addUser");
        setListItems(model, "create");
        view.addObject("userDTO", new UserDTO());
        model.addAttribute("active", "users");
        return view;
    }

    @PostMapping(value = {"/user"})
    public ModelAndView addCompany(@Valid @ModelAttribute("userDTO")UserDTO userDTO,
                                    BindingResult result, Model model) {
    	userValidator.validate(userDTO, result);
        ModelAndView view = new ModelAndView();

    	if (result.hasErrors()) {
            logger.info("There are form errors " + result.getFieldErrorCount());
            view = new ModelAndView("superadmin/forms/addUser");
            setListItems(model, "edit");
    	} else {
            User user = new User(userDTO);
            userService.save(user);
            view = new ModelAndView("superadmin/users");
            view.addObject("users", userService.getUsers(NOTDELETED));
        }
        model.addAttribute("active", "users");
        return view;
    }

    @GetMapping(value = {"/editUser/{id}"})
    public ModelAndView editCompany(@PathVariable("id") long id, Model model) {
        ModelAndView view = new ModelAndView("superadmin/forms/addUser");
        view.addObject("userDTO", userService.getUserById(id));
        setListItems(model, "edit");
        model.addAttribute("active", "users");
        return view;
    }
    
    @GetMapping(value = {"/deleteUser/{id}"})
    public ModelAndView deleteUser(@PathVariable("id") long id, Model model) {
        User user = new User(userService.getUserById(id));
        user.setIsDeleted(DELETED);
        userService.save(user);
        ModelAndView view = new ModelAndView("superadmin/users");
        view.addObject("users", userService.getUsers(NOTDELETED));
        model.addAttribute("active", "users");
        return view;
    }

    public void setListItems(Model model,String optype) {
    	List<Company> lstcompany=companyService.getCompanies(NOTDELETED);
        Map<Long, String > companies = new HashMap<>();
        lstcompany.forEach(company -> companies.put(company.getId(), company.getCompanyName()));
        model.addAttribute("lstcompany", companies);
        List<Role> lstroles=roleService.getRoles();
        Map<Long, String > roles = new HashMap<>();
        lstroles.forEach(role -> roles.put(role.getId(), role.getName()));
        model.addAttribute("lstroles", roles);
        model.addAttribute("optype", optype);
    }

}
