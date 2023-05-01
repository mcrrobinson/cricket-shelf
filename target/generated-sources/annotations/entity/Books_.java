package entity;

import entity.Authors;
import entity.BasketHasBook;
import entity.Genres;
import entity.OrderHasBook;
import entity.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-05-01T01:12:03")
@StaticMetamodel(Books.class)
public class Books_ { 

    public static volatile SingularAttribute<Books, String> thumbnail;
    public static volatile SingularAttribute<Books, Integer> publishYear;
    public static volatile SingularAttribute<Books, Double> salesPrice;
    public static volatile SingularAttribute<Books, String> edition;
    public static volatile CollectionAttribute<Books, Authors> authorsCollection;
    public static volatile SingularAttribute<Books, String> title;
    public static volatile CollectionAttribute<Books, OrderHasBook> orderHasBookCollection;
    public static volatile CollectionAttribute<Books, Users> usersCollection;
    public static volatile CollectionAttribute<Books, Genres> genresCollection;
    public static volatile CollectionAttribute<Books, BasketHasBook> basketHasBookCollection;
    public static volatile SingularAttribute<Books, Integer> bookId;

}