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
				<li>${film.id}</li>
				<li>${film.title}</li>
				<li>${film.description}</li>
				<li>${film.releaseYear}</li>
				<li>${film.languageId}</li>
				<li>${film.rentalDuration}</li>
				<li>${film.rentalRate}</li>
				<li>${film.length}</li>
				<li>${film.replacementCost}</li>
				<li>${film.rating}</li>
				<li>${film.specialFeatures}</li>
				<c:forEach var="actor" items="${film.actors}">
					<li>${actor}</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<p>No film found</p>
		</c:otherwise>
	</c:choose>
	
	<form action="editFilm.do" method=GET>
		<input type="submit" value="Edit Film Details" name="${film.id}"/>
	</form>
	
	<form action="deleteFilm.do" method=GET>
		<input type="submit" value="Delete Film" name="${film.id}"/>
	</form>

	<p>
		<a href="index.html" class="btn btn-secondary" role="button">Back to Home</a>
	</p>

</body>
</html>