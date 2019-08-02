package net.url.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.view.RedirectView;

import net.url.domain.Hit;
import net.url.domain.UrlMapper;
import net.url.repository.HitRepository;
import net.url.repository.UrlMapperRepository;

@Controller
public class UrlMapperController {
	
	@Autowired
	private UrlMapperRepository urlMapperRepository;
	
	@Autowired
	private HitRepository hitRepository;
	
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
	
	@PostMapping("/m/create")
	public String create(UrlMapper url_mapper) {
		url_mapper = urlMapperRepository.save(url_mapper);
		String shortenedUrl = idToShortenedUrl(url_mapper.getId());
		url_mapper.setShortenedUrl(shortenedUrl);
		urlMapperRepository.save(url_mapper);
		return "redirect:/m/list";
	}
	
	@GetMapping("/m/list")
	public String list(Model model) {
		model.addAttribute("urlMappers", urlMapperRepository.findAllByOrderByIdDesc());
		return "list";
	}

	@GetMapping("/m/hit")
	public String hit(Model model) {
		model.addAttribute("hit", hitRepository.findAllByOrderByTimestampDesc());
		return "hit";
	}
	
	@GetMapping("/{shortenedUrl}")
    public RedirectView redirectUrl(@PathVariable String shortenedUrl) {
		UrlMapper url_mapper = urlMapperRepository.findByShortenedUrl(shortenedUrl);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url_mapper.getOriginalUrl());
        
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String userIp = req.getHeader("X-FORWARDED-FOR");
        
        if (userIp == null) {
        	userIp = req.getRemoteAddr();
        }

        Date date= new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        
        Hit hit = new Hit(timestamp, userIp, shortenedUrl);
        hitRepository.save(hit);
        
        return redirectView;
    }
	
}
