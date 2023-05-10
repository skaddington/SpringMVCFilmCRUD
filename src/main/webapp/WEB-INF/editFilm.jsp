<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Film Details</title>
</head>
<body>

	<h2>Edit the Film's Details</h2>
	
	<div class="container">
		<form:form action="updateFilm.do" method="POST">
	    	<label for="title">Title</label>
	    	<input type="text" name="title" value="${film.title}"/>
	    	<br>
	    	<label for="description">Description</label>
	    	<input type="text" name="description" value="${film.description}"/>
	    	<br>
	    	<label for="releaseYear">Release Year</label>
	    	<input type="text" name="releaseYear" value="${film.releaseYear}"/>
	    	<br>
	    	<label for="languageId">Language</label>
	    	<select name="languageId" id="languageId-select" >
				<option value="1" selected="@(${film.languageId} == 1)">English</option>
				<option value="2" selected="@(${film.languageId} == 2)">Italian</option>
				<option value="3" selected="@(${film.languageId} == 3)">Japanese</option>
				<option value="4" selected="@(${film.languageId} == 4)">Mandarin</option>
				<option value="5" selected="@(${film.languageId} == 5)">French</option>
				<option value="6" selected="@(${film.languageId} == 6)">German</option>
			</select>
	    	<br>
	    	<label for="rentalDuration">Rental Duration</label>
	    	<input type="text" name="rentalDuration" value="${film.rentalDuration}"/>
	    	<br>
	    	<label for="rentalRate">Rental Rate</label>
	    	<input type="text" name="rentalRate" value="${film.rentalRate}"/>
	    	<br>
	    	<label for="length">Length</label>
	    	<input type="text" name="length" value="${film.length}"/>
	    	<br>
	    	<label for="replacementCost">Replacement Cost</label>
	    	<input type="text" name="replacementCost" value="${film.replacementCost}"/>
	    	<br>
	    	<label for="rating">Rating: </label>
	    	<input type="radio" id="G" name="rating" value="G" required>
	    	<label for="G">G</label>
	    	<input type="radio" id="PG" name="rating" value="PG">
	    	<label for="PG">PG</label>
	    	<input type="radio" id="PG13" name="rating" value="PG13">
	    	<label for="PG13">PG13</label>
	    	<input type="radio" id="R" name="rating" value="R">
	    	<label for="R">R</label>
	    	<input type="radio" id="NC17" name="rating" value="NC17">
	    	<label for="NC17">NC17</label>
	    	<br>
	    	<br>
			<input type="submit" value="Send Updated Film Details"/>
			<input type="hidden" name="id" value="${film.id}"/>
		</form:form>
		
		<script>
			const $select = document.querySelector('#languageId-select');
		 	$select.value = ${film.languageId}
		  
			radiobutton = ${film.rating};
			radiobutton.checked = true;
		</script>
	</div>
	
	<p>
		<a href="index.html" class="btn btn-secondary" role="button">Cancel Edit</a>
	</p>

</body>
</html>