private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Basic(optional = false)
@Column(name = "ADDRESS_ID")
private Integer addressId;
@Basic(optional = false)
@NotNull
@Size(min = 1, max = 250)
@Column(name = "STREET")
private String street;
@Basic(optional = false)
@NotNull
@Size(min = 1, max = 250)
@Column(name = "CITY")
private String city;
@Basic(optional = false)
@NotNull
@Size(min = 1, max = 250)
@Column(name = "STATE")
private String state;
@Basic(optional = false)
@NotNull
@Size(min = 1, max = 250)
@Column(name = "POSTCODE")
private String postcode;
@Basic(optional = false)
@NotNull
@Size(min = 1, max = 250)
@Column(name = "COUNTRY")
private String country;
@OneToMany(cascade = CascadeType.ALL, mappedBy = "addressId")
@JsonIgnore
private Collection<Orders> ordersCollection;
@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
@ManyToOne
@JsonIgnore
private Users userId;