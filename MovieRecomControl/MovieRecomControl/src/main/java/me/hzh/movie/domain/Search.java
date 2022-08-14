package me.hzh.movie.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Search{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movieid",nullable = false,length = 255)
    private String movieId;
    @Column(name = "userid",nullable = false,length = 20)
    private Long userId;
    @Column(name = "content",nullable = false,length = 255)
    private String content;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

    
}
