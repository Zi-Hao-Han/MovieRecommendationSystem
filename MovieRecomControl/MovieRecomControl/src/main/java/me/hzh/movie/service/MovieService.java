package me.hzh.movie.service;

import me.hzh.movie.domain.Movie;
import me.hzh.movie.domain.Rate;

import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    void save(Movie movie);
    
    List<Movie> findByMovieName(String searchContent);
    List<Movie> findByDescrip(String searchContent);
    List<Movie> findByTimelong(String searchContent);
    List<Movie> findByShoot(String searchContent);
    List<Movie> findByLanguage(String searchContent);
    List<Movie> findByActor(String searchContent);
    List<Movie> findByDirector(String searchContent);
    List<Movie> findByIssue(String searchContent);
    Page<Movie> findAll(Integer page,Integer size);
    List<Movie> findAll();
    Movie getOne(Long id);
}
