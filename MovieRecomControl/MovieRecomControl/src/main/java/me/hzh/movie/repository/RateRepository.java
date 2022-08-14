package me.hzh.movie.repository;

import me.hzh.movie.domain.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RateRepository extends JpaRepository<Rate,Long> {
    @Query(value = "SELECT  COUNT(rate.movie_id) AS num FROM rate WHERE rate.user_id = :userid GROUP BY rate.user_id",nativeQuery = true)
	Integer findRateCountByUserId(@Param("userid")Long userId);
    List<Rate> findByUserId(Long userId);
    @Query(value = "SELECT  rate.movie_id AS movieId ,AVG(rate.rate) AS rate,COUNT(rate.movie_id) AS num FROM rate GROUP BY rate.movie_id",nativeQuery = true)
    List<?> findRateCount();
}
