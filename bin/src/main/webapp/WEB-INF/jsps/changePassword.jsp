<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>changepass</title>
		<meta name="viewport" content="width=device-width,initial-scale=1">
	</head>
	<body>
		<div class="singleCard">
			<form class="card login"  method="POST" action="/auth/change-pass">
				 <h1>CHANGE PASSWORD</h1>
				 <div class="input">
					 <p>Email:</p>
					 <input type="text" name="username" id="username">
				 </div>
				 <div class="input">
					 <p>Old password:</p>
					 <input type="password" name="oldPassword">
				 </div>
				 <div class="input">
					 <p>New Password:</p>
					 <input type="password" name="newPassword" id="password">
				 </div>
				 <div class="input">
					 <p>Confirm new password:</p>
					 <input type="password" name="passwordConfirmation" id="confirm_password">
				 </div>
				 <div class="center">
				 	<input class="button" type="submit" value="Change password">
				 </div>
			</form>
			
		</div>
	</body>
	<script>
	var password = document.getElementById("password")
	  , confirm_password = document.getElementById("confirm_password");

	function validatePassword(){
	  if(password.value != confirm_password.value) {
	    confirm_password.setCustomValidity("Passwords Don't Match");
	  } else {
	    confirm_password.setCustomValidity('');
	  }
	}

	password.onchange = validatePassword;
	confirm_password.onkeyup = validatePassword;
	</script>
</html>