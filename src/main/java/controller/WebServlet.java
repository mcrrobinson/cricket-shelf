/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Authors;
import entity.Books;
import entity.Genres;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BooksFacade;

/**
 *
 * @author Squash
 */
@javax.servlet.annotation.WebServlet(name = "ControllerServlet", urlPatterns = {
    "/checkout", 
    "/book", 
    "/orders", 
    "/search", 
    "/basket"})
public class WebServlet extends HttpServlet {
    private ServletContext ctx;
    
    @EJB
    private BooksFacade booksFacade;
    

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.ctx = config.getServletContext();
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id;
        String userPath = request.getServletPath();             
        HttpSession session = request.getSession();
        
        if(userPath.equals("/api/logout")){
            session.invalidate();
            response.sendRedirect("/cricket-shelf/login");
            return;
        }
        
        Object id_attribute = session.getAttribute("id");
        if(id_attribute==null){
            response.sendRedirect("/cricket-shelf/login");
            return;
        } else {
            try {
                id = (int)id_attribute;
            } catch (ClassCastException e) {
                System.out.println("User session ID was set with a non-int value, resetting session...");
                session.invalidate();
                response.sendRedirect("/cricket-shelf/login");
                return;
            }
        }
        
        // I could do these as fetch requests or JSP things? What would the standard be?
        switch(userPath) {
            case "/book":
                String singleBookId = request.getParameter("id");

                Books singleBook = this.booksFacade.find(Integer.parseInt(singleBookId));
                this.ctx.setAttribute("book", singleBook);

                Collection<Authors> singleAuthorsCollection = singleBook.getAuthorsCollection();
                this.ctx.setAttribute("authors", singleAuthorsCollection);

                Collection<Genres> singleGenreCollection = singleBook.getGenresCollection();
                this.ctx.setAttribute("genres", singleGenreCollection);
                request.getRequestDispatcher("/WEB-INF/view/book.jsp").forward(request, response);
                break;
            case "/search":
                String search = request.getParameter("search");                
                List<Books> books = this.booksFacade.findLikeTitle(search);
                this.ctx.setAttribute("books", books);
                request.getRequestDispatcher("/WEB-INF/view/search.jsp").forward(request, response);
                break;
            default:
                // use RequestDispatcher to forward request internally
                String url = "/WEB-INF/view" + userPath + ".jsp";
                try {
                    request.getRequestDispatcher(url).forward(request, response);
                } catch (IOException | ServletException ex) {
                    ex.printStackTrace();
                }
        }
        
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
