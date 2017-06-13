package Servlets;

import Accounts.SessionService;
import Accounts.UsersDataSet;
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
public class SignInServlet extends HttpServlet {
    private SessionService sessionService;
    private DBService dbService;
    public SignInServlet(SessionService sessionService, DBService dbService){
        this.sessionService = sessionService;
        this.dbService = dbService;
    }
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        UsersDataSet dataSet = new UsersDataSet(login,pass);
        try {
            dataSet = dbService.getUser(login);

        } catch (DBException e) {
            e.printStackTrace();
        }
        if (pass != dataSet.getPassword() || login != dataSet.getLogin())
            response.getWriter().println("Incorrect login or password");
        else {
            sessionService.addSession(request.getSession().getId().toString(), login);
        }


    }
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("html/signIn.html"), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                response.getWriter().println(line);
            }
        } catch (IOException e) {
            // log error
        }

    }
}
