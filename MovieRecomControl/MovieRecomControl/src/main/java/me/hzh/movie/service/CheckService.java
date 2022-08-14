package me.hzh.movie.service;

import java.util.List;


import me.hzh.movie.domain.CheckMovie;

public interface CheckService {
    CheckMovie save(CheckMovie check);
    CheckMovie findByMovieIdAndUserId(Long movieId,Long userId);
	List<CheckMovie> findByUserId(Long userId);

}