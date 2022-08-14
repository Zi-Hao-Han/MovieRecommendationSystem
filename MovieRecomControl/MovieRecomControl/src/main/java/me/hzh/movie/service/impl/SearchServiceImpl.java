package me.hzh.movie.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hzh.movie.domain.Search;
import me.hzh.movie.repository.RateRepository;
import me.hzh.movie.repository.SearchRepository;
import me.hzh.movie.service.SearchService;
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
    public SearchServiceImpl(SearchRepository SearchRepository) {
        this.searchRepository = SearchRepository;
    }

    private SearchRepository searchRepository;

	@Override
	public Search save(Search search) {
		// TODO Auto-generated method stub
		return searchRepository.save(search);	
	}

	@Override
	public List<Search> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return searchRepository.findByUserId(userId);
	}
    

}
