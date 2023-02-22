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
		<title>ClanDer</title>
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
                        <form class="positionAbso" method="post">
                            <div class="profile__form">
                                <div class="profile__picture--wrapper">
                                    <img class="profile__picture" src="https://media.tenor.com/lx2WSGRk8bcAAAAC/pulp-fiction-john-travolta.gif" />
                                </div>
                                <div class="profile__attributes--wrapper">
                                    <div class="noMore__nicknameLevel--wrapper">
                                        <h3 class="noMore__nicknames">No more Profiles!</h3>
                                        <h4 class="profile__level">level -</h4>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <c:forEach items="${profiles}" var="user">
                <div class="mainPage__singleProfile">
                    <div class="mainPage__form__container">
			                    <div class="report_dots">
			                    	<form method="post" action="/report">
			                    	<input type="hidden" name="profileID" value="${user.getId()}"/>
				               		<button class="report_btn">
				               			<img title="Report" class="report_img" src="/resources/images/report.svg">
				               		</button>
				               		</form>
				               	</div>                       	
	                            <div class="profile__form">
                                <div class="profile__picture--wrapper">
                                    <img class="profile__picture" src="https://media.esportsedition.com/wp-content/uploads/2016/08/Hunter.jpg" />
                                </div>
                                <div class="profile__attributes--wrapper">
                                    <div class="profile__nicknameLevel--wrapper">
                                        <h3 class="profile__nickname">${user.username}</h3>
                                        <h4 class="profile__level">level ${user.characterLvl}</h4>
                                    </div>
                                    <div class="profile__game--wrapper">
                                        <p class="profile__paragraphs">${user.game}</p>
                                    </div>
                                    <div class="profile__characterRace--wrapper">
                                        <p class="profile__paragraphs">${user.characterRace}</p>
                                    </div>
                                    <div class="profile__characterClass--wrapper">
                                        <p class="profile__paragraphs">${user.characterClass}</p>
                                    </div>
                                    <div class="profile__description--wrapper">
                                        <p class="mainPage__profile__description">
                                            ${user.description}
                                        </p>
                                    </div>
                                </div>
                            </div>
                    </div>
                    <div class="mainPage__profile--buttons">
                    	<form method="post" action="/match-no">
                    		<input type="hidden" name="profileID" value="${user.getId()}"/>
                    		<button class="mainPage__profile--btn no-btn"><img class="mainPage__btn-color" src="/resources/images/shield_no.svg"></button>
                    	</form>
                    	<form method="post" action="/match-yes">
                    		<input type="hidden" name="profileID" value="${user.getId()}"/>
                    		<button class="mainPage__profile--btn yes-btn"><img class="mainPage__btn-color" src="/resources/images/shield_yes.svg"></button>
                    	</form>
                    </div>
                </div>
                </c:forEach>
            </main>
            <footer>

            </footer>
        </div>
    </body>
</html>