package org.flo99rida.myjsps;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class NoCacheFilter implements Filter {
    /**
     * @param request  the <code>ServletRequest</code> object contains the client's request
     * @param response the <code>ServletResponse</code> object contains the filter's response
     * @param chain    the <code>FilterChain</code> for invoking the next filter or the resource
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /*
        * When used together as "Cache-Control: no-cache, no-store, must-revalidate":
            (no-store):        The browser should not store the response
            (no-cache):        If it does cache it anyway, it must revalidate before use
            (must-revalidate): It must strictly follow expiration times
    *        using the 'Expire' Header or max-age attribute in 'Cache-Control' header
    *           The Expires header provides a specific date/time when the content expires, while              max-age specifies a duration in seconds. Modern browsers prefer max-age, but                  setting both ensures compatibility with older browsers.
        * */
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        chain.doFilter(request, response);

    }
}
