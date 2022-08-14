package me.hzh.movie.repository;

import me.hzh.movie.domain.CheckMovie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CheckRepository extends JpaRepository<CheckMovie,Long> {
	@Query(value = "SELECT  * FROM check_movie WHERE check_movie.movie_id = :movieid AND check_movie.user_id = :userid",nativeQuery = true)
    CheckMovie findByMovieIdAndUserId(@Param("movieid")Long movieId,@Param("userid")Long userId);
	List<CheckMovie> findByUserId(Long userId);
}