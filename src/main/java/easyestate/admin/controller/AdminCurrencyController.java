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

import easyestate.model.Currency;
import easyestate.service.CurrencyService;

@Controller
@RequestMapping("/admin/currencies")
public class AdminCurrencyController {
	
	@Autowired
	private CurrencyService currencyService;
	
	@GetMapping
	public String findAll(Model model) {
		
		model.addAttribute("currencies", currencyService.findAll());
		
		return "admin-currencies";
		
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		Currency currency = new Currency();
		
		model.addAttribute("currency", currency);
		
		String pageTitle = "Admin - Add Currency";
		String title = "Add Currency";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-currency";
		
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("currency") Currency currency) {
		
		currencyService.save(currency);
		
		return "redirect:/admin/currencies?saved";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		model.addAttribute("currency", currencyService.findById(id));
		
		String pageTitle = "Admin - Edit Currency";
		String title = "Edit Currency";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-currency";
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Long id) {
		
		currencyService.deleteById(id);
		
		return "redirect:/admin/currencies?deleted";
		
	}

}