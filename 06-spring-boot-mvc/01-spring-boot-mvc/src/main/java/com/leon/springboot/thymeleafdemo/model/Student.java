package com.leon.springboot.thymeleafdemo.model;

import java.util.List;

public class Student {
	private String firstName;
	private String lastName;
	private List<String> country;
	private String favoriteLanguage;
	private List<String> favoriteSystems;

	public Student() {
	}

	public Student(String firstName, String lastName, List<String> country, String favoriteLanguage, List<String> favoriteSystems) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.favoriteLanguage = favoriteLanguage;
		this.favoriteSystems = favoriteSystems;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<String> getCountry() {
		return country;
	}

	public String getListCountry() {
		return String.join(", ", country);
	}

	public void setCountry(List<String> country) {
		this.country = country;
	}

	public String getFavoriteLanguage() {
		return favoriteLanguage;
	}

	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	public List<String> getFavoriteSystems() {
		return favoriteSystems;
	}

	public String getListFavoriteSystems() {
		return String.join(", ", favoriteSystems);
	}

	public void setFavoriteSystems(List<String> favoriteSystems) {
		this.favoriteSystems = favoriteSystems;
	}
}
