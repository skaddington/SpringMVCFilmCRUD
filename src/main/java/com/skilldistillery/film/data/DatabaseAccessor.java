package com.skilldistillery.film.data;

import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface DatabaseAccessor {
	public Film findFilmById(int id);

	public List<Film> findFilmByKeyword(String keyword);

	public Actor findActorById(int id);

	public List<Actor> findActorsByFilmId(int id);

	public List<Film> findFilmsByActorId(int id);
	
	public String findLanguageNameByFilmId(int id);
	
	public String findCategoryByFilmId(int id);

	public Actor createActor(Actor actor);
	
	public boolean saveActor(Actor actor);
	
	public boolean deleteActor(Actor actor);
	
	public Film createFilm(Film film);
	
	public boolean updateFilm(Film film);

	public boolean deleteFilm(int id);
	
}
