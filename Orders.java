private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ORDER_ID")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TOTAL")
    private double total;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ORDERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ordered;
    @Column(name = "OUT_FOR_DELIVERY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date outForDelivery;
    @Column(name = "DELIVERED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delivered;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders")
    @JsonIgnore
    private Collection<OrderHasBook> orderHasBookCollection;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Addresses addressId;
    @JoinColumn(name = "CARD_ID", referencedColumnName = "CARD_ID")
    @ManyToOne(optional = false)
    private Cards cardId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Users userId;