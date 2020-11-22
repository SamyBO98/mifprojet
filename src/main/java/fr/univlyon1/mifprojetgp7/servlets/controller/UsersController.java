package fr.univlyon1.mifprojetgp7.servlets.controller;


import fr.univlyon1.mifprojetgp7.metier.AccountM;
import fr.univlyon1.mifprojetgp7.metier.ContributorM;
import fr.univlyon1.mifprojetgp7.metier.EventM;
import fr.univlyon1.mifprojetgp7.metier.InterestM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Interest;

import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;


import static fr.univlyon1.mifprojetgp7.utils.ParseURI.parseUri;
import static fr.univlyon1.mifprojetgp7.utils.ParseURI.sourceURI;
import static fr.univlyon1.mifprojetgp7.utils.PasswordHashing.*;

@WebServlet(name = "UsersController", urlPatterns = {"/users", "/users/*"})
public class UsersController extends HttpServlet {

    AccountM account;
    InterestM interest;
    EventM event;
    ContributorM contributor;
    private static final Logger LOGGER = Logger.getLogger(UsersController.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        account = new AccountM((EntityManager) config.getServletContext().getAttribute("em"));
        interest = new InterestM((EntityManager) config.getServletContext().getAttribute("em"));
        event = new EventM((EntityManager) config.getServletContext().getAttribute("em"));
        contributor = new ContributorM((EntityManager) config.getServletContext().getAttribute("em"));
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
                try {
                    resp.sendRedirect("/" + sourceURI(reqUri));
                    return;
                } catch (IOException e){
                    LOGGER.log(Level.SEVERE,"Exception occured",e);
                }

            } else if (uri.get(0).equals("profile")){
                page = "users/user.jsp";
                req.setAttribute("page", page);
                List<Interest> interests = interest.getInterests((Account) req.getSession(true).getAttribute("user"));
                req.setAttribute("interests", interests);
                int countContribs = contributor.getContributors((Account) req.getSession(true).getAttribute("user")).size();
                req.setAttribute("countContribs", countContribs);
                int countCreated = event.getEvent((Account) req.getSession(true).getAttribute("user")).size();
                req.setAttribute("countCreated", countCreated);
            } else {
                try{
                    resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                    return;
                }catch (IOException e){
                    LOGGER.log(Level.SEVERE,"Exception occured",e);
                }

            }
        } else {
            try{
                resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                return;
            }catch (IOException e){
                LOGGER.log(Level.SEVERE,"Exception occured",e);
            }

        }

        if (req.getSession(true).getAttribute("user") == null){
            try{
                req.getRequestDispatcher("/index.jsp").include(req, resp);
            }catch (IOException e){
                LOGGER.log(Level.SEVERE,"Exception occured",e);
            } catch (ServletException s){
                LOGGER.log(Level.SEVERE,"Servlet Exception occured",s);
            }

        } else {
            try{
                req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").include(req, resp);
            }catch (IOException e){
                LOGGER.log(Level.SEVERE,"Exception occured",e);
            } catch (ServletException s){
                LOGGER.log(Level.SEVERE,"Servlet Exception occured",s);
            }

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.log(Level.FINE,"POST");
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
                        tryAndCatch(req,resp,"/users/login");

                    } else {
                        tryAndCatch(req,resp,"/users/signup");

                    }

                } else {
                    tryAndCatch(req,resp,"/users/signup");

                }

            } else if (uri.get(0).equals("login")){
                String email = req.getParameter("email");
                String password = req.getParameter("password");

                Account user = account.getAccount(email);

                if (user == null){
                    tryAndCatch(req,resp,"/users/login");

                } else {
                    //Check if matches
                    if (verifyUserPassword(password, user.getPassword(), user.getSalt())){
                        req.getSession(true).setAttribute("user", user);
                        tryAndCatch(req,resp,"/events");

                    } else {
                        tryAndCatch(req,resp,"/users/login");

                    }
                }
            } else {
                try{
                    resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                    return;
                }catch (IOException e){
                    LOGGER.log(Level.SEVERE,"Exception occured",e);
                }

            }
        } else {
            try{
                resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                return;
            }catch (IOException e){
                LOGGER.log(Level.SEVERE,"Exception occured",e);
            }

        }

    }
    private void tryAndCatch(HttpServletRequest req, HttpServletResponse resp, String lastParamRedirect){
        try{
            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + lastParamRedirect);
            return;
        }catch (IOException e){
            LOGGER.log(Level.SEVERE,"Exception occured ",e);
        }
    }
}
