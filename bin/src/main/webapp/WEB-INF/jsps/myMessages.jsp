<%@page import="com.fdmgroup.clander.models.Message"%>
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
		<form action="/messages/inbox" id="inbox-form" method="get"></form>
        <form action="/messages/outbox" id="outbox-form" method="get"></form>
        
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
                <div class="myMessages__form__container">
                        <div class="myMessage__form">
                            <h2 class="profile__heading">My Messages</h2>
                            <div class="myMessages__inboxOutbox--container">
                                <div class="myMessages__sections--heading_container">
                                	<a href="/messages/received">
                                    	<button class="myMessage-btn"><h3 class="myMessages__sections--heading" id="inbox">Inbox</h3></button>
                                    </a>
                                    <a href="/messages/sent">
                                    	<button class="myMessage-btn"><h3 class="myMessages__sections--heading" id="outbox">Outbox</h3></button>
                                    </a>
                                    <%
									Object sender = request.getAttribute("matchedUsers");
									if (sender == null) {
									%>
                                    <a href="/newMessage">
                                    <button class="myMessage-btn">
                                    	<h3 class="myMessages__sections--heading-newMessage" id="newMsg" style="display: block;">New Message</h3>
                                    </button>
                                    </a>
                                    <%
									} else {
									%>
                                    <button form="messageForm" class="myMessage-btn">
                                    	<h3 class="myMessages__sections--heading-newMessage" id="sendMsg" style="display: block;">Send</h3>
                                    </button>
                                    <%
									}
									%>
                                </div>
                            </div>
                            <div class="myMessages__hrLine--horizontal"></div>
                            <%
							Object received = request.getAttribute("allMessagesReceived");
							if (received != null) {
							%>
                            <div class="myMessage__messages-container" id="inboxMsgs" style="display: flex;">
                                <div class="myMessages__inboxOutbox">
                                	<c:forEach items="${allMessagesReceived}" var="msg">
                                	<a href="/message/received/?id=${msg.getId()}">
                                	<div class="myMessages__singleMessage" id="singleMessage${msg.getId()}">
                                        <div class="myMessage__headings">
                                            <h5 class="myMessages__singleMessage--heading">From:</h5>
                                            <p class="myMessages__singleMessage--username">${msg.sender.username}</p>
                                        </div>
                                        <p class="myMessages__singleMessage--paragraph">${msg.content}</p>
                                    </div>
                                    </a>
                                	</c:forEach>
                                </div>
                                <div class="myMessages__read-newMessage">
                                    <div class="myMessages__readNewMessage-fromTo">
                                        <div class="myMessage__headings">
                                            <h5 class="myMessages__singleMessage--heading-readNewMessage">From:</h5>
                                            <p class="myMessages__singleMessage--username-readNewMessage">${selectedMsg.sender.username}</p>
                                        </div>
                                    </div>
                                    <%
									Message selectedMsg = (Message)request.getAttribute("selectedMsg");
                                    if(selectedMsg != null){
										if (selectedMsg.getType().equalsIgnoreCase("REPORT")) {
										%>
	                                    <div class="myMessages__readNewMessage-messageWindow">
	                                        <p class="myMessages__singleMessage--messageContent">
	                                            ${selectedMsg.content}
	                                        </p>
	                                        <div class="login__form--options">
	                                        <form action="/deleteMessage" method="POST">
	                                        	<input type="hidden" name="id" value="${selectedMsg.id}">
	                                        	<button class="myMessage__decline--btn">Decline</button>
	                                        </form>
	                                        <form action="/ban" method="POST">
	                                        	<input type="hidden" name="messID" value="${selectedMsg.id}">
	                                        	<input type="hidden" name="id" value="${reportedID}">
	                                        	<input type="hidden" name="type" value="TEMP">	
	                                        	<button class="myMessage__accept--btn">Ban</button>
	                                        </form>
	                                        <form action="/ban" method="POST">
	                                        	<input type="hidden" name="messID" value="${selectedMsg.id}">
	                                        	<input type="hidden" name="id" value="${reportedID}">
	                                        	<input type="hidden" name="type" value="PERM">	
	                                        	<button class="myMessage__accept--btn">Perm ban</button>
	                                        </form>
	                                        </div>
	                                    </div>
	                                    <%
										} else if (selectedMsg.getType().equalsIgnoreCase("INVITE")){
										%>
										<div class="myMessages__readNewMessage-messageWindow">
	                                        <p class="myMessages__singleMessage--messageContent">
	                                            ${selectedMsg.content}
	                                        </p>
	                                        <div class="login__form--options">
	                                        	<button class="myMessage__decline--btn">Decline</button>
	                                        	<button class="myMessage__accept--btn">Accept</button>
	                                        </div>
	                                    </div>
	                                    <%
										} else {
										%>
										<div class="myMessages__readNewMessage-messageWindow">
	                                        <p class="myMessages__singleMessage--messageContent">
	                                            ${selectedMsg.content}
	                                        </p>
	                                    </div>
	                                    <%
										}
                                    }
									%>
                                </div>
                            </div>
                            <%
							} else {
							%>
                            <div class="myMessage__messages-container" id="outboxMsgs" style="display: flex;">
                                <div class="myMessages__inboxOutbox">
                                	<c:forEach items="${allMessagesSent}" var="msg">
                                	<a href="/message/sent/?id=${msg.getId()}">
                                	<div class="myMessages__singleMessage" id="singleMessage${msg.getId()}">
                                        <div class="myMessage__headings">
                                            <h5 class="myMessages__singleMessage--heading">To:</h5>
                                            <p class="myMessages__singleMessage--username">${msg.receiver.username}</p>
                                        </div>
                                        <p class="myMessages__singleMessage--paragraph">${msg.content}</p>
                                    </div>
                                    </a>
                                	</c:forEach>
                                </div>
                                <%
								Object receivers = request.getAttribute("matchedUsers");
								if (receivers == null) {
								%>
                                <div class="myMessages__read-newMessage">
                                    <div class="myMessages__readNewMessage-fromTo">
                                        <div class="myMessage__headings">
                                            <h5 class="myMessages__singleMessage--heading-readNewMessage">To:</h5>
                                            <p class="myMessages__singleMessage--username-readNewMessage">${selectedMsg.receiver.username}</p>
                                        </div>
                                    </div>
                                    <div class="myMessages__readNewMessage-messageWindow">
                                        <p class="myMessages__singleMessage--messageContent">
                                            ${selectedMsg.content}
                                        </p>
                                    </div>
                                </div>
                                <%
								} else {
								%>
								<div class="myMessages__read-newMessage">
									<form action="/newMessage" method="POST" name="messageForm" id ="messageForm">
	                                    <div class="myMessages__readNewMessage-fromTo">
	                                        <div class="myMessage__headings">
	                                            <h5 class="myMessages__singleMessage--heading-readNewMessage">To:</h5>
	                                            <select name="receiver" class="profile__games--select">
	                                            	<c:forEach items="${matchedUsers}" var="matchedUser">
	                                            	<option value="${matchedUser.id}">${matchedUser.username}</option>
	                                            	</c:forEach>
	                                        	</select>
	                                        </div>
	                                    </div>
	                                    <div class="myMessages__readNewMessage-messageWindow">
	                                        <textarea name="content" class="myMessages__singleMessage--messageContent"></textarea>
	                                    </div>
                                    </form>
                                </div>
								<%
								}
								%> 
                            </div>
                            <%
							}
							%>  
                        </div>
                </div>
            </main>
        </div>
        <script>
        	  document.getElementById("inbox").addEventListener("click", function() {
        	  document.getElementById("inboxMsgs").style.display = "flex";
        	  document.getElementById("outboxMsgs").style.display = "none";
        	});

        	document.getElementById("outbox").addEventListener("click", function() {
        	  document.getElementById("inboxMsgs").style.display = "none";
        	  document.getElementById("outboxMsgs").style.display = "flex";
        	});
        	
        	document.getElementById("singleMessage"+${selectedMsg.id}).style.border = "2px solid rgba(147, 6, 133, 0.6)";
        </script>
    </body>
</html>