/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swat.principal.validation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginValidation extends HttpServlet implements Filter {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("llamando el metodo");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        if(name.equals("admin") && pass.equals("admin")) {
            RequestDispatcher rd = req.getRequestDispatcher("module/traduccion.xhtml");  
            rd.forward(req ,resp);  
        }
        else {
            out.print("Lo siento nombre o contraseña invalida, intentar de nuevo");
            RequestDispatcher rd = req.getRequestDispatcher("index.html");  
            rd.include(req,resp);
        }
        out.close();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("inicializando");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("invocando");
        HttpServletRequest a = (HttpServletRequest)request;
        HttpServletResponse b = (HttpServletResponse)response;
        System.out.println(a.getContextPath());
        System.out.println(a.getRequestURI());
        System.out.println(a.getRequestURI());
        b.sendRedirect("index.html");
    }
    
}
