package entity;

import entity.Books;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-28T16:16:45")
@StaticMetamodel(Authors.class)
public class Authors_ { 

    public static volatile CollectionAttribute<Authors, Books> booksCollection;
    public static volatile SingularAttribute<Authors, String> name;
    public static volatile SingularAttribute<Authors, Integer> authorId;

}