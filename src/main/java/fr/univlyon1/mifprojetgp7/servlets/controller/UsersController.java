package fr.univlyon1.mifprojetgp7.servlets.controller;


import fr.univlyon1.mifprojetgp7.metier.AccountM;
import fr.univlyon1.mifprojetgp7.model.Account;

import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static fr.univlyon1.mifprojetgp7.utils.ParseURI.parseUri;

@WebServlet(name = "UsersController", urlPatterns = {"/users", "/users/*"})
public class UsersController extends HttpServlet {

    AccountM account;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        account = new AccountM((EntityManager) config.getServletContext().getAttribute("em"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> uri = parseUri(req.getRequestURI(), "users");
        String page = null;

        if (uri.size() == 1){
            if (uri.get(0).equals("signup")){
                page = "/logs/signup.jsp";
            } else if (uri.get(0).equals("login")){
                page = "/logs/login.jsp";
            }
        }


        req.getRequestDispatcher(page).include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> uri = parseUri(req.getRequestURI(), "users");
        String page = null;

        if (uri.size() == 1){
            if (uri.get(0).equals("signup")){
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String name = req.getParameter("name");
                String firstname = req.getParameter("firstname");

                System.out.println(email + " " + password + " " + name + " " + firstname);

                Account user = account.createAccount(email, password, name, firstname);

                if (user == null){
                    page = "/logs/signup.jsp";
                } else {
                    page = "/logs/login.jsp";
                }

            } else if (uri.get(0).equals("login")){
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                Account user = account.getAccount(email, password);

                if (user == null){
                    page = "/logs/login.jsp";
                } else {
                    req.getSession(true).setAttribute("user", user);
                    page = "/welcome.jsp";
                }
            }
        }

        req.getRequestDispatcher(page).include(req, resp);

    }
}
