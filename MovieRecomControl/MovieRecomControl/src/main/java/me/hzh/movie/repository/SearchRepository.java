package me.hzh.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hzh.movie.domain.Search;

public interface SearchRepository extends JpaRepository<Search,Long> {
	List<Search> findByUserId(Long userId);
}


