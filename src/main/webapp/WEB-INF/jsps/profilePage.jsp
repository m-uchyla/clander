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
                <div class="profile__form__container">
                    <form method="post" action="/profile">
                        <div class="profile__form">
                            <h2 class="profile__heading">My profile</h2>
                            <div class="profile__sections--heading_container">
                                <h3 class="profile__sections--heading" id="edit">Edit</h3>
                                <h3 class="profile__sections--settings" id="filter">Filter</h3>
                            </div>
                            <div class="profile__hrLine--settings"></div>
                            <div class="profile__edit--wrapper" id="edit_container" style="display: flex;">
	                            <div class="changePass_wrapper">
	                            	<a href="/auth/change-pass">
		                            	<div class="changePass_button">
		                            		<img title="Change Password" class="changePass_img" src="resources/images/changePass.svg">
		                            	</div>
	                            	</a>
	                            </div>
	                            <div class="profile__picture--wrapper">
	                                <img class="profile__picture" src="https://media.esportsedition.com/wp-content/uploads/2016/08/Hunter.jpg" />
	                            </div>
	                            <div class="profile__attributes--wrapper">
	                                <div class="profile__nicknameLevel--wrapper">
	                                    <input class="profile__nickname input__profile input__heading" value="${loggedUser.username}" name="userrname">
	                                    <h4 class="profile__level">level </h4><input class="input__level" value="${loggedUser.characterLvl}" name="characterLvl">
	                                </div>
	                                <div class="profile__game--wrapper">
	                                    <input class="profile__paragraphs input__profile" placeholder="Game" value="${loggedUser.game}" name="game">
	                                </div>
	                                <div class="profile__characterRace--wrapper">
	                                    <input class="profile__paragraphs input__profile" placeholder="Character race" value="${loggedUser.characterRace}" name="characterRace">
	                                </div>
	                                <div class="profile__characterClass--wrapper">
	                                    <input class="profile__paragraphs input__profile" placeholder="Character class" value="${loggedUser.characterClass}" name="characterClass">
	                                </div>
	                                <div class="profile__description--wrapper">
	                                    <textarea class="profile__description " name="description" placeholder="Your description">${loggedUser.description}</textarea>
	                                </div>
	                            </div>
	                            <div class="profile__submit">
                                	<button class="register__submit--button" type="submit">Save!</button>
                            	</div>
	                        </div>
	                        <div class="profile__filter--wrapper" id="filter_container" style="display: none;">
                            <div class="profile__filterAttributes--wrapper">
                                <div class="profile__settings--wrapper">
                                    <h4 class="profile__filter--attributes">Level: </h4>
                                    <div class="profile__filter--levelsBox">
                                        <div class="profile__levelBox">
                                            <p class="profile__level-fromTo">From: </p>
                                            <input class="profile__level--input" type="number" min="0" value="${filter.characterLvlFrom}" name="characterLvlFromFilter"/>
                                        </div>
                                        <div class="profile__levelBox">
                                            <p class="profile__level-fromTo">To: </p>
                                            <input class="profile__level--input" type="number" min="0" value="${filter.characterLvlTo}" name="characterLvlToFilter"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="profile__settings--wrapper">
                                    <h4 class="profile__filter--attributes">Game: </h4>
                                    <div class="profile__filter--games">
                                        <select name="gameFilter" class="profile__games--select">
                                            <c:forEach items="${games}" var="game">
                                            	<option>${game}</option>
                                            </c:forEach>
                                            
                                        </select>
                                    </div>
                                </div>
                                <div class="profile__settings--wrapper">
                                    <h4 class="profile__filter--attributes">Race: </h4>
                                    <div class="profile__filter--games">
                                        <select name="characterRaceFilter" class="profile__games--select">
                                            <c:forEach items="${races}" var="race">
                                            	<option>${race}</option>
                                            </c:forEach>
                                            <option>Human</option>
                                            <option>Goblin</option>
                                            <option>TwójStary</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="profile__settings--wrapper">
                                    <h4 class="profile__filter--attributes">Class: </h4>
                                    <div class="profile__filter--games">
                                        <select name="characterClassFilter" class="profile__games--select">
                                            <c:forEach items="${classes}" var="burrito">
                                            	<option>${burrito}</option>
                                            </c:forEach>
                                            <option>Warrior</option>
                                            <option>Spearman</option>
                                            <option>Knight</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="profile__submit">
                                <button class="register__submit--button" type="submit">Save!</button>
                            </div>
                            <!--  -->
                        </div>
                    </form>
                </div>
            </main>
        </div>
        <script>
        	  document.getElementById("edit").addEventListener("click", function() {
        	  document.getElementById('edit_container').style.display = "flex";
        	  document.getElementById('filter_container').style.display = "none";
        	});

        	  document.getElementById("filter").addEventListener("click", function() {
        	  document.getElementById('edit_container').style.display = "none";
          	  document.getElementById('filter_container').style.display = "flex";
        	});
        </script>
    </body>
</html>