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

import easyestate.model.Amenity;
import easyestate.service.AmenityService;

@Controller
@RequestMapping("/admin/amenities")
public class AdminAmenityController {
	
	@Autowired
	private AmenityService amenityService;
	
	@GetMapping
	public String findAll(Model model) {
		
		model.addAttribute("amenities", amenityService.findAll());
		
		return "admin-amenities";
		
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		Amenity amenity = new Amenity();
		
		model.addAttribute("amenity", amenity);
		
		String pageTitle = "Admin - Add Amenity";
		String title = "Add Amenity";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-amenity";
		
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute("amenity") Amenity amenity) {
		
		amenityService.save(amenity);
		
		return "redirect:/admin/amenities?saved";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		model.addAttribute("amenity", amenityService.findById(id));
		
		String pageTitle = "Admin - Edit Amenity";
		String title = "Edit Amenity";
		
		model.addAttribute("page-title", pageTitle);
		model.addAttribute("title", title);
		
		return "add-edit-amenity";
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Long id) {
		
		amenityService.deleteById(id);
		
		return "redirect:/admin/amenities?deleted";
		
	}

}