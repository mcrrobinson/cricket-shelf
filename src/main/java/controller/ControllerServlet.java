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
    "/api/search",
    "/api/cards",
    "/api/card/add",
    "/api/recently-viewed",
    "/api/recently-added",
    "/api/related-books"})
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
    
    public boolean getAdminStatus(){
        return true;
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
//        id = 107;
        
        
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
            case "/api/orders/list-all":
                response.setContentType("text/json");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write(new ObjectMapper().writeValueAsString(ordersFacade.findAll().toArray()));
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
            default:
                System.out.println("Unrecognised call ...");
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
                
                BasketHasBookPK basketHasBookPk = new BasketHasBookPK(id, deserialisedBook.bookId);
                basketFacade.create(new BasketHasBook(basketHasBookPk, deserialisedBook.quantity)); // Persist
                
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.sendRedirect("/cricket-shelf");
                break;
            case "/api/order/place":
                System.out.println("starting...");
                String serialisedPostBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                OrderPlacePost orderBody = new ObjectMapper().readValue(serialisedPostBody, OrderPlacePost.class);
                
                
                Cards paymentCard = cardsFacade.find(orderBody.paymentId);
                Integer paymentUserId = paymentCard.getUserId().getUserId();

                Addresses addressPresented = addressesFacade.find(orderBody.addressId);
                Integer addressUserId = addressPresented.getUserId().getUserId();
                
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
                order.setAddressId(addressPresented);
                order.setCardId(paymentCard);
                order.setUserId(usersFacade.find(id));
                
                Orders databaseOrder = ordersFacade.createReturnObject(order); // Custom Persist
                
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
            case "/api/order/status":
                String serialisedStatusBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                StatusPost statusBody = new ObjectMapper().readValue(serialisedStatusBody, StatusPost.class);
                
                Orders statusOrder = ordersFacade.find(statusBody.orderId);
                if(id != statusOrder.getUserId().getUserId()){
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
  
                switch(statusBody.status){
                    case 1:
                        statusOrder.setOrdered(statusBody.timestamp);
                        ordersFacade.edit(statusOrder); // Persist
                        break;
                    case 2:
                        statusOrder.setOutForDelivery(statusBody.timestamp);
                        ordersFacade.edit(statusOrder); // Persist
                        break;
                    case 3:
                        statusOrder.setDelivered(statusBody.timestamp);
                        ordersFacade.edit(statusOrder); // Persist
                        break;
                    default:
                        System.out.println(statusBody.status);
                        
                }
                
                // Update here...
                response.setStatus(HttpServletResponse.SC_OK);
                break;
            case "/api/recently-viewed":
                String s = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                BookIdPost body = new ObjectMapper().readValue(s, BookIdPost.class);
                
                // This doesn't feel right...
                Users recentViewUser = usersFacade.find(id);
                recentViewUser.setRecentBookId(booksFacade.find(body.bookId));
                usersFacade.edit(recentViewUser); // Persist
                
                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write("Done!");
                break;
            case "/api/address/add":
                String addressString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Addresses address = new ObjectMapper().readValue(addressString, Addresses.class);
                address.setUserId(usersFacade.find(id));
                addressesFacade.create(address); // Persist
                
                response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
                response.sendRedirect("/cricket-shelf/checkout");
                break;
                
            case "/api/card/add":
                String cardString = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                Cards card = new ObjectMapper().readValue(cardString, Cards.class);
                card.setUserId(usersFacade.find(id));
                cardsFacade.create(card); // Persist
                
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
