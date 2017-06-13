package Servlets;

import Accounts.SessionService;
import DataBase.DBException;
import DataBase.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Created by Alex Nevolinf on 22.12.2016.
 */
public class SignUpServlet extends HttpServlet{
    private SessionService sessionService;
    private DBService dbService;
    public SignUpServlet(SessionService sessionService, DBService dbService){
        this.sessionService = sessionService;
        this.dbService = dbService;
    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        response.setContentType("text/html;charset=utf-8");
        if (login == null || pass == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
           dbService.addUser(login, pass);

            response.getWriter().println("<h1>Sign up was succesful</h1>");
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("html/signUp.html"), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                response.getWriter().println(line);
            }
        } catch (IOException e) {
            // log error
        }


    }
}
