package com.product.billing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/superadmin")
public class SuperAdminController {
	 @RequestMapping(value = "/", method = RequestMethod.GET)
	    public String index(Model model) {
	        model.addAttribute("active","dashboard");
	        return "superadmin/dashboard";
	    }
}
