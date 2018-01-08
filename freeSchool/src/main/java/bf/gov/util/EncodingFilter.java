package bf.gov.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Cette classe est un servlet filter qui implémente le codage UTF-8 du canal de communication entre les browser et le serveur. 
 * Tout dans cette application est codée en UTF-8.
 * 
 * @author trasmiro
 *
 */
public class EncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        if (filterChain != null) {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    @Override
    public void destroy() {
        this.encoding = null;
    }
}
