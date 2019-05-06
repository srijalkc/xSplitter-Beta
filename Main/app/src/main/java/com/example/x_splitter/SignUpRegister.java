//package com.example.x_splitter;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class SignUpRegister {
//        Connection con = null;
//        public void connect(){
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                con = DriverManager.getConnection("jdbc:mysql://192.168.10.6:3306/users_db", "root", "");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            System.out.println("connect() method Executed");
//        }
//        public void addUsers(SignUpRegisterValues rv){
//            String query = "insert into users_info(username, email, password) values (?,?,?)";
//            PreparedStatement pst;
//            try {
//                pst = con.prepareStatement(query);
//                pst.setString(1, rv.email);
//                pst.setString(2, rv.username);
//                pst.setString(3, rv.password);
//                pst.executeUpdate();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            System.out.println("addUsers() method Executed");
//        }
//}
//
//
//
//
