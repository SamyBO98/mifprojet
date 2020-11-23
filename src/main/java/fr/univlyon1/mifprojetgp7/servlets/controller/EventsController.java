package fr.univlyon1.mifprojetgp7.servlets.controller;

import fr.univlyon1.mifprojetgp7.metier.CategoryM;
import fr.univlyon1.mifprojetgp7.metier.ContributorM;
import fr.univlyon1.mifprojetgp7.metier.EventM;
import fr.univlyon1.mifprojetgp7.metier.InterestM;
import fr.univlyon1.mifprojetgp7.model.Account;
import fr.univlyon1.mifprojetgp7.model.Interest;
import fr.univlyon1.mifprojetgp7.model.Event;
import fr.univlyon1.mifprojetgp7.model.Category;
import fr.univlyon1.mifprojetgp7.model.Contributor;

import javax.persistence.EntityManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static fr.univlyon1.mifprojetgp7.utils.ParseURI.parseUri;
import static fr.univlyon1.mifprojetgp7.utils.ParseURI.sourceURI;

@WebServlet(name = "EventsController", urlPatterns = {"/events", "/events/*"})
// We use our doGet to manage many actions so we need more than 15 Cognitive Complexity
@SuppressWarnings({"squid:S3776", "squid:S2209", "squid:S2696"})
public class EventsController extends HttpServlet {

    private static EventM event;
    private static CategoryM categorie;
    private static ContributorM contributor;
    private static InterestM interest;
    private static final Logger LOGGER = Logger.getLogger(EventsController.class.getName());
    private static final int NB_3 = 3;
    private static final int NB_4 = 4;
    private static final String EXCEPTION_OCCURED = "Exception occured ";
    private static final String TITLE_STRING = "title";
    private static final String NUMBER_FORMAT_EXCEPTION = "NumberFormatException occured ";
    private static final String EVENTS_URI = "/events";
    private static final String SEARCH_STRING = "search";
    private static final String EVENTS_JSP_URI = "events/events.jsp";
    private static final String EVENTS_STRING = "events";

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.event = new EventM((EntityManager) config
                .getServletContext().getAttribute("em"));
        this.categorie = new CategoryM((EntityManager) config
                .getServletContext().getAttribute("em"));
        this.contributor = new ContributorM((EntityManager) config
                .getServletContext().getAttribute("em"));
        this.interest = new InterestM((EntityManager) config
                .getServletContext().getAttribute("em"));
    }

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp) throws ServletException, IOException {
        String reqUri = req.getRequestURI();
        List<String> uri = parseUri(reqUri, EVENTS_STRING);
        String page = null;
        if (uri.isEmpty()) { //Liste des évènements de catégories likés
            page = EVENTS_JSP_URI;
            req.setAttribute("page", page);
            List<Interest> interests = interest
                    .getInterests((Account) req.getSession(true)
                            .getAttribute("user"));
            List<Event> events = new ArrayList<>();
            for (Interest inte: interests) {
                events.addAll(event.getEvent(inte.getCategory()));
            }
            req.setAttribute(EVENTS_STRING, events);
        } else if (uri.size() == 1) {
            if (uri.get(0).equals("all")) { //Liste de tout les évènements
                page = EVENTS_JSP_URI;
                req.setAttribute("page", page);
                List<Event> events = event.getEvents();
                req.setAttribute(EVENTS_STRING, events);
            } else if (uri.get(0).equals("create")) { //Appel vers la création d'évènement
                page = "events/createEvent.jsp";
                req.setAttribute("page", page);
                List<Category> categories = categorie.getCategories();
                req.setAttribute("categories", categories);
            } else if (uri.get(0).equals(SEARCH_STRING)) {
                //Appel vers la page de recherche d'évènements filtré
                page = "events/searchEvents.jsp";
                req.setAttribute("page", page);
            } else if (uri.get(0).equals("created")) { //Liste des évènements crées
                page = EVENTS_JSP_URI;
                req.setAttribute("page", page);
                List<Event> events = event
                        .getEvent((Account) req.getSession(true)
                                .getAttribute("user"));
                req.setAttribute(EVENTS_STRING, events);
            } else if (uri.get(0).equals("participate")) { //Liste des évènements ou on participe
                page = EVENTS_JSP_URI;
                req.setAttribute("page", page);
                List<Contributor> contributors = contributor
                        .getContributors((Account) req.getSession(true)
                                .getAttribute("user"));
                List<Event> events = event.getEvents(contributors);
                req.setAttribute(EVENTS_STRING, events);
            } else {
                //Appel vers un évènement unique
                // (redirection vers /events si l'évènement n'existe pas)
                try {
                    Event ev = event.getEvent(Integer.parseInt(uri.get(0)));
                    if (ev == null) {
                        sendRedirectAndReturn(req, resp, EVENTS_URI);
                    } else {
                        page = "events/event.jsp";
                        req.setAttribute("page", page);
                        boolean liked;
                        Contributor contrib = contributor.getContributor(ev,
                                (Account) req.getSession(true).getAttribute("user"));
                        liked = contrib != null;
                        req.setAttribute("joiners", contributor.getContributors(ev).size());
                        req.setAttribute("like", liked);
                        req.setAttribute("event", ev);
                    }
                } catch (NumberFormatException n) {
                    LOGGER.log(Level.SEVERE, NUMBER_FORMAT_EXCEPTION, n);
                }
            }
        } else if (uri.size() == 2) {
            if (uri.get(1).equals("participate")) {
                 try {
                    Account user = (Account) req.getSession(true).getAttribute("user");
                    Event ev = event.getEvent(Integer.parseInt(uri.get(0)));
                    if (contributor.updateContributorToEvent(ev, user)) {
                        tryAndCatchRedirect(req, resp, "/events/", uri.get(0));
                    } else {
                        tryAndCatchRedirect(req, resp, EVENTS_URI);
                    }
                    return;
                 } catch (NumberFormatException n) {
                    LOGGER.log(Level.SEVERE, NUMBER_FORMAT_EXCEPTION, n);
                }
            } else if (uri.get(0).equals(SEARCH_STRING)) {
                if (uri.get(1).equals(TITLE_STRING)) {
                    page = "events/searchEvents.jsp";
                    String filter = TITLE_STRING;
                    req.setAttribute("filter", filter);
                } else {
                    page = "categories/categories.jsp";
                    List<Category> categories = categorie.getCategories();
                    req.setAttribute("categories", categories);
                }
                req.setAttribute("page", page);
            } else if (uri.get(1).equals("delete")) {
                 try {
                    Event ev = event.getEvent(Integer.parseInt(uri.get(0)));
                    List<Contributor> contributors = contributor.getContributors(ev);
                    contributor.deleteContributors(contributors);
                    event.deleteEvent(ev);
                    sendRedirectAndReturn(req, resp, "/events/created");
                 } catch (NumberFormatException n) {
                    LOGGER.log(Level.SEVERE, NUMBER_FORMAT_EXCEPTION, n);
                }
            } else {
                sendRedirectAndReturn(req, resp, EVENTS_URI);
            }
        } else if (uri.size() == NB_3) {
            if (uri.get(0).equals(SEARCH_STRING)) {
                List<Event> events;
                if (uri.get(1).equals(TITLE_STRING)) {
                    events = event.getEvent(uri.get(2));
                } else {
                    events = event.getEvent(categorie.getCategory(uri.get(2)));
                }
                req.setAttribute(EVENTS_STRING, events);
                page = EVENTS_JSP_URI;
                req.setAttribute("page", page);
            } else {
                sendRedirectAndReturn(req, resp, EVENTS_URI);
            }
        } else if (uri.size() == NB_4) {
            if (uri.get(0).equals(SEARCH_STRING) && uri.get(1)
                    .equals("category") && uri.get(NB_3).equals("react")) {
                Category category = categorie.getCategory(uri.get(2));
                boolean update = interest.updateInterest(category,
                        (Account) req.getSession(true).getAttribute("user"));
                if (update) {
                    tryAndCatchRedirect(req, resp, "/events/search/categories");
                } else {
                    tryAndCatchRedirect(req, resp, EVENTS_URI);
                }
                return;
            } else {
                sendRedirectAndReturn(req, resp, EVENTS_URI);
            }
        } else {
            // Pareil
             try {
                resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + EVENTS_URI);
                return;
             } catch (IOException e) {
                LOGGER.log(Level.SEVERE, EXCEPTION_OCCURED, e);
            }
        }
        if (req.getSession(true).getAttribute("user") == null) {
            tryAndCatchRequest(req, resp, "/index.jsp");
        } else {
            tryAndCatchRequest(req, resp, "/WEB-INF/jsp/welcome.jsp");
        }
    }

    private void sendRedirectAndReturn(final HttpServletRequest req,
                                       final HttpServletResponse resp,
                                       final String lastParam) {
        try {
            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + lastParam);
            return;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, EXCEPTION_OCCURED, e);
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp) throws ServletException, IOException {
        String reqUri = req.getRequestURI();
        List<String> uri = parseUri(reqUri, EVENTS_STRING);

        if (uri.size() == 1 && uri.get(0).equals("create")) {
                String title = req.getParameter(TITLE_STRING);
                String contenu = req.getParameter("contenu");
                String categoryName = req.getParameter("category");

                Category cat = categorie.getCategory(categoryName);

                if (cat != null) {
                    Account user = (Account) req.getSession(true).getAttribute("user");
                    if (event.createEvent(title, contenu, user, cat) != null) {
                        //
                        tryAndCatchRedirect(req, resp, "/events/created");

                    } else {
                        //
                        tryAndCatchRedirect(req, resp, "/events/create");

                    }
                }

        } else if (uri.size() == 2 && uri.get(0).equals(SEARCH_STRING)
                && uri.get(1).equals(TITLE_STRING)) {
                String textFilter = req.getParameter("text-filter");
                tryAndCatchRedirect(req, resp, "/events/search/title/", textFilter);
        } else {
            tryAndCatchRedirect(req, resp, EVENTS_URI);

        }
    }

    private void tryAndCatchRedirect(final HttpServletRequest req,
                                     final HttpServletResponse resp,
                                     final String lastParamRedirect) {
         try {
            resp.sendRedirect("/" + sourceURI(req.getRequestURI()) + lastParamRedirect);
            return;
         } catch (IOException e) {
            LOGGER.log(Level.SEVERE, EXCEPTION_OCCURED, e);
        }
    }

    private void tryAndCatchRedirect(final HttpServletRequest req, final HttpServletResponse resp,
                                     final String lastParamRedirect, final String textFilter) {
         try {
            resp.sendRedirect("/" + sourceURI(req.getRequestURI())
                    + lastParamRedirect + textFilter);
            return;
         } catch (IOException e) {
            LOGGER.log(Level.SEVERE, EXCEPTION_OCCURED, e);
        }
    }

    private void tryAndCatchRequest(final HttpServletRequest req,
                                    final HttpServletResponse resp, final String param) {
         try {
            req.getRequestDispatcher(param).include(req, resp);
         } catch (IOException e) {
            LOGGER.log(Level.SEVERE, EXCEPTION_OCCURED, e);
         } catch (ServletException s) {
            LOGGER.log(Level.SEVERE, "Servlet Exception occured ", s);
        }
    }

}
