package entity;

import entity.Orders;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-05-01T01:12:03")
@StaticMetamodel(Addresses.class)
public class Addresses_ { 

    public static volatile SingularAttribute<Addresses, String> country;
    public static volatile SingularAttribute<Addresses, String> city;
    public static volatile SingularAttribute<Addresses, String> street;
    public static volatile SingularAttribute<Addresses, String> postcode;
    public static volatile SingularAttribute<Addresses, String> state;
    public static volatile CollectionAttribute<Addresses, Orders> ordersCollection;
    public static volatile SingularAttribute<Addresses, Users> userId;
    public static volatile SingularAttribute<Addresses, Integer> addressId;

}