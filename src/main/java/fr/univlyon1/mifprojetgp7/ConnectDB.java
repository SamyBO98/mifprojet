package fr.univlyon1.mifprojetgp7;

import fr.univlyon1.mifprojetgp7.metier.EventM;
import fr.univlyon1.mifprojetgp7.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/register")
public class ConnectDB extends HttpServlet {

    private EntityManager em;
    private EventM event;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
        event = new EventM(em);
    }

    @Override
    public void destroy() {
        super.destroy();
        em.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("title") != null){
            System.out.println("Post send");
            event.createEvent(req.getParameter("title"), req.getParameter("description"));
        }

        resp.sendRedirect(req.getServletContext().getContextPath()+ "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        resp.sendRedirect(req.getServletContext().getContextPath()+ "/");
    }
}
