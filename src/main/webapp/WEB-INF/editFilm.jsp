<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edited Film Details</title>
</head>
<body>

<h2>Edit the Film's Details</h2>

<form:form action="updateFilm.do" method="POST">
    <label for="title">Title</label>
    <input type="text" name="title" value="${film.title }"/>
    <br>
    <label for="description">Description</label>
    <input type="text" name="description" value="${film.description }"/>
    <br>
    <label for="releaseYear">Release Year</label>
    <input type="text" name="releaseYear" value="${film.releaseYear }"/>
    <br>
    <label for="languageId">Language ID</label>
    <input type="text" name="languageId" value="${film.languageId }"/>
    <br>
    <label for="rentalDuration">Rental Duration</label>
    <input type="text" name="rentalDuration" value="${film.rentalDuration}"/>
    <br>
    <label for="rentalRate">Rental Rate</label>
    <input type="text" name="rentalRate" value="${film.rentalRate }"/>
    <br>
    <label for="length">Length</label>
    <input type="text" name="length" value="${film.length }"/>
    <br>
    <label for="replacementCost">Replacement Cost</label>
    <input type="text" name="replacementCost" value="${film.replacementCost }"/>
    <br>
    <label for="rating">Rating</label>
    <input type="text" name="rating" value="${film.rating }"/>
    <br>
		<input type="submit" value="Send Updated Film Details"/>
		<input type="hidden" name="id" value="${film.id }"/>
  </form:form>

</body>
</html>