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
                <div class="register__form__container">
                    
                    <form method="POST" action="/auth/change-pass">
                        <div class="login__form">
                            <h2 class="changePass__heading">Change Password</h2>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="text" name="username" placeholder="Username" />
                                <label class="login__input--floating-label">Username</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="password" name="oldPassword" placeholder="Old password" />
                                <label class="login__input--floating-label">Old password</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="password" id="newPassword" name="newPassword" placeholder="New Password" />
                                <label class="login__input--floating-label">New password</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="password" id="password" name="passwordConfirmation" placeholder="Password confirmation" />
                                <label class="login__input--floating-label">Repeat password</label>
                            </div>
                            <div class="login__submit">
                                <button class="register__submit--button" type="submit">Change</button>
                            </div>
                        </div>
                    </form>
                </div>
            </main>
        </div>
    </body>
</html>