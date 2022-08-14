package me.hzh.movie.service;

import me.hzh.movie.domain.Rate;

import java.util.List;

public interface RateService {
    Rate save(Rate rate);
    List<Rate> findByUserId(Long userId);
    List<?> findAllGroupByMovieId();
    Integer findRateCountByUserId(Long userId);
}
