@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b"),
    @NamedQuery(name = "Books.findByBookId", query = "SELECT b FROM Books b WHERE b.bookId = :bookId"),
    @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title"),
    @NamedQuery(name = "Books.findLikeTitle", query = "SELECT b FROM Books b WHERE b.title LIKE :title"),
    @NamedQuery(name = "Books.findLastBook", query = "SELECT b FROM Books b WHERE b.bookId=(SELECT max(b.bookId) FROM Books b)"),
    @NamedQuery(name = "Books.findByEdition", query = "SELECT b FROM Books b WHERE b.edition = :edition"),
    @NamedQuery(name = "Books.findBySalesPrice", query = "SELECT b FROM Books b WHERE b.salesPrice = :salesPrice"),
    @NamedQuery(name = "Books.findByPublishYear", query = "SELECT b FROM Books b WHERE b.publishYear = :publishYear")})


private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BOOK_ID")
    private Integer bookId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "EDITION")
    private String edition;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALES_PRICE")
    private double salesPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PUBLISH_YEAR")
    private int publishYear;
    @Lob
    @Column(name = "THUMBNAIL")
    private Serializable thumbnail;
    @ManyToMany(mappedBy = "booksCollection")
    @JsonIgnore
    private Collection<Authors> authorsCollection;
    @ManyToMany(mappedBy = "booksCollection")
    @JsonIgnore
    private Collection<Genres> genresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books")
    @JsonIgnore
    private Collection<OrderHasBook> orderHasBookCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "books")
    @JsonIgnore
    private Collection<BasketHasBook> basketHasBookCollection;
    @OneToMany(mappedBy = "recentBookId")
    @JsonIgnore
    private Collection<Users> usersCollection;