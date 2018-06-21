import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

public class JDBC {
    /**
     * Metoda ładuje sterownik jdbc
     *
     * @return true/false
     */
    public static boolean checkDriver(String driver) {
        // LADOWANIE STEROWNIKA
        System.out.println("Sprawdzanie sterownika:");
       try {
            Class.forName(driver).newInstance();
            return true;
        } catch (Exception e) {
            System.out.println("Blad przy ladowaniu sterownika bazy!"+e.toString());
            return false;
        }
    }

    /**
     * Metoda służy do połączenia z MySQL bez wybierania konkretnej bazy
     *
     * @return referencja do uchwytu bazy danych
     */
    public static Connection getConnection(String kindOfDatabase, String adres, int port, String userName, String password) {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);
        try {
           // DriverManager.registerDriver(new com.mysql.jdbc.Driver ());

            conn = DriverManager.getConnection(kindOfDatabase + adres + ":" + port + "/",
                    connectionProps);
        } catch (SQLException e) {
            System.out.println("Błąd połączenia z bazą danych! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(2);
        }
        System.out.println("Połączenie z bazą danych: ... OK");
        return conn;
    }

    /**
     * tworzenie obiektu Statement przesyłającego zapytania do bazy connection
     *
     * @param connection - połączenie z bazą
     * @return obiekt Statement przesyłający zapytania do bazy
     */
    private static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Błąd createStatement! " + e.getMessage() + ": " + e.getErrorCode());
            System.exit(3);
        }
        return null;
    }


    public static void closeConnection() {
        System.out.println("Zamykanie polaczenia z bazą:");
        try {
            st.close();
            con.close();
        } catch (SQLException e) {
            System.out
                    .println("Bląd przy zamykaniu polączenia z bazą! " + e.getMessage() + ": " + e.getErrorCode());
            ;
            System.exit(4);
        }
        System.out.println("zamknięcie OK");
    }

    /**
     * Wykonanie kwerendy i przesłanie wyników do obiektu ResultSet
     *
     * @param s   - Statement
     * @param sql - zapytanie
     * @return wynik
     */
    private static ResultSet executeQuery(Statement s, String sql) {
        try {
            return s.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return null;
    }

    private static int executeUpdate(Statement s, String sql) {
        try {
            return s.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Zapytanie nie wykonane! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return -1;
    }


    private static Statement st;
    private static Connection con;

    public static void connectToDB() {
        if (checkDriver("com.mysql.jdbc.Driver"))
            System.out.print(" ... OK");
        else
            System.exit(1);

        con = getConnection("jdbc:mysql://", "localhost", 3306, "root", "");
        st = createStatement(con);

        assert st != null;

        if (executeUpdate(st, "USE Users_DB;") == 0)
            System.out.println("Baza wybrana");
        else {
            System.out.println("Baza nie istnieje! Tworzymy bazę: ");
            if (executeUpdate(st, "create Database Users_DB;") == 1)
                System.out.println("Baza utworzona");
            else
                System.out.println("Baza nieutworzona!");
            if (executeUpdate(st, "USE Users_DB;") == 0)
                System.out.println("Baza wybrana");
            else
                System.out.println("Baza niewybrana!");
        }

    }


    public static ArrayList<User> getUsersList() {
        System.out.println("DOWNLOADING users FROM DB");
        User user = null;
        ArrayList<User> userList = new ArrayList<>();
        String sql = "Select * from users";
        ResultSet r = executeQuery(st, sql);
        try {
            if (r != null) {
                while (r.next()) {
                    Object id = r.getObject(1);
                    Object email = r.getObject(2);
                    Object password = r.getObject(3);
                    Object accountType = r.getObject(4);
                    Object registerDate = r.getObject(5);
                    if (id != null) {
                        user = new User(email.toString(), password.toString(), accountType.toString());
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Bląd odczytu z bazy! " + e.getMessage() + ": " + e.getErrorCode());
        }
        return userList;
    }



    public static void registerUser(User user) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String date = (dateFormat.format(cal.getTime()));

        System.out.println("Adding user TO DB");
        String sql;
        sql = "INSERT INTO `users` (`id`, `user_email`, `user_password`, `user_account_type`, `register_date`) VALUES ( NULL,'" + user.getEmail() + "' ,'" + user.getPassword() + "', '" + user.getLevel() + "', '" + date + "')";

        executeUpdate(st, sql);
    }


}