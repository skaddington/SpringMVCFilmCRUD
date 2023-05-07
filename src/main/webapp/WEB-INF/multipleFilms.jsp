<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films</title>
</head>
<body>
	<div class="container">
		<ul>
			<c:forEach var="film" items="${films }">
				<ul>
					<li>${film.filmId}</li>
					<li>${film.title}</li>
					<li>${film.description}</li>
					<li>${film.releaseYear}</li>
					<li>${film.language}</li>
					<li>${film.rentalDuration}</li>
					<li>${film.rentalRate}</li>
					<li>${film.length}</li>
					<li>${film.replacementCost}</li>
					<li>${film.rating}</li>
					<li>${film.specialFeatures}</li>
 					<li>${film.actors}</li>
					<br>
 				</ul>
			</c:forEach>
		</ul>

		<p>
			<a href="index.html" class="btn btn-secondary" role="button">Back
				to Home</a>
		</p>
	</div>
</body>
</html>