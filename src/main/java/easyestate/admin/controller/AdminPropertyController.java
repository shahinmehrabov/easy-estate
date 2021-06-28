package easyestate.admin.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import easyestate.model.Property;
import easyestate.model.User;
import easyestate.repository.UserRepository;
import easyestate.service.AmenityService;
import easyestate.service.CountryService;
import easyestate.service.CurrencyService;
import easyestate.service.PropertyService;
import easyestate.service.StatusService;
import easyestate.service.StorageService;
import easyestate.service.TypeService;

@Controller
@RequestMapping("/admin/properties")
public class AdminPropertyController {
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private AmenityService amenityService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private TypeService typeService;
	
	@GetMapping
	public String findAll(Model model) {
		
		model.addAttribute("properties", propertyService.findAll());
		
		return "admin-properties";
		
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		
		Property property = new Property();
		
		model.addAttribute("property", property);
		
		model.addAttribute("amenities", amenityService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("currencies", currencyService.findAll());
		model.addAttribute("statuses", statusService.findAll());
		model.addAttribute("types", typeService.findAll());
		
		return "add-property";
		
	}
	
	@PostMapping("/add")
	public String add(Principal principal, @ModelAttribute("property") Property property, @RequestParam("first_image") MultipartFile firstImage,
			@RequestParam("second_image") MultipartFile secondImage, @RequestParam("third_image") MultipartFile thirdImage, 
			@RequestParam("fourth_image") MultipartFile fourthImage) throws IOException {
		
		String firstImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(firstImage.getOriginalFilename());
		String secondImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(secondImage.getOriginalFilename());
		String thirdImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(thirdImage.getOriginalFilename());
		String fourthImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(fourthImage.getOriginalFilename());
		
		property.setFirstImage(firstImageName);
		property.setSecondImage(secondImageName);
		property.setThirdImage(thirdImageName);
		property.setFourthImage(fourthImageName);
		
		String username = principal.getName();
		
		User user = userRepository.findByUsername(username);
		
		property.setUserid(user.getId());
		
		propertyService.save(property);
		
		storageService.uploadFile(firstImage, firstImageName);
		storageService.uploadFile(secondImage, secondImageName);
		storageService.uploadFile(thirdImage, thirdImageName);
		storageService.uploadFile(fourthImage, fourthImageName);
		
		return "redirect:/admin/properties?saved";
		
	}
	
	@PostMapping("/edit")
	public String edit(Principal principal, @ModelAttribute("property") Property property, @RequestParam("first_image") MultipartFile firstImage,
			@RequestParam("second_image") MultipartFile secondImage, @RequestParam("third_image") MultipartFile thirdImage, 
			@RequestParam("fourth_image") MultipartFile fourthImage) throws IOException {
		
		if(!firstImage.isEmpty()) {
			
			String firstImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(firstImage.getOriginalFilename());
			
			property.setFirstImage(firstImageName);
			
			storageService.uploadFile(firstImage, firstImageName);
			
		}
		
		if(!secondImage.isEmpty()) {
			
			String secondImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(secondImage.getOriginalFilename());
			
			property.setSecondImage(secondImageName);
			
			storageService.uploadFile(secondImage, secondImageName);
			
		}
		
		if(!thirdImage.isEmpty()) {
			
			String thirdImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(thirdImage.getOriginalFilename());
			
			property.setThirdImage(thirdImageName);
			
			storageService.uploadFile(thirdImage, thirdImageName);
			
		}
		
		if(!fourthImage.isEmpty()) {
			
			String fourthImageName = System.currentTimeMillis() + "-" + StringUtils.cleanPath(fourthImage.getOriginalFilename());
			
			property.setFourthImage("/img/properties/" + fourthImageName);
			
			storageService.uploadFile(fourthImage, fourthImageName);
			
		}
		
		propertyService.save(property);
		
		return "redirect:/admin/properties?saved";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		Optional<Property> optionalProperty = propertyService.findById(id);
		
		Property property = optionalProperty.get();
		
		model.addAttribute("property", property);
		
		model.addAttribute("amenities", amenityService.findAll());
		model.addAttribute("countries", countryService.findAll());
		model.addAttribute("currencies", currencyService.findAll());
		model.addAttribute("statuses", statusService.findAll());
		model.addAttribute("types", typeService.findAll());
		
		return "admin-edit-property";
		
	}
	
	@RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.DELETE})
	public String delete(@PathVariable Long id) {
		
		propertyService.deleteById(id);
		
		return "redirect:/admin/properties?deleted";
		
	}

}