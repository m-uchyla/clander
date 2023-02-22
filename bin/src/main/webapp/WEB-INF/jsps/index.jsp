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
	                    <div class="navbar__logoImg--container">
	                    	<a href="/">
	                        	<img class="navbar__logo" src="/resources/images/logo.svg" />
                            </a>
	                        <h2 class="navbar__heading">Clander</h2>
	                    </div>
                    <div class="navbar__buttons--container">
	                    <%
						Object loggedInObj = request.getAttribute("loggedIn");
						if (loggedInObj == null || !(Boolean) loggedInObj) {
						%>
                        <a href="/auth/login">
                        	<button class="mainPage__login-btn">Login</button>
                        </a>
                        <a href="/auth/register">
                        	<button class="mainPage__register-btn">Register</button>
                        </a>
                        <%
						} else {
						%>
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
						<%
						}
						%>
                    </div>
                </nav>
            </header>
            <main>
                <div class="mainPage__container">
                    <div class="mainPage__heading--container">
                        <h1 class="mainPage__heading--onePart">Find</h1>
                        <h2 class="mainPage__heading--twoPart">Your</h2>
                        <h1 class="mainPage__heading--onePart">companion!</h1>
                    </div>
                    <a href="/auth/register">
                    	<button class="mainPage__account-btn">Create Account!</button>
                    </a>
                </div>
            </main>
            <footer>

            </footer>
        </div>
    </body>
</html>