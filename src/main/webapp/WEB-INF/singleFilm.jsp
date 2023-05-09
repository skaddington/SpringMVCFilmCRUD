<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC Film Site</title>
</head>
<body>

	<h1>MVC Film Site</h1>

	<c:choose>
		<c:when test="${! empty film}">
			<ul>
				<li>Id: ${film.id}</li>
				<li>Title: ${film.title}</li>
				<li>Description: ${film.description}</li>
				<li>Release Year: ${film.releaseYear}</li>
				<li>Language Id: ${film.languageId}</li>
				<li>Rental Duration: ${film.rentalDuration}</li>
				<li>Rental Rate: ${film.rentalRate}</li>
				<li>Length (in minutes): ${film.length}</li>
				<li>Replacement Cost: ${film.replacementCost}</li>
				<li>Rating: ${film.rating}</li>
				<li>Special Features: ${film.specialFeatures}</li>
				<li>Actors: </li>
				<ul>
 					<c:forEach var="actor" items="${film.actors}">
						<li>${actor}</li>
					</c:forEach>
				</ul>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
	
	<form action="editFilm.do" method=GET>
		<input type="submit" value="Edit Film Details"/>
		<input type="hidden" name="id" value="${film.id }"/>
	</form>
	<br>
	<form action="deleteFilm.do" method=POST>
		<input type="submit" value="Delete Film"/>
		<input type="hidden" name="delete" value="${film.id }"/>
		
	</form>

	<p>
		<a href="index.html" class="btn btn-secondary" role="button">Back to Home</a>
	</p>

</body>
</html>