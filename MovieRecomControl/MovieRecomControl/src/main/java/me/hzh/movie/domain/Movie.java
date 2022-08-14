package me.hzh.movie.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ima_name",nullable = false,length = 20)
    private String imgName;
    @Column(name = "movie_name",nullable = false,length = 128)
    private String movieName;
    @Column(name = "descrip",length = 255)
    private String descrip;
    
    @Column(name = "timelong",length = 128)
    private String timeLong;
    @Column(name = "shoot",length = 255)
    private String shoot;
    @Column(name = "language",length = 128)
    private String language;
    @Column(name = "actors",length = 255)
    private String actors;
    @Column(name = "director",length = 255)
    private String director;
    @Column(name = "issue",length = 20)
    private String issue;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getTimeLong() {
		return timeLong;
	}
	public void setTimeLong(String timeLong) {
		this.timeLong = timeLong;
	}
	public String getShoot() {
		return shoot;
	}
	public void setShoot(String shoot) {
		this.shoot = shoot;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
    
}
