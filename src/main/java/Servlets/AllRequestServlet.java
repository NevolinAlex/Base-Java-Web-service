package Servlets;

import Accounts.SessionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Alex Nevolinf on 28.12.2016.
 */
public class AllRequestServlet extends HttpServlet {
    SessionService sessionService;

    public AllRequestServlet(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        if (!sessionService.checkToEmpty(request.getSession().getId().toString())){
            response.sendRedirect("/signin");
            return;
        }
        else {
            String name = request.getRequestURI();
            File file = new File("Files" + name);

            try {
                OutputStream out = response.getOutputStream();
                FileInputStream in = new FileInputStream(file);
                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                in.close();
                out.flush();
            } catch (Exception e) {
                return;
            }

        }
    }
}
