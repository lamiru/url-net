package net.url.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.url.domain.UrlMapper;


public interface UrlMapperRepository extends JpaRepository<UrlMapper, Long> {
	public List<UrlMapper> findAllByOrderByIdAsc();
	
	public List<UrlMapper> findAllByOrderByIdDesc();
}
