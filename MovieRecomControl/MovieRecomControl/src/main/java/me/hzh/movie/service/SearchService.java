
package me.hzh.movie.service;

import java.util.List;

import me.hzh.movie.domain.Search;

public interface SearchService {
	Search save(Search search);
	List<Search> findByUserId(Long userId);

}
