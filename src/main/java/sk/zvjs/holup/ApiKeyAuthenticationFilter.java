package sk.zvjs.holup;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import sk.zvjs.holup.user.UserService;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

class ApiKeyAuthenticationFilter implements Filter {
    static final private String AUTH_METHOD = "api-key";
    private final UserService userService;

    ApiKeyAuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException  {
        if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            String apiKey = getApiKey((HttpServletRequest) request);
            if(apiKey != null) {
                try {
                    String validApiKey = userService.checkApiKey(apiKey);
                    if (apiKey.equals(validApiKey)) {
                        ApiKeyAuthenticationToken apiToken = new ApiKeyAuthenticationToken(apiKey, AuthorityUtils.NO_AUTHORITIES);
                        SecurityContextHolder.getContext().setAuthentication(apiToken);
                    } else {
                        invalidAPIKey(response);
                        return;
                    }
                } catch (Exception e) {
                    invalidAPIKey(response);
                    return;
                }
            }
        }

        chain.doFilter(request, response);

    }

    private void invalidAPIKey(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(401);
        httpResponse.getWriter().write("Invalid API Key");
    }

    private String getApiKey(HttpServletRequest httpRequest) {
        String apiKey = null;

        String authHeader = httpRequest.getHeader("Authorization");
        if(authHeader != null) {
            authHeader = authHeader.trim();
            if(authHeader.toLowerCase().startsWith(AUTH_METHOD + " ")) {
                apiKey = authHeader.substring(AUTH_METHOD.length()).trim();
            }
        }

        return apiKey;
    }
}
