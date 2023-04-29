/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Addresses;
import entity.Authors;
import entity.BasketHasBook;
import entity.BasketHasBookPK;
import entity.Books;
import entity.Cards;
import entity.Genres;
import entity.OrderHasBook;
import entity.OrderHasBookPK;
import entity.Orders;
import entity.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.AddressesFacade;
import session.BasketHasBookFacade;
import session.BooksFacade;
import session.CardsFacade;
import session.GenresFacade;
import session.OrderHasBookFacade;
import session.OrdersFacade;
import session.UsersFacade;


/**
 *
 * @author Squash
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {
    "/api/cart/add", 
    "/api/cart/list", 
    "/api/order/place",
    "/api/orders/list",
    "/api/orders/product",
    "/api/addresses",
    "/api/address/add",
    "/api/cards",
    "/api/card/add",
    "/api/recently-viewed",
    "/api/recently-added",
    "/api/related-books",
    "/checkout", 
    "/book", 
    "/orders", 
    "/search", 
    "/basket",
    "/"})
public class ControllerServlet extends HttpServlet {
    private ServletContext ctx;
    
    @EJB
    private OrdersFacade ordersFacade;
    
    @EJB
    private UsersFacade usersFacade;
    
    @EJB
    private BooksFacade booksFacade;
    
    @EJB
    private CardsFacade cardsFacade;
    
    @EJB
    private AddressesFacade addressesFacade;
    
    @EJB
    private BasketHasBookFacade basketFacade;
    
    @EJB
    private OrderHasBookFacade orderFacade;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.ctx = config.getServletContext();
    }
    
    private double getTotalFromBookCollection(Collection<BasketHasBook> bookCollection){
        double runningTotal = 0;
        for(BasketHasBook basketBook : bookCollection){
            Integer qty = basketBook.getQuantity();
            double bookSalesPrice = basketBook.getBooks().getSalesPrice();
            
            runningTotal += qty * bookSalesPrice;
        }
        return runningTotal;
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
        
//        Object id_attribute = session.getAttribute("id");
//        if(id_attribute==null){
//            response.sendRedirect("/cricket-shelf/login");
//            return;
//        } else {
//            try {
//                id = (int)id_attribute;
//            } catch (ClassCastException e) {
//                System.out.println("User session ID was set with a non-int value, resetting session...");
//                session.invalidate();
//                response.sendRedirect("/cricket-shelf/login");
//                return;
//            }
//        }
        id = 107;
        
        
        switch(userPath){
            case "/api/related-books":
                String bookId = request.getParameter("id");
                int bookIdAsInt;
                try {
                    bookIdAsInt = Integer.parseInt(bookId);
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    break;
                }
                
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                
                // Gets the books related to the books genre.
                Books book = booksFacade.find(bookIdAsInt);
                Collection<Genres> genreCollection = book.getGenresCollection();
                List<Books> listOfBooks = new ArrayList<Books>();  
                for(Genres genre: genreCollection){
                    for(Books bookFromGenre : genre.getBooksCollection()){
                        
                        if(bookFromGenre.getBookId() != book.getBookId()){
                            listOfBooks.add(bookFromGenre);
                        }
                        
                    }
                }
                
                response.getWriter().write(new ObjectMapper().writeValueAsString(listOfBooks));
                break;
                
            case "/api/cart/list":
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                
                response.getWriter().write(new ObjectMapper().writeValueAsString(usersFacade.find(id).getBasketHasBookCollection().toArray()));
                break;
            case "/api/orders/list":
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(new ObjectMapper().writeValueAsString(usersFacade.find(id).getOrdersCollection().toArray()));
                break;
            case "/api/orders/product":
                Integer orderIdAsInt;
                String orderId = request.getParameter("id");
                
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                
                try {
                    orderIdAsInt = Integer.parseInt(orderId);
                } catch (NumberFormatException err) {
                    err.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    break;
                }
                
                Orders order = ordersFacade.find(orderIdAsInt);
                if(order == null){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    break;
                }
                
                response.getWriter().write(new ObjectMapper().writeValueAsString(order.getOrderHasBookCollection().toArray()));
                break;
            case "/api/addresses":
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(new ObjectMapper().writeValueAsString(usersFacade.find(id).getAddressesCollection().toArray()));
                break;
            case "/api/cards":
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(new ObjectMapper().writeValueAsString(usersFacade.find(id).getCardsCollection().toArray()));
                break;
            case "/api/recently-viewed":
                Books recentlyViewedBook;
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                try {
                    recentlyViewedBook = usersFacade.find(id).getRecentBookId();
                    System.out.println(recentlyViewedBook);
                } catch (java.lang.NullPointerException err){
                    System.out.println("User returned null pointer on recent book, they likely haven't viewed a book yet.");
                    response.getWriter().write("null");
                    break;
                }
                response.getWriter().write(new ObjectMapper().writeValueAsString(recentlyViewedBook));
                break;
            case "/api/recently-added":
                Books recentlyAddedBook;
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                
                try {
                    recentlyAddedBook = booksFacade.findLastBook();
                } catch (java.lang.NullPointerException err){
                    System.out.println("Recently added books returned null pointer, there are likely no books in the dir.");
                    response.getWriter().write("null");
                    break;
                }
                response.getWriter().write(new ObjectMapper().writeValueAsString(recentlyAddedBook));
                break;
            case "/book":
                String singleBookId = request.getParameter("id");
                
                Books singleBook = booksFacade.find(Integer.parseInt(singleBookId));
                ctx.setAttribute("book", singleBook);
                
                Collection<Authors> singleAuthorsCollection = singleBook.getAuthorsCollection();
                ctx.setAttribute("authors", singleAuthorsCollection);

                Collection<Genres> singleGenreCollection = singleBook.getGenresCollection();
                ctx.setAttribute("genres", singleGenreCollection);
                request.getRequestDispatcher("/WEB-INF/view/book.jsp").forward(request, response);
                break;
            case "/search":
                String search = request.getParameter("search");                
                List<Books> books = booksFacade.findLikeTitle(search);
                ctx.setAttribute("books", books);
                
                request.getRequestDispatcher("/WEB-INF/view/search.jsp").forward(request, response);
                break;
            case "/index":
                System.out.println("Not yet implemented...");
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
        
        int id;
        String userPath = request.getServletPath();             
        HttpSession session = request.getSession();
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
           
        switch(userPath){
            case "/api/cart/add":
                String serialisedBookAdd = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                AddBookClass deserialisedBook = new ObjectMapper().readValue(serialisedBookAdd, AddBookClass.class);
                basketFacade.create(new BasketHasBook(new BasketHasBookPK(id, deserialisedBook.bookId), deserialisedBook.quantity));
                System.out.println("added book... sucess");
                
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.sendRedirect("/cricket-shelf/");
                break;
            case "/api/order/place":
                System.out.println("starting...");
                String serialisedPostBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                OrderPlacePost orderBody = new ObjectMapper().readValue(serialisedPostBody, OrderPlacePost.class);
                
                Integer paymentUserId = cardsFacade.find(orderBody.paymentId).getUserId().getUserId();
                Integer addressUserId = addressesFacade.find(orderBody.addressId).getUserId().getUserId();
                if(paymentUserId != id || addressUserId != id){
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    break;
                }
                
                // Clears basket
                Collection<BasketHasBook> basketBooks = usersFacade.find(id).getBasketHasBookCollection();
                Orders order = new Orders();
                    
                order.setTotal(getTotalFromBookCollection(basketBooks));
                order.setStatus("Ordered");
                order.setOrdered(new Date());
                
                // Why is this requesting an object, should be an integer?
                order.setAddressId(addressesFacade.find(addressUserId));
                order.setCardId(cardsFacade.find(paymentUserId));
                order.setUserId(usersFacade.find(id));
                
                Orders databaseOrder = ordersFacade.createReturnObject(order);
                
                System.out.println(databaseOrder.getOrderId());
                
                for(BasketHasBook book : basketBooks) {
                   
                    Books localBook = book.getBooks();                    
                    OrderHasBook bookOrder = new OrderHasBook(
                            new OrderHasBookPK(
                                    databaseOrder.getOrderId(), 
                                    localBook.getBookId()),
                            book.getQuantity(), 
                            localBook.getSalesPrice());
                    
                    orderFacade.create(bookOrder);
                    basketFacade.remove(book);
                }
                
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.sendRedirect("/cricket-shelf/");
                break;
                
            case "/api/recently-viewed":
                String s = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                BookIdPost body = new ObjectMapper().readValue(s, BookIdPost.class);
                
                // This doesn't feel right...
                usersFacade.find(id).setRecentBookId(booksFacade.find(body.bookId));
                response.setStatus(HttpServletResponse.SC_CREATED);
                break;
            case "/api/address/add":
                String addressString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Addresses address = new ObjectMapper().readValue(addressString, Addresses.class);
                address.setUserId(usersFacade.find(id));
                addressesFacade.create(address);
                
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.sendRedirect("/cricket-shelf/checkout");
                break;
                
            case "/api/card/add":
                String cardString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Cards card = new ObjectMapper().readValue(cardString, Cards.class);
                card.setUserId(usersFacade.find(id));
                cardsFacade.create(card);
                
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.sendRedirect("/cricket-shelf/checkout");
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
