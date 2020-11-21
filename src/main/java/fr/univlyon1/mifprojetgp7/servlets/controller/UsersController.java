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
import static fr.univlyon1.mifprojetgp7.utils.ParseURI.sourceURI;
import static fr.univlyon1.mifprojetgp7.utils.PasswordHashing.*;

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
        String reqUri = req.getRequestURI();
        List<String> uri = parseUri(reqUri, "users");
        String page = null;

        if (uri.size() == 1){
            if (uri.get(0).equals("signup")){
                page = "WEB-INF/jsp/logs/signup.jsp";
                req.setAttribute("page", page);
            } else if (uri.get(0).equals("login")){
                page = "WEB-INF/jsp/logs/login.jsp";
                req.setAttribute("page", page);
            } else if (uri.get(0).equals("disconnect")){
                req.getSession(true).invalidate();
                resp.sendRedirect("/" + sourceURI(reqUri));
                return;
            }
        }

        if (req.getSession(true).getAttribute("user") == null){
            req.getRequestDispatcher("/index.jsp").include(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").include(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        List<String> uri = parseUri(req.getRequestURI(), "users");

        if (uri.size() == 1){
            if (uri.get(0).equals("signup")){
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String passwordConfirm = req.getParameter("passwordconfirm");
                String name = req.getParameter("name");
                String firstname = req.getParameter("firstname");

                if (password.equals(passwordConfirm) && !password.equals("")){
                    //Generate Salt (stored in DB)
                    String salt = getSalt(30);
                    String securedPassword = generateSecurePassword(password, salt);

                    if (account.createAccount(email, name, firstname, securedPassword, salt) != null){
                        resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/users/login");
                    } else {
                        resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/users/signup");
                    }

                } else {
                    resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/users/signup");
                }

            } else if (uri.get(0).equals("login")){
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                Account user = account.getAccount(email);

                if (user == null){
                    resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/users/login");
                } else {
                    //Check if matches
                    if (verifyUserPassword(password, user.getPassword(), user.getSalt())){
                        req.getSession(true).setAttribute("user", user);
                        resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                    } else {
                        resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/users/login");
                    }
                }
            }
        }

    }
}
