package fr.univlyon1.mifprojetgp7.servlets;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "ConnectDB", loadOnStartup = 1, urlPatterns = "/")
public class ConnectDB extends HttpServlet {

    public EntityManager getEm() {
        return em;
    }

    public void setEm(final EntityManager em) {
        this.em = em;
    }

    @java.lang.SuppressWarnings("squid:S2226")
    private EntityManager em;

    @Override
    @java.lang.SuppressWarnings("squid:S106")
    //Enlève l'erreur du system.out pour vérifier de notre côté si la BD s'est bien lancée
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        this.em = Persistence.createEntityManagerFactory("mifprojetgp7-database")
                .createEntityManager();
        config.getServletContext().setAttribute("em", this.em);

        System.out.println("Initialisation successfull");
    }

    @Override
    public void destroy() {
        super.destroy();
        em.close();
    }
}
