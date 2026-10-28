package com.laxmi.calc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        try {
            double a = Double.parseDouble(req.getParameter("a"));
            double b = Double.parseDouble(req.getParameter("b"));
            String op = req.getParameter("op");
            double result;
            switch (op) {
                case "add": result = a + b; break;
                case "sub": result = a - b; break;
                case "mul": result = a * b; break;
                case "div":
                    if (b == 0) {
                        out.println("<p>Cannot divide by zero.</p><a href='/'>Back</a>");
                        return;
                    }
                    result = a / b;
                    break;
                default:
                    out.println("<p>Unknown operation.</p><a href='/'>Back</a>");
                    return;
            }
            out.println("<h2>Result: " + result + "</h2><a href='/'>Back</a>");
        } catch (Exception e) {
            out.println("<p>Please enter valid numbers.</p><a href='/'>Back</a>");
        }
    }
}
