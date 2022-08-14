package me.hzh.movie.service.impl;

import me.hzh.movie.domain.Movie;
import me.hzh.movie.domain.Type;
import me.hzh.movie.repository.MovieRepository;
import me.hzh.movie.repository.TypeRepository;
import me.hzh.movie.service.MovieService;
import me.hzh.movie.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private TypeRepository typeRepository;
    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }
	@Override
	public List<Type> findByfilmtype(String type) {
		// TODO Auto-generated method stub
		return typeRepository.findByfilmtype(type);
	}

}
