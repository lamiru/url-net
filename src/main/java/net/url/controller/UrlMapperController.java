package net.url.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.url.domain.UrlMapper;
import net.url.repository.UrlMapperRepository;

@Controller
public class UrlMapperController {
	
	@Autowired
	private UrlMapperRepository urlMapperRepository;
	
	public String idToShortenedUrl(Long id) {  
		// 62 Characters.
	    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    int length = chars.length();
	    StringBuilder shortenedUrl = new StringBuilder("");
	   
	    while (id > 0) {
	    	int s = (int) (id % length);
	    	System.out.println(s);
	    	
	    	// Because id starts with 1, use s-1 instead of s in order to match 1 to a.
	    	shortenedUrl.append(chars.charAt(s-1)); 
	        id = id / length;
	    }
	  
	    return shortenedUrl.toString();
	}
	
	@PostMapping("/create")
	public String create(UrlMapper url_mapper) {
		url_mapper = urlMapperRepository.save(url_mapper);
		String shortenedUrl = idToShortenedUrl(url_mapper.getId());
		url_mapper.setShortenedUrl(shortenedUrl);
		urlMapperRepository.save(url_mapper);
		return "redirect:/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("urlMappers", urlMapperRepository.findAllByOrderByIdDesc());
		return "list";
	}
	
}
