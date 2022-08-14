package me.hzh.movie.repository;

import me.hzh.movie.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {
	@Query(value = "SELECT  * FROM type WHERE type.type LIKE %:type%",nativeQuery = true)
    List<Type> findByfilmtype(@Param("type")String type);
}
