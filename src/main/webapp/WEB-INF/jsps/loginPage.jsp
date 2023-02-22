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
                    <a href="/"><div class="navbar__logoImg--container">
                        <img class="navbar__logo" src="/resources/images/logo.svg" />
                        <h2 class="navbar__heading">Clander</h2>
                    </div></a>
                    <div class="navbar__buttons--container">
                        <div></div>
                        <div></div>
                    </div>
                </nav>
            </header>
            <main>
                <div class="login__form__container">
                    
                    <form method="post" action="/process_login">
                        <div class="login__form">
                            <h2 class="login__heading">Login</h2>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="text" name="username" placeholder="Login" />
                                <label class="login__input--floating-label">Login</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="password" id="password" name="password" placeholder="Password" />
                                <label class="login__input--floating-label">Password</label>
                            </div>
                            <div class="login__submit">
                                <button class="login__submit--button" type="submit">Login</button>
                            </div>
                        </div>
                    </form>
                    <div class="login__form--options">
                        <a class="login__links" href="/forgotPass">Forgot password?</a>
                        <a class="login__links" href="/auth/register">Register account.</a>
                    </div>
                </div>
            </main>
        </div>
    </body>
</html>