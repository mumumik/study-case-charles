<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix = "rms" uri = "/WEB-INF/link.tld"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">

  <title>RMS</title>
  <meta name="description" content="Index">
  <meta name="author" content="Mitrais">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
  <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
  <rms:link type="stylesheet" href="css/styles.css?v=1.0"/>

  <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.js"></script>
  <![endif]-->
</head>

<body>
    <div class="mdl-layout mdl-js-layout mdl-color--grey-100 box-center">
    	<main class="mdl-layout__content">
    		<input type="button" value="Add"
					onclick="window.location.href='form'; return false;"
					class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent"
			 />
    		<table class="mdl-data-table mdl-js-data-table mdl-data-table--selectable mdl-shadow--2dp">
              <thead>
                <tr>
                  <th class="mdl-data-table__cell--non-numeric">User Name</th>
                  <th>Password</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
              <c:forEach items = "${users}" var="user">
              
              		<!-- set up a delete link for each user -->
					<c:url var="deleteLink" value="delete">
						<c:param name="userId" value="${user.id}" />
					</c:url>
					
					<!-- set up an update link for each user -->
					<c:url var="updateLink" value="load">
						<c:param name="userId" value="${user.id}" />
					</c:url>
					
                  	<tr>
                    	<td class="mdl-data-table__cell--non-numeric"><c:out value = "${user.userName}"/></td>
                    	<td><c:out value = "${user.password}"/></td>
                    	<td>
                    		<a href="${updateLink}">Update</a>
                    		 | 
                    		<a href="${deleteLink}" onclick="if (!(confirm('Are you sure want to delete this user?'))) return false">Delete</a>
                    	</td>
                  	</tr>
              </c:forEach>
              </tbody>
            </table>
    	</main>
    </div>
  <script src="js/scripts.js"></script>
</body>
</html>
