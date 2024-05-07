package se.lexicon.data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    //Variables
    private static String _url;
    private static String _userName;
    private static String  _password;

    //Setters


    public static void set_url(String url) {
        _url = url;
    }

    public static void set_userName(String userName) {
        _userName = userName;
    }

    public static void set_password(String password) {
        _password = password;
    }

    //Methods
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(_url,_userName,_password);
        }catch (SQLException e){
            System.out.println("Error getting connection to: " +_url);
        }
        return connection;
    }
}
