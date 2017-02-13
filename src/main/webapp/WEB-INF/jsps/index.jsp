<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Chat - Customer Module</title>
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet" type="text/css"/>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div id="wrapper">
    <div id="menu">
        <p class="welcome">Welcome, <b><sec:authentication property="principal.name"/></b></p>
        <p class="logout"><a id="exit" href="#">Exit Chat</a></p>
        <div style="clear:both"></div>
    </div>

    <div id="chatbox"></div>

    <sec:authorize access="hasRole('WRITER')">
        <form name="message" action="">
            <input name="usermsg" type="text" id="usermsg" size="63"/>
            <input name="submitmsg" type="submit" id="submitmsg" value="Send"/>
        </form>
    </sec:authorize>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //If user wants to end session
        $("#exit").click(function () {
            var exit = confirm("Are you sure you want to end the session?");
            if (exit == true) {
                window.location = '/login?logout';
            }
        });
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
        //If user submits the form
        $("#submitmsg").click(function () {
            var msg = $("#usermsg");
            var clientmsg = msg.val();
            $.post("/messages/create", {text: clientmsg});
            msg.attr("value", "");
            return false;
        });
        //Load the file containing the chat log
        function loadLog() {

            $.ajax({
                url: "/messages",
                cache: false,
                success: function (html) {
                    $("#chatbox").html(html); //Insert chat log into the #chatbox div
                }
            });
        }

        setInterval(loadLog, 2500);
    });
</script>
</body>
</html>