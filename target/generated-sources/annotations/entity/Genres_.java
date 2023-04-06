package entity;

import entity.Books;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-06T21:14:17")
@StaticMetamodel(Genres.class)
public class Genres_ { 

    public static volatile SingularAttribute<Genres, Integer> genreId;
    public static volatile CollectionAttribute<Genres, Books> booksCollection;
    public static volatile SingularAttribute<Genres, String> name;

}