package me.hzh.movie.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie_id",nullable = false,length = 20)
    private Long movieId;
    @Column(name = "user_id",nullable = false,length = 20)
    private Long userId;
    @Column(name = "rate",nullable = false)
    private Double rate;
    @Transient
    private Integer num;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
    
}
