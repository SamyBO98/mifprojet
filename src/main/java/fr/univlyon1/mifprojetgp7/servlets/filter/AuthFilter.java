package fr.univlyon1.mifprojetgp7.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static fr.univlyon1.mifprojetgp7.utils.ParseURI.parseUri;
import static fr.univlyon1.mifprojetgp7.utils.ParseURI.sourceURI;


public class AuthFilter extends HttpFilter implements Filter {

    private List<String> excludedUrls;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getRequestURI().contains("users")){
            List<String> urls = parseUri(req.getRequestURI(), "users");
            if (urls.size() == 1){
                String url = urls.get(0);
                if (!excludedUrls.contains(url)){
                    if (req.getSession(true).getAttribute("user") == null){
                        res.sendRedirect("/" + sourceURI(req.getRequestURI()));
                        return;
                    }
                }
            } else {
                res.sendRedirect("/" + sourceURI(req.getRequestURI()));
                return;
            }
        } else {
            if (req.getSession(true).getAttribute("user") == null){
                res.sendRedirect("/" + sourceURI(req.getRequestURI()));
                return;
            }
        }

        super.doFilter(req, res, chain);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String excludePattern = config.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(excludePattern.split(","));
    }
}
