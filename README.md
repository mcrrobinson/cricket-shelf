# cricket-shelf
Enterprise Web Architectures Requirements

> CREATE THE TABLES
> NEW FILE > PERSISTENCE > ENTITY CLASSES FROM DATABASE > *FOLLOW WIZARD*
> NEW FILE > PERSITSNCE > SESSION BEANS FOR ENTITY CLASSES > *FOLLOW WIZARD*
> ADD @JsonIgnore TO ALL RELATIONSHIPS E.G 
```java
@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
@ManyToOne(optional = false)
@JsonIgnore
private Users userId;
```
inside of entity.Users.java
