package se.lexicon.data.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

    //Variables
    private static final String _url = "jdbc:mysql://localhost:3306/todoit";
    private static final String _userName = "username";
    private static final String _password = "password";

    //Setters



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
