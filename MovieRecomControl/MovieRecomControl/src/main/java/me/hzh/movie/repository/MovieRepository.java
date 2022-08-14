package me.hzh.movie.repository;

import me.hzh.movie.domain.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie,Long> {
	@Query(value = "SELECT  * FROM movie WHERE movie.movie_name LIKE %:moviename%",nativeQuery = true)
    List<Movie> findByMovieName(@Param("moviename")String movieName);
	@Query(value = "SELECT  * FROM movie WHERE movie.descrip LIKE %:descrip%",nativeQuery = true)
    List<Movie> findByDescrip(@Param("descrip")String descrip);
	@Query(value = "SELECT  * FROM movie WHERE movie.timelong LIKE %:timelong%",nativeQuery = true)
    List<Movie> findByTimelong(@Param("timelong")String timelong);
	@Query(value = "SELECT  * FROM movie WHERE movie.shoot LIKE %:shoot%",nativeQuery = true)
    List<Movie> findByShoot(@Param("shoot")String shoot);
	@Query(value = "SELECT  * FROM movie WHERE movie.language LIKE %:language%",nativeQuery = true)
    List<Movie> findByLanguage(@Param("language")String language);
	@Query(value = "SELECT  * FROM movie WHERE movie.actors LIKE %:actors%",nativeQuery = true)
    List<Movie> findByActor(@Param("actors")String actors);
	@Query(value = "SELECT  * FROM movie WHERE movie.director LIKE %:director%",nativeQuery = true)
    List<Movie> findByDirector(@Param("director")String director);
	@Query(value = "SELECT  * FROM movie WHERE movie.issue LIKE %:issue%",nativeQuery = true)
    List<Movie> findByIssue(@Param("issue")String issue);
}
