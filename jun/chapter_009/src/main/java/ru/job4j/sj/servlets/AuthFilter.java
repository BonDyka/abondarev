package ru.job4j.sj.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter for authentication of user.
 *
 * @author Alexander Bondarev(mailto:bondarew2507@gmail.com).
 * @since 11.03.2018.
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("login") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
