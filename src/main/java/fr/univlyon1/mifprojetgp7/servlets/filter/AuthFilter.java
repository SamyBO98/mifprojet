package fr.univlyon1.mifprojetgp7.servlets.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import static fr.univlyon1.mifprojetgp7.utils.ParseURI.parseUri;
import static fr.univlyon1.mifprojetgp7.utils.ParseURI.sourceURI;


public class AuthFilter extends HttpFilter implements Filter {

    private List<String> excludedUrls;
    private static final Logger LOGGER = Logger.getLogger(AuthFilter.class.getName());

    @Override
    protected void doFilter(final HttpServletRequest req, final HttpServletResponse res,
                            final FilterChain chain) throws IOException, ServletException {
        res.setContentType("text/html; charset=UTF-8");
        if (req.getRequestURI().contains("users")) {
            List<String> urls = parseUri(req.getRequestURI(), "users");
            if (urls.size() == 1) {
                String url = urls.get(0);
                if (!excludedUrls.contains(url)) {
                    if (req.getSession(true).getAttribute("user") == null) {
                        res.sendRedirect("/" + sourceURI(req.getRequestURI()));
                        return;
                    } else {
                        LOGGER.log(Level.FINE, "Deconnexion");
                    }
                }
            } else {
                res.sendRedirect("/" + sourceURI(req.getRequestURI()));
                return;
            }
        } else {
            if (req.getSession(true).getAttribute("user") == null) {
                res.sendRedirect("/" + sourceURI(req.getRequestURI()));
                return;
            }
        }

        super.doFilter(req, res, chain);
    }

    @Override
    public void init(final FilterConfig config) throws ServletException {
        String excludePattern = config.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(excludePattern.split(","));
    }
}
