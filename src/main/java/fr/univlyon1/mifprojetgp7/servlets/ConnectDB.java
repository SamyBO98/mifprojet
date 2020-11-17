package fr.univlyon1.mifprojetgp7.servlets;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ConnectDB", loadOnStartup = 1, urlPatterns = "/")
public class ConnectDB extends HttpServlet {

    EntityManager em;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.em = Persistence.createEntityManagerFactory("mifprojetgp7-database").createEntityManager();
        config.getServletContext().setAttribute("em", this.em);

        System.out.println("Initialisation successfull");
    }

    @Override
    public void destroy() {
        super.destroy();
        em.close();
    }
}
