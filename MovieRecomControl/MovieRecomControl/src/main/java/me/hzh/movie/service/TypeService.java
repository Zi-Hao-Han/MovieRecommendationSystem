
package me.hzh.movie.service;

import me.hzh.movie.domain.Type;

import java.util.List;

import org.springframework.data.repository.query.Param;

public interface TypeService {
	List<Type> findByfilmtype(@Param("type")String type);
}
