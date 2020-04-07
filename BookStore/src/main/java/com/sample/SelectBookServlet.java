package com.sample;
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
        name = "selectbook",
        urlPatterns = "/SelectBooks"
)
public class SelectBookServlet extends HttpServlet {

    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        }catch(Exception e){System.out.println(e);}
        return con;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String book_type = req.getParameter("Type");
        System.out.println(book_type);
        List<String> el=new ArrayList<String>();


        Connection con = SelectBookServlet.getConnection();
        try {
            PreparedStatement ps=con.prepareStatement("select * from books where type=?");
            ps.setString(1,book_type);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
                el.add(rs.getString(1));
                el.add(rs.getString(2));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        req.setAttribute("books", el);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }
}
