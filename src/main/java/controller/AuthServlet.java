package controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Users;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.UsersFacade;

/**
 *
 * @author Squash
 */
@WebServlet(name = "AuthServlet", urlPatterns = {
    "/login", 
    "/signup", 
    "/api/logout",
    "/api/login",
    "/api/signup"})
public class AuthServlet extends HttpServlet {
    
    @EJB
    private UsersFacade usersFacade;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request Servlet request
     * @param response Servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
           
        // if user already logged in...
        if(session.getAttribute("id") != null){
            response.sendRedirect("/cricket-shelf");
            return;
        }
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();   
        HttpSession session = request.getSession();
        
        if(userPath.equals("/api/login")){
            if(session.getAttribute("id") != null){
                response.sendRedirect("/cricket-shelf");
                return;
            }
            
            String serialisedPostBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            LoginPost loginObject = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(serialisedPostBody, LoginPost.class);
            
            List<Users> users = usersFacade.findByEmailAndPassword(loginObject.emailAddress, loginObject.password);
            if(users.isEmpty()){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("text/html");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("Incorrect username or password");
                return;
            } else if(users.size() > 1){ 
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("text/html");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("There exists multiple users. Issues...");
                return;
            } else {
                Users user = users.get(0);
                user.setLoginDate(new Date());
                usersFacade.edit(user);
                
                session.setAttribute("id", user.getUserId());
                session.setAttribute("user", user);
                response.sendRedirect("/cricket-shelf");
                return;
            }
        } else {
            // If the user is already logged in...
            if(session.getAttribute("id") != null){
                response.sendRedirect("/cricket-shelf");
                return;
            }
            
            String serialisedPostBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            SignupPost signupObject = new ObjectMapper().readValue(serialisedPostBody, SignupPost.class);
            List<Users> users = usersFacade.findByEmailAddress(signupObject.emailAddress);
            if(!(users.isEmpty())){
                
                // EMAIL ALREADY EXISTS
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.sendRedirect("/cricket-shelf/signup");
                return;
            }
            
            
            Users user = new Users();
            user.setFirstName(signupObject.firstName);
            user.setLastName(signupObject.lastName);
            user.setEmailAddress(signupObject.emailAddress);
            user.setPassword(signupObject.password);
            user.setAdmin(false);
            user.setBasketTotal(0);
            user.setLoginDate(new Date());
            usersFacade.create(user);
            
            session.setAttribute("id", 6);
            session.setAttribute("user", user);
        }
        response.sendRedirect("/cricket-shelf");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}