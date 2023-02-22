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
                <div class="register__form__container">
                    
                    <form method="post" action="/auth/register">
                        <div class="login__form">
                            <h2 class="login__heading">Register</h2>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="email" name="email" placeholder="Email" />
                                <label class="login__input--floating-label">E-mail</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="text" name="username" placeholder="Username" />
                                <label class="login__input--floating-label">Username</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="password" id="password" name="password" placeholder="Password" />
                                <label class="login__input--floating-label">Create Password</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="login__inputs login__input--floating-label-input" type="password" id="password" name="confirmPassword" placeholder="password" />
                                <label class="login__input--floating-label">Confirm Password</label>
                            </div>
                            <div class="login__submit">
                                <button class="register__submit--button" type="submit">Register!</button>
                            </div>
                        </div>
                    </form>
                </div>
            </main>
        </div>
    </body>
</html>