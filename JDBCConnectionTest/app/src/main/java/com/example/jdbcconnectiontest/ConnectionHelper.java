package com.example.jdbcconnectiontest;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    String DBUsername, DBPassword;
    @SuppressLint("NewApi")
    public Connection connections(){
        DBUsername="root";
        DBPassword=" ";

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users_db", DBUsername, DBPassword);
        }
        catch(SQLException e){
            Log.e("error From SQL", e.getMessage());
        }
        catch(ClassNotFoundException e){
            Log.e("Error from Class", e.getMessage());
        }
        catch(Exception e){
            Log.e("Error from Exception", e.getMessage());
        }
        return connection;
    }
}
