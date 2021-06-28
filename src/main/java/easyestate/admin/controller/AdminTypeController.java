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

import easyestate.model.Type;
import easyestate.service.TypeService;

@Controller
@RequestMapping("/admin/types")
public class AdminTypeController {
	
	@Autowired
	private TypeService typeService;
	
	@GetMapping
	public String findAll(Model model) {
		
		model.addAttribute("types", typeService.findAll());
		
		return "admin-types";
		
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		Type type = new Type();
		
		model.addAttribute("type", type);
		
		String pageTitle = "Admin - Add Type";
		String title = "Add Type";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-type";
		
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("type") Type type) {
		
		typeService.save(type);
		
		return "redirect:/admin/types?saved";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		model.addAttribute("type", typeService.findById(id));
		
		String pageTitle = "Admin - Edit Type";
		String title = "Edit Type";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-type";
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Long id) {
		
		typeService.deleteById(id);
		
		return "redirect:/admin/types?deleted";
		
	}

}