package com.example.jdbcconnectiontest;

import android.telecom.ConnectionRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetData {

    Connection connection;
    String connectionResult;
    Boolean isSuccess = false;
    String ConnectionResult = "";

    public List<Map<String, String>> getData(){
        List<Map<String, String>> data = null;
        data = new ArrayList<Map<String, String>>();

        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connections();
            if (connection==null){
                ConnectionResult="Check your localhost availablity";
            }
            else{
                String query = "select * from users_info";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while(rs.next()){
                    Map<String, String> datanum = new HashMap<String, String>();
                    datanum.put("Username", rs.getString("username"));
                    datanum.put("Email", rs.getString("email"));
                    datanum.put("username", rs.getString("password"));
                    data.add(datanum);
                }
                ConnectionResult= "Succesful";
                isSuccess = true;
                connection.close();
            }
        }
        catch(Exception e){
            isSuccess = false;
            ConnectionResult= e.getMessage();
        }
        return data;
    }
}
