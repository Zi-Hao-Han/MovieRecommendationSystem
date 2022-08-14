package me.hzh.movie.service.impl;

import me.hzh.movie.domain.CheckMovie;
import me.hzh.movie.repository.CheckRepository;
import me.hzh.movie.service.CheckService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CheckServiceImpl implements CheckService {

    private CheckRepository checkRepository;
    @Autowired
    public CheckServiceImpl(CheckRepository checkRepository) {
        this.checkRepository = checkRepository;
    }

    @Override
    public CheckMovie save(CheckMovie check) {
    	return checkRepository.save(check);
    }

	@Override
	public CheckMovie findByMovieIdAndUserId(Long movieId, Long userId) {
		// TODO Auto-generated method stub
		return checkRepository.findByMovieIdAndUserId(movieId, userId);
	}

	@Override
	public List<CheckMovie> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return checkRepository.findByUserId(userId);
	}


	
    
}