package com.stackroute.moviecruiserapp.domain;




import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Document
@ApiModel(description="Details about the movie")
public class Movie {

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Movie(String id, String title, String overview, Integer vote_average, String poster_path, String comments,
			String username) {
		super();
		this.id = id;
		this.title = title;
		this.overview = overview;
		this.vote_average = vote_average;
		this.poster_path = poster_path;
		this.comments = comments;
		this.username = username;
	}

	

	@ApiModelProperty(notes="Primary key for movie identification")
	private String id;
    
	@ApiModelProperty(notes="Title of the movie")
	private String title;
     
	@ApiModelProperty(notes="overview of the movie")
	private String overview;
	
	@ApiModelProperty(notes="Rating")
	private Integer vote_average;
    
	@ApiModelProperty(notes="Image path")
	private String poster_path;
    
	@ApiModelProperty(notes="User Comments")
	private String comments;
	
	@ApiModelProperty(notes="User of the movie")
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public Integer getVote_average() {
		return vote_average;
	}

	public void setVote_average(Integer vote_average) {
		this.vote_average = vote_average;
	}

	public String getPoster_path() {
		return poster_path;
	}

	public void setPoster_path(String poster_path) {
		this.poster_path = poster_path;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((overview == null) ? 0 : overview.hashCode());
		result = prime * result + ((poster_path == null) ? 0 : poster_path.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((vote_average == null) ? 0 : vote_average.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (overview == null) {
			if (other.overview != null)
				return false;
		} else if (!overview.equals(other.overview))
			return false;
		if (poster_path == null) {
			if (other.poster_path != null)
				return false;
		} else if (!poster_path.equals(other.poster_path))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (vote_average == null) {
			if (other.vote_average != null)
				return false;
		} else if (!vote_average.equals(other.vote_average))
			return false;
		return true;
	}



}
