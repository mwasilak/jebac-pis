package pl.sixpinetrees.tournament.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        retrieveToken(request).ifPresent(token -> processTokenForAuthentication(request, token));
        chain.doFilter(request, response);
    }

    private Optional<String> retrieveToken(HttpServletRequest request) {

        final String requestTokenHeader = request.getHeader("Authorization");
        if (StringUtils.hasText(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            return Optional.ofNullable(requestTokenHeader.substring(7));
        }
        return Optional.empty();
    }

    private void processTokenForAuthentication(HttpServletRequest request, String jwtToken) {
        tokenUtil.getUsernameFromToken(jwtToken)
                .ifPresent(username -> authenticateUserRequest(request, username));
    }

    private void authenticateUserRequest(HttpServletRequest request, String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        setAuthenticationForRequest(request, userDetails);
    }

    private void setAuthenticationForRequest(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
