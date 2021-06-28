package easyestate.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.amazonaws.util.IOUtils;

import easyestate.service.StorageService;

@Controller
@RequestMapping("/image")
public class StorageController {
	
	@Autowired
	private StorageService storageService;
	
	@GetMapping("/{imageName}")
	public void image(@PathVariable String imageName, HttpServletResponse response) throws IOException {
		
		response.setContentType(imageName.substring(imageName.length() - 3));
		
		InputStream is = new ByteArrayInputStream(storageService.downloadFile(imageName));
		
		IOUtils.copy(is, response.getOutputStream());
		
	}

}
