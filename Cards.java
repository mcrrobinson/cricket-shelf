private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CARD_ID")
    private Integer cardId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPIRATION_MONTH")
    private int expirationMonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EXPIRATION_YEAR")
    private int expirationYear;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NUMBER")
    private String number;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cardId")
    @JsonIgnore
    private Collection<Orders> ordersCollection;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Users userId;