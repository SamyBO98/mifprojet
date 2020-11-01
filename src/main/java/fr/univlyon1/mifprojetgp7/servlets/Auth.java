package fr.univlyon1.mifprojetgp7.servlets;

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

/**
 * Simple Servlet used for connect the user.
 * We check into the database if the user exists and if the password matches with this one.
 * -> If everything works, we moved him to the Welcome page.
 * -> If not, we let him in the Connection page.
 */
@WebServlet(name = "Auth", urlPatterns = "/auth")
public class Auth extends HttpServlet {

    private AccountM account;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.account = new AccountM((EntityManager) config.getServletContext().getAttribute("em"));
    }

    /**
     * Get the forum from "Log In" jsp page.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("logon-email");
        String password = req.getParameter("logon-password");

        if (email != null && password != null){
            Account acc = account.getAccount(email, password);

            if (acc != null){
                req.getSession(true).setAttribute("user", acc);
                resp.sendRedirect("welcome.jsp");
            } else {
                resp.sendRedirect("logs/logon.jsp");
            }

        } else {
            resp.sendRedirect("logs/logon.jsp");
        }
    }

}
