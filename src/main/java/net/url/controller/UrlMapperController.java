package net.url.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import net.url.domain.UrlMapper;
import net.url.repository.UrlMapperRepository;

@Controller
public class UrlMapperController {
	private List<UrlMapper> url_mappers = new ArrayList<UrlMapper>();
	
	@Autowired
	private UrlMapperRepository urlMapperRepository;
	
	@PostMapping("/create")
	public String create(UrlMapper url_mapper) {
		System.out.println(url_mapper);
		urlMapperRepository.save(url_mapper);
		return "index";
//		return "redirect:/log";
	}
}
