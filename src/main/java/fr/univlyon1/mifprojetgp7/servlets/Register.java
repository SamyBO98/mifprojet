package fr.univlyon1.mifprojetgp7.servlets;

import fr.univlyon1.mifprojetgp7.metier.AccountM;
import fr.univlyon1.mifprojetgp7.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Simple Servlet used for register a new user.
 * We check into the database if the user don't exists.
 * -> If everything works, we moved him to the Auth page.
 * -> If not, we let him in the Register page.
 */
@WebServlet(name = "Register", urlPatterns = "/register")
public class Register extends HttpServlet {

    private AccountM account;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.account = new AccountM((EntityManager) config.getServletContext().getAttribute("em"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        String email = req.getParameter("login-email");
        String password = req.getParameter("login-password");
        String firstname = req.getParameter("login-firstname");
        String lastname = req.getParameter("login-lastname");

        if (email != null && password != null && firstname != null && lastname != null){

            Account acc = account.createAccount(email, password, firstname, lastname);

            if (acc != null){
                resp.sendRedirect("logs/logon.jsp");
            } else {
                resp.sendRedirect("logs/login.jsp");
            }

        } else {
            resp.sendRedirect("logs/login.jsp");
        }
    }
}
