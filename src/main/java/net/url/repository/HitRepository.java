package net.url.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.url.domain.Hit;


public interface HitRepository extends CrudRepository<Hit, Long> {
	public List<Hit> findAllByOrderByIdAsc();
	
	public List<Hit> findAllByOrderByIdDesc();
	
	public List<Hit> findAllByOrderByTimestampAsc();
	
	public List<Hit> findAllByOrderByTimestampDesc();
}
