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

@WebServlet(urlPatterns = "/put")
public class ServletPut extends HttpServlet {

    Model model = Model.getInstance();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        JsonObject jsonObject = new Gson().fromJson(jb.toString(), JsonObject.class);

        String idS = jsonObject.get("id").getAsString();
        String name = jsonObject.get("name").getAsString();
        String surname = jsonObject.get("surname").getAsString();
        String salaryS = jsonObject.get("salary").getAsString();

        Map<Integer, User> map = model.getFromList();
        int id = Integer.parseInt(idS);
        double salary = Double.parseDouble(salaryS);
        if (map.containsKey(id)) {
            response.setContentType("application/json;charset=utf-8");
            User user = map.get(id);
            user.setName(name);
            user.setSurname(surname);
            user.setSalary(salary);


            response.setContentType("application/json;charset=utf-8");
            model.getFromList().put(id, user);
        } else {
            response.getWriter().println("Нет пользователя с ID:" + id + ". Всего пользователей: " + model.getFromList().size());
        }

    }


}
