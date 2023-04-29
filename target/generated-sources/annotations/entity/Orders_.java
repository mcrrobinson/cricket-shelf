package entity;

import entity.Addresses;
import entity.Cards;
import entity.OrderHasBook;
import entity.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-28T16:16:45")
@StaticMetamodel(Orders.class)
public class Orders_ { 

    public static volatile SingularAttribute<Orders, Date> ordered;
    public static volatile SingularAttribute<Orders, Double> total;
    public static volatile SingularAttribute<Orders, Integer> orderId;
    public static volatile SingularAttribute<Orders, Cards> cardId;
    public static volatile SingularAttribute<Orders, Date> outForDelivery;
    public static volatile SingularAttribute<Orders, Date> delivered;
    public static volatile CollectionAttribute<Orders, OrderHasBook> orderHasBookCollection;
    public static volatile SingularAttribute<Orders, Users> userId;
    public static volatile SingularAttribute<Orders, String> status;
    public static volatile SingularAttribute<Orders, Addresses> addressId;

}