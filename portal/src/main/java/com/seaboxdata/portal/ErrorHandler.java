package com.seaboxdata.portal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quick.core.base.model.DataStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorHandler extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");

        response.setContentType("text/json;charset=UTF-8");

        PrintWriter out = response.getWriter();

        DataStore result = new DataStore();

        if (statusCode == 401)
            result.setError("Authentication Failure");
        else
            result.setError("Unknown Error");

        ObjectMapper objectMapper = new ObjectMapper();
        out.println(objectMapper.writeValueAsString(result));
    }
}
