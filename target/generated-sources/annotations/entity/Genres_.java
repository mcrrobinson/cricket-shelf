package entity;

import entity.Books;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-05-01T01:12:03")
@StaticMetamodel(Genres.class)
public class Genres_ { 

    public static volatile SingularAttribute<Genres, Integer> genreId;
    public static volatile CollectionAttribute<Genres, Books> booksCollection;
    public static volatile SingularAttribute<Genres, String> name;

}