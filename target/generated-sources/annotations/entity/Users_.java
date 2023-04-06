package entity;

import entity.Addresses;
import entity.BasketHasBook;
import entity.Books;
import entity.Cards;
import entity.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-06T21:14:17")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> firstName;
    public static volatile SingularAttribute<Users, String> lastName;
    public static volatile SingularAttribute<Users, String> emailAddress;
    public static volatile CollectionAttribute<Users, Addresses> addressesCollection;
    public static volatile CollectionAttribute<Users, Cards> cardsCollection;
    public static volatile SingularAttribute<Users, Boolean> admin;
    public static volatile SingularAttribute<Users, Double> basketTotal;
    public static volatile CollectionAttribute<Users, Orders> ordersCollection;
    public static volatile SingularAttribute<Users, Integer> userId;
    public static volatile CollectionAttribute<Users, BasketHasBook> basketHasBookCollection;
    public static volatile SingularAttribute<Users, Books> recentBookId;

}