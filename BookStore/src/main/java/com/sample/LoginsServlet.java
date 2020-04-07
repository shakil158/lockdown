package com.sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;



import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;


@WebServlet(
        name = "login",
        urlPatterns = "/login"
)
public class LoginsServlet   extends HttpServlet {





    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        int i = 0;
        Connection con = SelectBookServlet.getConnection();
        PreparedStatement ps= null;
        try {
            ps = con.prepareStatement("select * from userss where user=? and pass = ?");
            ps.setString(1,user);
            ps.setString(2,pass);
            ResultSet rs=ps.executeQuery();
            while(rs.next()) {
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }





        resp.setContentType("text/html");
        PrintWriter out=resp.getWriter();

        System.out.println(user);
        System.out.println(pass);
        if (i>0){
            req.getRequestDispatcher("book.html").include(req, resp);
        }
        else{
            out.println("enter valid username ans password");
        }

    }

    }
