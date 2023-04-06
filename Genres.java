private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GENRE_ID")
    private Integer genreId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NAME")
    private String name;
    @JoinTable(name = "BOOK_HAS_GENRE", joinColumns = {
        @JoinColumn(name = "GENRE_ID", referencedColumnName = "GENRE_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")})
    @ManyToMany
    @JsonIgnore
    private Collection<Books> booksCollection;