package entity;

import entity.Orders;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-06T21:14:17")
@StaticMetamodel(Cards.class)
public class Cards_ { 

    public static volatile SingularAttribute<Cards, Integer> expirationYear;
    public static volatile SingularAttribute<Cards, String> number;
    public static volatile SingularAttribute<Cards, Integer> cardId;
    public static volatile SingularAttribute<Cards, String> name;
    public static volatile SingularAttribute<Cards, Integer> expirationMonth;
    public static volatile CollectionAttribute<Cards, Orders> ordersCollection;
    public static volatile SingularAttribute<Cards, Users> userId;

}