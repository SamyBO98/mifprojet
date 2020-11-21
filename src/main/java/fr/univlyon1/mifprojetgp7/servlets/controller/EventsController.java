package fr.univlyon1.mifprojetgp7.servlets.controller;

import fr.univlyon1.mifprojetgp7.metier.CategoryM;
import fr.univlyon1.mifprojetgp7.metier.ContributorM;
import fr.univlyon1.mifprojetgp7.metier.EventM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Contributor;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import static fr.univlyon1.mifprojetgp7.utils.ParseURI.parseUri;
import static fr.univlyon1.mifprojetgp7.utils.ParseURI.sourceURI;

@WebServlet(name = "EventsController", urlPatterns = {"/events", "/events/*"})
public class EventsController extends HttpServlet {

    static EventM event;
    static CategoryM categorie;
    static ContributorM contributor;
    private static final Logger LOGGER = Logger.getLogger(EventsController.class.getName());

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.event = new EventM((EntityManager) config.getServletContext().getAttribute("em"));
        this.categorie = new CategoryM((EntityManager) config.getServletContext().getAttribute("em"));
        this.contributor = new ContributorM((EntityManager) config.getServletContext().getAttribute("em"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqUri = req.getRequestURI();
        List<String> uri = parseUri(reqUri, "events");
        String page = null;

        if (uri.size() == 0){
            //Liste de tout les évènements
            page = "events/events.jsp";
            req.setAttribute("page", page);
            List<Event> events = event.getEvents();
            req.setAttribute("events", events);

        } else if (uri.size() == 1) {
            if (uri.get(0).equals("create")) {
                //Appel vers la création d'évènement
                page = "events/createEvent.jsp";
                req.setAttribute("page", page);
                List<Category> categories = categorie.getCategories();
                req.setAttribute("categories", categories);

            } else if (uri.get(0).equals("search")) {
                //Appel vers la page de recherche d'évènements filtré
                page = "events/searchEvents.jsp";
                req.setAttribute("page", page);

            } else if (uri.get(0).equals("created")) {
                //Liste des évènements crées
                page = "events/events.jsp";
                req.setAttribute("page", page);
                List<Event> events = event.getEvent((Account) req.getSession(true).getAttribute("user"));
                req.setAttribute("events", events);

            } else if (uri.get(0).equals("participate")){
                //Liste des évènements ou on participe
                page = "events/events.jsp";
                req.setAttribute("page", page);
                List<Contributor> contributors = contributor.getContributors((Account) req.getSession(true).getAttribute("user"));
                List<Event> events = event.getEvents(contributors);
                req.setAttribute("events", events);

            } else {
                //Appel vers un évènement unique (redirection vers /events si l'évènement n'existe pas)
                try {
                    Event ev = event.getEvent(Integer.parseInt(uri.get(0)));

                    if (ev == null){
                        try{
                            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                            return;
                        } catch (IOException e){
                            LOGGER.log(Level.SEVERE,"Exception occured ",e);
                        }

                    } else {
                        page = "events/event.jsp";
                        req.setAttribute("page", page);
                        boolean liked;
                        Contributor contrib = contributor.getContributor(ev, (Account) req.getSession(true).getAttribute("user"));
                        liked = contrib != null;
                        req.setAttribute("joiners", contributor.getContributors(ev).size());
                        req.setAttribute("like", liked);
                        req.setAttribute("event", ev);
                    }
                } catch (NumberFormatException n){
                    LOGGER.log(Level.SEVERE,"NumberFormatException occured ",n);
                }

            }
        } else if (uri.size() == 2){
            if (uri.get(1).equals("participate")){
                try{
                    Account user = (Account) req.getSession(true).getAttribute("user");
                    Event ev = event.getEvent(Integer.parseInt(uri.get(0)));
                    if (contributor.updateContributorToEvent(ev, user)){
                        try{
                            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events/" + uri.get(0));
                        } catch (IOException e){
                            LOGGER.log(Level.SEVERE,"Exception occured ",e);
                        }

                    }
                    else {
                        try{
                            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                        }catch (IOException e){
                            LOGGER.log(Level.SEVERE,"Exception occured ",e);
                        }

                    }
                    return;
                }catch (NumberFormatException n){
                    LOGGER.log(Level.SEVERE,"NumberFormatException occured ",n);
                }

            } else if (uri.get(0).equals("search")){
                if (uri.get(1).equals("title")){
                    page = "events/searchEvents.jsp";
                    String filter = "title";
                    req.setAttribute("filter", filter);
                } else {
                    page = "categories/categories.jsp";
                    List<Category> categories = categorie.getCategories();
                    req.setAttribute("categories", categories);
                }
                req.setAttribute("page", page);
            } else if (uri.get(1).equals("delete")){
                try{
                    Event ev = event.getEvent(Integer.parseInt(uri.get(0)));
                    List<Contributor> contributors = contributor.getContributors(ev);
                    contributor.deleteContributors(contributors);
                    event.deleteEvent(ev);
                    try{
                        resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                        return;
                    } catch (IOException e){
                        LOGGER.log(Level.SEVERE,"Exception occured ",e);
                    }
                }catch (NumberFormatException n){
                    LOGGER.log(Level.SEVERE,"NumberFormatException occured ",n);
                }
            }

        } else if (uri.size() == 3){
            if (uri.get(0).equals("search")){
                List<Event> events;
                if (uri.get(1).equals("title")){
                    events = event.getEvent(uri.get(2));
                } else {
                    events = event.getEvent(categorie.getCategory(uri.get(2)));
                }
                req.setAttribute("events", events);
                page = "events/events.jsp";
                req.setAttribute("page", page);
            }
        }

        if (req.getSession(true).getAttribute("user") == null){
            try{
                req.getRequestDispatcher("/index.jsp").include(req, resp);
            }catch (IOException e){
                LOGGER.log(Level.SEVERE,"Exception occured ",e);
            }catch (ServletException s){
                LOGGER.log(Level.SEVERE,"Servlet Exception occured ",s);
            }

        } else {
            try{
                req.getRequestDispatcher("/WEB-INF/jsp/welcome.jsp").include(req, resp);
            }catch (IOException e){
                LOGGER.log(Level.SEVERE,"Exception occured ",e);
            }catch (ServletException s){
                LOGGER.log(Level.SEVERE,"Servlet Exception occured ",s);
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqUri = req.getRequestURI();
        List<String> uri = parseUri(reqUri, "events");

        if (uri.size() == 1){
            if (uri.get(0).equals("create")){
                String title = req.getParameter("title");
                String contenu = req.getParameter("contenu");
                String categoryName = req.getParameter("category");

                Category cat = categorie.getCategory(categoryName);

                if (cat != null){
                    Account user = (Account) req.getSession(true).getAttribute("user");
                    if (event.createEvent(title, contenu, user, cat) != null){
                        try{
                            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events");
                        }catch (IOException e){
                            LOGGER.log(Level.SEVERE,"Exception occured ",e);
                        }

                    } else {
                        try{
                            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events/create");
                        }catch (IOException e){
                            LOGGER.log(Level.SEVERE,"Exception occured ",e);
                        }

                    }
                } else {
                    try{
                        resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events/create");
                    }catch (IOException e){
                        LOGGER.log(Level.SEVERE,"Exception occured ",e);
                    }

                }
            }
        } else if (uri.size() == 2){
            if (uri.get(0).equals("search") && uri.get(1).equals("title")){
                String textFilter = req.getParameter("text-filter");
                try{
                    resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + "/events/search/title/" + textFilter);
                }catch (IOException e){
                    LOGGER.log(Level.SEVERE,"Exception occured ",e);
                }


            }
        }
    }

}
