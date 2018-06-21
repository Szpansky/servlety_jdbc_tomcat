function first() {
    alert("first");
}

$(document).ready(function () {

    $("#logout_button").click(function () {
        post("/logout","logout");
    });
   
    $("#login_button").click(function () {
        $("section div.mainContent").hide();
        $("section div.squareContent").hide();
        $("section div.loginContent").show();

    });

    $("#main_page").click(function () {
        $("section div.mainContent").show();
        $("section div.squareContent").hide();
        $("section div.loginContent").hide();

    });

    $("#need_privileges").click(function () {
        alert("Brak uprawnien do obliczania funkcji kwadratowej");
    });

    $("#square_page").click(function () {
        $("section div.mainContent").hide();
        $("section div.squareContent").show();
        $("section div.loginContent").hide();


        var liczba = prompt("Podaj współczynnik a:");
        var liczba2 = prompt("Podaj współczynnik b:");
        var liczba3 = prompt("Podaj współczynnik c:");

        var a = parseFloat(liczba);
        var b = parseFloat(liczba2);
        var c = parseFloat(liczba3);

        if (a == 0) {
            document.getElementById("squareResult").innerHTML = "To nie jest równanie kwadratowe"
        }
        else {
            var delta = b * b - 4 * a * c;
            var p = Math.sqrt(delta);

            if (delta == 0) {
                var wynik = -b / (2 * a);
                document.getElementById("squareResult").innerHTML = "Równanie ma jedno rozwiązanie, wynosi: " + wynik
            }

            if (delta > 0) {
                var x1 = (-b - p) / (2 * a);
                var x2 = (-b + p) / (2 * a);
                document.getElementById("squareResult").innerHTML = "Równanie ma dwa rozwiązania, pierwsze wynosi: " + x1 + ", a drugie: " + x2
            }
            else {
                document.getElementById("squareResult").innerHTML = "Delta jest ujemna, równanie nie ma rozwiązań!"
            }
        }
    });


    var date = new Date();
    var dd = date.getDate();
    var mm = date.getMonth() + 1;
    var yyyy = date.getFullYear();

    today = mm + '/' + dd + '/' + yyyy + ' ' + date.toLocaleTimeString();
    document.getElementById("czas").innerHTML = today;
    var myVar = setInterval(function () {
        myDate()
    }, 1000);

    function myDate() {
        date = new Date();
        var dd = date.getDate();
        var mm = date.getMonth() + 1;
        var yyyy = date.getFullYear();
        today = mm + '/' + dd + '/' + yyyy + ' ' + date.toLocaleTimeString();

        document.getElementById("czas").innerHTML = today
    }

    var urodziny = new Date('4/26/' + date.getFullYear());
    console.log(urodziny);

    var timeDiff = urodziny.getTime() - date.getTime();
    var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

    var days = ['Niedziela', 'Poniedziałek', 'Wtorek', 'Środa', 'Czwartek', 'Piątek', 'Sobota'];
    var dayName = 'Dzisiaj jest ' + days[date.getDay()] + '\n';

    var content = "";

    if (diffDays >= 0) {
        content += 'Właściciel ma urodziny za : ' + diffDays + ' dni'
    } else {
        content += 'Urodziny właściciela były : ' + Math.abs(diffDays) + ' dni temu';
    }
    document.getElementById("today").innerHTML = dayName;
    document.getElementById("urodziny").innerHTML = content


});

function post(path, params, method) {
    method = method || "post";
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
        }
    }

    document.body.appendChild(form);
    form.submit();
}