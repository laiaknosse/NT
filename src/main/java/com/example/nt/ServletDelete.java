package com.example.nt;

import com.example.nt.logic.Model;
import com.example.nt.logic.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {
    Model model = Model.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer jb = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        JsonObject jsonRequest = new Gson().fromJson(jb.toString(), JsonObject.class);

        response.setContentType("text/html;charset=utf-8");
        int id = jsonRequest.get("id").getAsInt();



        if (model.getFromList().containsKey(id)) {
            response.setContentType("application/json;charset=utf-8");
            model.getFromList().remove(id);
            response.getWriter().println("Пользователь с ID: " + id + " был успешно удален!");

        } else {
            response.getWriter().println("Нет пользователя с ID:" + id + ". Всего пользователей: " + model.getFromList().size());
        }
    }


}
