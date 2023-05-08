<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edited Film Details</title>
</head>
<body>

<h2>Edit the Film's Details</h2>

<form action="EditFilm.do" method="GET">
    Current Title (${film.title}): <input type="text" name="title"/> 
    <br>
    Current Description (${film.description}): <input type="text" name="description"/> 
    <br>
    Current Release Year (${film.releaseYear}): <input type="text" name="releaseYear"/> 
    <br>
   	Current Language ID (${film.languageId}): <input type="text" name="languageId"/> 
    <br>
    Current Rental Duration (${film.rentalDuration}): <input type="text" name="rentalDuration"/> 
    <br>
    Current Rental Rate (${film.rentalRate}): <input type="text" name="rentalRate"/> 
    <br>
    Current Length (${film.length}): <input type="text" name="length"/> 
    <br>
    Current Replacement Cost (${film.replacementCost}): <input type="text" name="replacementCost"/> 
    <br>
    Current Rating (${film.rating}): <input type="text" name="rating"/> 
    <br>
    <input type="submit" value="Edit Film Details" />
  </form>

</body>
</html>