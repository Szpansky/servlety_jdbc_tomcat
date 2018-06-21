<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Boolean isSesja = false;
    String logedUser = "";
    if (session != null && session.getAttribute("userID") != null) {
        isSesja = true;
        if (session.getAttribute("userID").toString().equals("admin")) {
            logedUser = "admin";
        }
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="pl">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <script src="jquery-3.3.1.min.js"></script>
    <script src="scripts.js"></script>
    <link rel="stylesheet" href="main.css" type="text/css">

</head>
<body>
<div class="site">
    <header>
    <h1>
        DODAWANIE NOWEGO USERA
    </h1>
    </header>

    <section>
        <nav>
            <button class="menu_button" onclick="location.href='/index.jsp'">
                <span>Wróć do strony głównej</span>
            </button>

            <news>
                <h2> newsy </h2>
                <ul>
                    <li>
                        <div id="today"></div>
                    </li>
                    <li>
                        <div id="urodziny"></div>
                </ul>
            </news>
        </nav>

        <div class="mainContent">
            <% if (isSesja && logedUser.equals("admin")) { %>
            <form action="AddUser" method="post">
                Name:<input type="text" name="email"/><br/><br/>
                Password:<input type="password" name="password"/><br/><br/>
                Level:<input type="text" name="level"/><br/><br/>
                <input type="submit" value="Dodaj"/>
            </form>
            <% } else { %>
                <h1>
                    A ty skąd się tu wziąłeś ?
                </h1>
            <% } %>
        </div>

    </section>

    <footer>
        <div id="czas"></div>
    </footer>
</div>
</body>
</html>
