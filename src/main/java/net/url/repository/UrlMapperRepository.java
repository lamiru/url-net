package net.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.url.domain.UrlMapper;

public interface UrlMapperRepository extends JpaRepository<UrlMapper, Long> {
	
}
