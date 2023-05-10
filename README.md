# Spring MVC Film C.R.U.D. Project

## Objectives

- **What is the End Goal?**
<p>The goal of the MVC Film Site is to present the user with an web page in which they can search for, add, modify, and delete films in the database.

- **General Starter Setup :**
<p>Transfer over necessary files from FilmQuery Project and make modifications to work through a server. Add methods in the DAO to allow CRUD operations Create a Controller with appropriate annotations. Convert to Gradle project and add necessary dependencies to the build.gradle. Insert start transaction, commit, and rollback logic to the DAO. Develop XML files, JSP Views pages, HTML forms/files.  

- **User Story #1 :** 
<p> An option the user has on the home page is to search for a film via id. The user's input is sent through a form to the Controller where that id value is passed to our DAO via method call and is substituted into our mySQL SELECT statement using a bind (?). The PreparedStatement utilizes the bind to execute the query and get a ResultSet. If there is a film that matches the id entered, the film's details are displayed to the user via a JSP page. If not, "No such film found" is returned.

- **User Story #2 :** 
<p> From the home page, the user may also choose to add a film to the database. This utilizes a HTML form where the user can add the film's details. Once the submit button is initiated, the information is filtered into the appropriate method in the Controller and sent to the DAO to be added to the database using an INSERT statement to the film table. If the insert was successful, the film's details are displayed to the user with it's newly assigned id.

- **User Story #3 :** 
<p> When a film's details are displayed, the user has the option of deleting the film from the database. This is achieved with a submit button which passes the film's id (hidden) to the deleteFilm method in the Controller. The DAO takes in the id and uses it in a DELETE statement. If the deletion is successful, the user is notified. They can lookup the film again and confirm that it is no more.

- **User Story #4 :** 
<p> While the film's details are displayed, the user also has the option to update those details in the database. This is also achieved by use of the hidden id and submit button. The user is presented with a form that has the current values entered, so the user only has to input what they wish to update. When the user submits the edited information, the film is sent into the updateFilm method in the Controller and the DAO is called to updateFilm using an UPDATE statement. If the update was successful, the film's details are displayed once again to the user.

- **User Story #5 :** 
<p> Another option on the home page lets the user input a keyword to search through the film's titles and descriptions. This is similar to searching by id except the bind in the DAO uses Regular Expression (REGEX) when substituting in the user's input. Also, this time we are using two bindings rather than one. The result is much the same as well: if a film's title or description contains the keyword, that film's details will print, otherwise, "No such film found" is returned. If the film is found, the user has the option to edit or delete the film.

- **User Story #6 :** 
<p> Including the category of the film and the actors in it can be viewed as well. Using additional retrieval methods in the DAO, we can pass in the film's id and a SELECT statement using ON-common fields JOINing to other tables. The result is then returned to the caller and assigned to the appropriate field using a setter which we added to the Film object class. The user can now view the category and a list of actors (if there are any) when viewing the details of the film.

## Technologies Used
* MySQL, CRUD
* Spring MVC Framework
* Gradle, Dependencies
* Java, POJO, DAO
* HTML, JSP, XML, Servlet
* Controller, Autowired and other Annotations
* Tag Libraries, Expression Language
* Spring Tool Suite
* Github Collaboration
* Google - Stack Overflow, Oracle
* Instructor, TA and classmate help (Thank you!)

## Lessons Learned
* CRUD
* Annotations and their functions
* Transactions, Commits, and Rollbacks
* Most Prominent BrainBlocks
<br>- Figuring out to use form buttons passing in the hidden film id for the Edit and Delete options. With the help of a classmate and a couple instructors, we got the flow of information corrected and smoothed out.
<br>- RequestMapping methods in the Controller had us stumped for a while, but we eventually worked the kinks out.

## Team Members
* Samantha Addington
* Kristen Tsuboi
