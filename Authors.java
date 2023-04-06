private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Basic(optional = false)
@Column(name = "AUTHOR_ID")
private Integer authorId;
@Basic(optional = false)
@NotNull
@Size(min = 1, max = 50)
@Column(name = "NAME")
private String name;
@JoinTable(name = "BOOK_HAS_AUTHOR", joinColumns = {
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "AUTHOR_ID")}, inverseJoinColumns = {
    @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID")})
@ManyToMany
@JsonIgnore
private Collection<Books> booksCollection;