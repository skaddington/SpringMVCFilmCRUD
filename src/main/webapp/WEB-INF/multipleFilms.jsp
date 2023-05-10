<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Films by Keyword</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty film}">
		
		<h2>Films found containing your keyword/phrase</h2>

		<c:forEach var="film" items="${films}">
			<ul>
				<li>Id: ${film.id}</li>
				<li>Title: ${film.title}</li>
				<li>Description: ${film.description}</li>
				<li>Release Year: ${film.releaseYear}</li>
				<li>Language: ${film.languageName} (Id: ${film.languageId})</li>
				<li>Rental Duration: ${film.rentalDuration}</li>
				<li>Rental Rate: <fmt:formatNumber type="currency" value="${film.rentalRate}"/></li>
				<li>Length (in minutes): ${film.length}</li>
				<li>Replacement Cost: <fmt:formatNumber type="currency" value="${film.replacementCost}"/></li>
				<li>Rating: ${film.rating}</li>
				<li>Category: ${film.category}</li>
				<li>Special Features: ${film.specialFeatures}</li>
				<li>Actors: </li>
				<ul>
 					<c:forEach var="actor" items="${film.actors}">
						<li>${actor}</li>
					</c:forEach>
				</ul>
 			</ul>
 			<form action="editFilm.do" method=GET>
				<input type="submit" value="Edit ${film.title}"/>
				<input type="hidden" name="id" value="${film.id }"/>
			</form>
			<br>
			<form action="deleteFilm.do" method=POST>
				<input type="submit" value="Delete ${film.title}"/>
				<input type="hidden" name="delete" value="${film.id }"/>
		
			</form>
			<br>
		</c:forEach>
		</c:when>
		<c:otherwise>
			<p>
				<h2>No film found</h2>
			</p>
		</c:otherwise>
	</c:choose>

	<p>
		<a href="index.html" class="btn btn-secondary" role="button">Back to Home</a>
	</p>
		
</body>
</html>