<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Boolean isSesja = false;
    String logedUser = "";
    if (session != null && session.getAttribute("userID") != null) {
        isSesja = true;
        logedUser = session.getAttribute("userID").toString();
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
        <%
            switch (logedUser) {
                case ("user"): {
        %><h1>Witaj USER, Jak mija dzień ?</h1><%
        }
        break;
        case ("admin"): {
    %><h1>Witaj szefie, na serwerze wszystko ok</h1><%
        }
        break;
        default: {
    %><h1>Witaj nieznajomy, może się zalogujesz ?</h1><%
            }
        }
    %>
        Lorem ipsum dolor it amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed, lacinia
        in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam. Quisque semper
        justo at risus. Donec venenatis, turpis vel hendrerit interdum, dui ligula ultricies purus, sed posuere libero
        dui id orci. Nam congue, pede vitae dapibus aliquet, elit magna vulputate arcu, vel tempus metus leo non est.
        Etiam sit amet lectus quis est congue mollis. Phasellus congue lacus eget neque. Phasellus ornare, ante vitae
        consectetuer consequat, purus sapien ultricies dolor, et mollis pede metus eget nisi. Praesent sodales velit
        quis augue. Cras suscipit, urna at aliquam rhoncus, urna quam viverra nisi, in interdum massa nibh nec erat.
    </header>

    <section>
        <nav>
            <button id="main_page" class="menu_button">
                <span>main page</span>
            </button>

            <% if (logedUser.equals("admin")) { %>
            <button id="square_page" class="menu_button">
                <span>kwadrat</span>
            </button>
            <button class="menu_button" onclick="location.href='/dodajusera.jsp'">
                <span>Dodaj usera</span>
            </button>
            <% } else { %>
            <button id="need_privileges" class="menu_button">
                <span>kwadrat</span>
            </button>
            <% } %>

            <% if (isSesja) { %>
            <button id="logout_button" class="menu_button">
                <span>wylogowanie</span>
            </button>
            <% } else { %>
            <button id="login_button" class="menu_button">
                <span>logowanie</span>
            </button>
            <% } %>

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
            <h3>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin nibh augue, suscipit a, scelerisque sed,
                lacinia in, mi. Cras vel lorem. Etiam pellentesque aliquet tellus. Phasellus pharetra nulla ac diam.
                Quisque semper justo at risus. Donec venenatis, turpis vel hendrerit interdum, dui ligula ultricies
                purus, sed posuere libero dui id orci. Nam congue, pede vitae dapibus aliquet, elit magna vulputate
                arcu, vel tempus metus leo non est. Etiam sit amet lectus quis est congue mollis. Phasellus congue lacus
                eget neque. Phasellus ornare, ante vitae consectetuer consequat, purus sapien ultricies dolor, et mollis
                pede metus eget nisi. Praesent sodales velit quis augue. Cras suscipit, urna at aliquam rhoncus, urna
                quam viverra nisi, in interdum massa nibh nec erat.
            </h3>
        </div>

        <div class="squareContent">
            <h3 id="squareResult">
                kwadrat
            </h3>
        </div>

        <div class="loginContent">
            <form method="post" action="login">
                Email ID:<input type="text" name="email"/><br/>
                Password:<input type="text" name="pass"/><br/>
                <input type="submit" value="login"/>
            </form>
        </div>
    </section>

    <footer>
        <div id="czas"></div>
    </footer>
</div>
</body>
</html>
