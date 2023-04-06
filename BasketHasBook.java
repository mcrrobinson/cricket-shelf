private static final long serialVersionUID = 1L;
@EmbeddedId
protected BasketHasBookPK basketHasBookPK;
@Basic(optional = false)
@NotNull
@Column(name = "QUANTITY")
private int quantity;
@JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID", insertable = false, updatable = false)
@ManyToOne(optional = false)
private Books books;
@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", insertable = false, updatable = false)
@ManyToOne(optional = false)
@JsonIgnore
private Users users;