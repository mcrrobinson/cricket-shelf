package entity;

import entity.Books;
import entity.OrderHasBookPK;
import entity.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-28T16:16:45")
@StaticMetamodel(OrderHasBook.class)
public class OrderHasBook_ { 

    public static volatile SingularAttribute<OrderHasBook, Integer> quantity;
    public static volatile SingularAttribute<OrderHasBook, Books> books;
    public static volatile SingularAttribute<OrderHasBook, OrderHasBookPK> orderHasBookPK;
    public static volatile SingularAttribute<OrderHasBook, Double> itemCostOnPurchase;
    public static volatile SingularAttribute<OrderHasBook, Orders> orders;

}