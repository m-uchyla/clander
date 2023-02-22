<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Register</title>
	</head>
	<body>
       <div class="login__page-wrapper">
           <main>
                <div class="register__form__container">
                    <form method="post" action="/auth/register">
                        <div class="login__form">
                        	<h2 class="login__title">Sign Up</h2>
                            <div class="login__input--wrapper">
                                <input class="register__inputs register__input--floating-label-input" type="email" name="email" path="email" placeholder="Enter e-mail"/>
                                <label class="register__input--floating-label register__input--floating-label-login" path="email">Enter e-mail</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="register__inputs register__input--floating-label-input" type="username" name="username" path="username" placeholder="Enter Username"/>
                                <label class="register__input--floating-label register__input--floating-label-username" path="username">Enter Username</label>
                            	
                            </div>
                            <div class="login__input--wrapper">
                                <input class="register__inputs register__input--floating-label-input" type="password" id="password" name="password" path="password" placeholder="Create Password"/>
                                <label class="register__input--floating-label" path="password">Create password</label>
                            </div>
                            <div class="login__input--wrapper">
                                <input class="register__inputs register__input--floating-label-input" type="password" id="password" name="confirmPassword" path="confirmPassword" placeholder="Confirm Password"/>
                                <label class="register__input--floating-label" path="confirmPassword">Confirm password</label>
                            </div>
                            <div class="login__submit">
                                <button class="register__submit--button" type="submit" path="submit">Create Account</button>
                            </div>
                        </div>
                    </form>              
                </div>
            </main>
        </div>

    </body>
</html>