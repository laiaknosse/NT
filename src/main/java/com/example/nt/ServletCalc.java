package com.example.nt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calc")
public class ServletCalc extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        String operator = jsonRequest.get("operator").getAsString();
        double a = jsonRequest.get("a").getAsDouble();
        double b = jsonRequest.get("b").getAsDouble();

        double result = 0;

        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b != 0) {
                    result = a / b;
                } else {
                    System.out.println("Нельзя делить на ноль!");
                }
                break;
            default:
                System.out.println("Неизвестный оператор!");
                break;
        }

        JsonObject resultObject = new JsonObject();
        resultObject.add("result", new JsonPrimitive(result));

        response.setContentType("application/json");
        response.getWriter().println(new Gson().toJson(resultObject));
    }


}
