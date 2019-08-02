package net.url.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.url.domain.UrlMapper;


public interface UrlMapperRepository extends CrudRepository<UrlMapper, Long> {
	public List<UrlMapper> findAllByOrderByIdAsc();
	
	public List<UrlMapper> findAllByOrderByIdDesc();
	
	public UrlMapper findByShortenedUrl(String shortenedUrl);
}
