package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Component
public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PASS = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int id) {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.* FROM film WHERE film.id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String desc = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");

				film = new Film(id, title, desc, releaseYear, languageId, rentDur, rate, length, repCost, rating,
						features);
				film.setActors(findActorsByFilmId(id));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	public List<Film> findFilmByKeyword(String keyword) {
		List<Film> filmList = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.*  FROM film WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");

				Film film = new Film(id, title, desc, releaseYear, languageId, rentDur, rate, length, repCost, rating,
						features);
				filmList.add(film);
				film.setActors(findActorsByFilmId(id));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT actor.* FROM actor JOIN film_actor ON actor.id = film_actor.actor_id WHERE film_actor.film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				Actor actor = new Actor(id, firstName, lastName);

				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);

			String sql = "SELECT * FROM actor WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				actor = new Actor(actorId, firstName, lastName);

				actor.setFilms(findFilmsByActorId(actorId));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.*, language.name FROM film JOIN film_actor ON film.id = film_actor.film_id JOIN language ON film.language_id = language.id WHERE film_actor.actor_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actorId);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				int releaseYear = rs.getInt("release_year");
				int languageId = rs.getInt("language_id");
				String language = rs.getString("name");
				int rentDur = rs.getInt("rental_duration");
				double rate = rs.getDouble("rental_rate");
				int length = rs.getInt("length");
				double repCost = rs.getDouble("replacement_cost");
				String rating = rs.getString("rating");
				String features = rs.getString("special_features");

				Film film = new Film(id, title, desc, releaseYear, languageId, rentDur, rate, length, repCost, rating,
						features);
				films.add(film);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public Actor createActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO actor (first_name, last_name) " + " VALUES (?,?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newActorId = keys.getInt(1);
					actor.setId(newActorId);
					if (actor.getFilms() != null && actor.getFilms().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Film film : actor.getFilms()) {
							stmt.setInt(1, film.getId());
							stmt.setInt(2, newActorId);
							updateCount = stmt.executeUpdate();
						}
					}
				}
				keys.close();
			} else {
				actor = null;
			}
			conn.commit(); // COMMIT TRANSACTION
			stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + actor);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public boolean saveActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "UPDATE actor SET first_name=?, last_name=? " + " WHERE id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, actor.getFirstName());
			stmt.setString(2, actor.getLastName());
			stmt.setInt(3, actor.getId());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film_actor WHERE actor_id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, actor.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Film film : actor.getFilms()) {
					stmt.setInt(1, film.getId());
					stmt.setInt(2, actor.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
				stmt.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteActor(Actor actor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "DELETE FROM film_actor WHERE actor_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());

			int updateCount = stmt.executeUpdate();

			sql = "DELETE FROM actor WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, actor.getId());
			updateCount = stmt.executeUpdate();

			conn.commit(); // COMMIT TRANSACTION
			stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());

			int updateCount = stmt.executeUpdate();

			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					System.out.println("Added new film to db" + updateCount);
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					System.out.println("new film id" + newFilmId);
					if (film.getActors() != null && film.getActors().size() > 0) {
						sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
						stmt = conn.prepareStatement(sql);
						for (Actor actor : film.getActors()) {
							stmt.setInt(1, newFilmId);
							stmt.setInt(2, actor.getId());
							updateCount = stmt.executeUpdate();
						}
					}
				}
				keys.close();
			} else {
				film = null;
			}
			conn.commit(); // COMMIT TRANSACTION
			stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + film);
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public boolean saveFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "UPDATE film SET title=?, description=?, release_year=?, language_id=?, rental_duration=?, rental_rate=?, length=?, replacement_cost=?, rating=? WHERE id=?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setInt(10, film.getId());

			System.out.println("Prepared Statement/Bind works");

			int updateCount = stmt.executeUpdate();

			System.out.println("executeUpdate works");

			if (updateCount == 1) {
				// Replace actor's film list
				sql = "DELETE FROM film WHERE id = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, film.getId());
				updateCount = stmt.executeUpdate();
				sql = "INSERT INTO film_actor (film_id, actor_id) VALUES (?,?)";
				stmt = conn.prepareStatement(sql);
				for (Actor actor : film.getActors()) {
					stmt.setInt(1, actor.getId());
					stmt.setInt(2, film.getId());
					updateCount = stmt.executeUpdate();
				}
				conn.commit(); // COMMIT TRANSACTION
				stmt.close();
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean deleteFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "DELETE FROM film_actor WHERE film_id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());

			int updateCount = stmt.executeUpdate();

			sql = "DELETE FROM film WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, film.getId());
			updateCount = stmt.executeUpdate();

			conn.commit(); // COMMIT TRANSACTION
			stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
