package easyestate.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easyestate.model.Country;
import easyestate.service.CountryService;

@Controller
@RequestMapping("/admin/countries")
public class AdminCountryController {
	
	@Autowired
	private CountryService countryService;
	
	@GetMapping
	public String findAll(Model model) {
		
		model.addAttribute("countries", countryService.findAll());
		
		return "admin-countries";
		
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		Country country = new Country();
		
		model.addAttribute("country", country);
		
		String pageTitle = "Admin - Add Country";
		String title = "Add Country";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-country";
		
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("country") Country country) {
		
		countryService.save(country);
		
		return "redirect:/admin/countries?saved";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		model.addAttribute("country", countryService.findById(id));
		
		String pageTitle = "Admin - Edit Country";
		String title = "Edit Country";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-country";
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Long id) {
		
		countryService.deleteById(id);
		
		return "redirect:/admin/countries?deleted";
		
	}

}