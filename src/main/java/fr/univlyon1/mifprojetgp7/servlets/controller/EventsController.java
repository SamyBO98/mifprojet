package fr.univlyon1.mifprojetgp7.servlets.controller;

import fr.univlyon1.mifprojetgp7.metier.EventM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Event;

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

@WebServlet(name = "EventController", urlPatterns = {"/events", "/events/*"})
public class EventsController extends HttpServlet {

    EventM event;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.event = new EventM((EntityManager) config.getServletContext().getAttribute("em"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqUri = req.getRequestURI();
        List<String> uri = parseUri(reqUri, "events");
        String page = null;

        if (uri.size() == 0){
            List<Event> events = event.getEvents();
            req.setAttribute("events", events);
            page = "events.jsp";
            req.setAttribute("page", page);
        } else if (uri.size() == 1){
            if (uri.get(0).equals("create")){
                page = "createEvent.jsp";
                req.setAttribute("page", page);
            } else if (uri.get(0).equals("search")){
                page = "searchEvents.jsp";
                req.setAttribute("page", page);
            } else {
                /**
                 * Pour l'instant on pense qu'il s'agit de l'id de l'event
                 */
                page = "event.jsp";
                req.setAttribute("page", page);
                req.setAttribute("event", event.getEvent(Integer.parseInt(uri.get(0))));
            }
        } else if (uri.size() == 2){
            if (uri.get(1).equals("participate")){
                Account user = (Account) req.getSession(true).getAttribute("user");
                Event ev = event.getEvent(Integer.parseInt(uri.get(0)));
                if (event.updateContributorToEvent(ev, user) == true){
                    resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                }
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
        String reqUri = req.getRequestURI();
        List<String> uri = parseUri(reqUri, "events");
        String page = null;

        if (uri.size() == 1){
            if (uri.get(0).equals("create")){
                String title = req.getParameter("title");
                String contenu = req.getParameter("contenu");
                event.createEvent(title, contenu, (Account) req.getSession(true).getAttribute("user"));

                List<Event> events = event.getEvents();
                req.setAttribute("events", events);

                page = "events.jsp";
                req.setAttribute("page", page);

            } else if (uri.get(0).equals("search")){
                page = "searchEvents.jsp";
                req.setAttribute("page", page);
            }
        }


        if (req.getSession(true).getAttribute("user") == null){
            req.getRequestDispatcher("/index.jsp").include(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").include(req, resp);
        }
    }
}
