package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.webMethods;

@WebServlet(name = "myServlet", urlPatterns = {"/myServlet"})
public class myServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("myName");

        webMethods web = new webMethods();
       

        request.setAttribute("person", "sha la la");
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/display.jsp");
        dispatcher.forward(request, response);

    }

}
