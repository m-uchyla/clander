<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
	<head>
		<style>
		<%@
			include file="/resources/styles/styles.css" 
		%>
		</style>
		<meta charset="ISO-8859-1">
		<title>Clander</title>
	</head>
	<body>
        <div class="pages__wrapper--backgroundImg">
            <header>
                <nav class="navbar__container">
                    <a href="/match"><div class="navbar__logoImg--container">
                        <img class="navbar__logo" src="/resources/images/logo.svg" />
                        <h2 class="navbar__heading">Clander</h2>
                    </div></a>
                    <div class="navbar__buttons--container">
                    	<a href="/myMatches">
                        	<button class="loggedIn__btn"><img class="loggedIn__img" src="/resources/images/matches.svg"/></button>
                        </a>
                    	<a href="/messages/received">
                        	<button class="loggedIn__btn"><img class="loggedIn__img" src="/resources/images/messages.svg"/></button>
                        </a>
                    	<a href="/profile">
                        	<button class="loggedIn__btn"><img class="loggedIn__img" src="/resources/images/profile.svg"/></button>
                        </a>
                        <a href="/logout">
                        	<button class="loggedIn__btn"><img class="loggedIn__img" src="/resources/images/logout.svg"/></button>
                        </a>
                    </div>
                </nav>
            </header>
            <main>
                <div class="mainPage__singleProfile">
                    <div class="mainPage__form__container">
                        <h2 class="myMatches__heading">My Matches</h2>
                        <div class="myMatches__hrLine--settings"></div>
                        <c:forEach items="${allMatchedUsers}" var="user">          	
                            <div class="myMatches__wrapper">
                                <a href="/user/?id=${user.id}">
                                    <div class="myMatches__attributes--wrapper">
                                        <div class="profile__picture--wrapper">
                                            <img class="myMatches__picture" src="https://media.esportsedition.com/wp-content/uploads/2016/08/Hunter.jpg" />
                                        </div>
                                        <div class="myMatches__nicknameLevel--wrapper">
                                            <h3 class="myMatches__nickname">${user.username}</h3>
                                            <h4 class="myMatches__level">level ${user.characterLvl}</h4>
                                        </div>
                                        <div class="myMatches__attr--wrapper">
                                            <h4 class="myMatches__attributes">${user.game}</h4>
                                            <h4 class="myMatches__attributes">${user.characterRace}</h4>
                                            <h4 class="myMatches__attributes">${user.characterClass}</h4>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>