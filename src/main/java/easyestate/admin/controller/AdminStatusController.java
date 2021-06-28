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

import easyestate.model.Status;
import easyestate.service.StatusService;

@Controller
@RequestMapping("/admin/status")
public class AdminStatusController {
	
	@Autowired
	private StatusService statusService;
	
	@GetMapping
	public String findAll(Model model) {
		
		model.addAttribute("statuses", statusService.findAll());
		
		return "admin-status";
		
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		Status status = new Status();
		
		model.addAttribute("status", status);
		
		String pageTitle = "Admin - Add Status";
		String title = "Add Status";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-status";
		
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("status") Status status) {
		
		statusService.save(status);
		
		return "redirect:/admin/status?saved";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		model.addAttribute("status", statusService.findById(id));
		
		String pageTitle = "Admin - Edit Status";
		String title = "Edit Status";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-status";
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Long id) {
		
		statusService.deleteById(id);
		
		return "redirect:/admin/status?deleted";
		
	}

}