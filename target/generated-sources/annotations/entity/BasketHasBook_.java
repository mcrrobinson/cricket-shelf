package entity;

import entity.BasketHasBookPK;
import entity.Books;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-05-01T01:12:03")
@StaticMetamodel(BasketHasBook.class)
public class BasketHasBook_ { 

    public static volatile SingularAttribute<BasketHasBook, Integer> quantity;
    public static volatile SingularAttribute<BasketHasBook, Books> books;
    public static volatile SingularAttribute<BasketHasBook, BasketHasBookPK> basketHasBookPK;
    public static volatile SingularAttribute<BasketHasBook, Users> users;

}