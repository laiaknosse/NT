package com.example.nt;

import com.example.nt.logic.Model;
import com.example.nt.logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/get")
public class ServletList extends HttpServlet {
    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        int id = Integer.parseInt(request.getParameter("id"));
        PrintWriter pw = response.getWriter();

        if (id == 0) {
            response.setContentType("application/json;charset=utf-8");
            pw.print(gson.toJson(model.getFromList()));

        } else if (id > 0) {
            if (id > model.getFromList().size()) {
                pw.print("<html>" +
                        "<h3>Такого пользователя нет!</h3>" +
                        "<a href=\\index.jsp>Домой</a>" +
                        "</html>");
            } else {
                response.setContentType("application/json;charset=utf-8");
                pw.print(gson.toJson(model.getFromList().get(id)));
            }
        } else {
            pw.print("<html>" +
                    "<h3>ID должен быть больше нуля!</h3>" +
                    "<a href=\\index.jsp>Домой</a>" +
                    "</html>");
        }
    }


}
