package org.flo99rida.myjsps;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/actor.jsp")
public class ActorFilter implements Filter {
    /**
     * @param filterConfig a <code>FilterConfig</code> object containing the filter's configuration and initialization
     *                     parameters
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    /**
     * @param request  the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


            if(Integer.parseInt(request.getParameter("actorID")) == 10) {
                response.getOutputStream().println("Don't Recall actorID 10 again!");
            }else{
                chain.doFilter(request,response);
            }



    }

    /**
     *
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
