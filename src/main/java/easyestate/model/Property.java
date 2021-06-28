package easyestate.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer price;
	
	@ManyToOne
	@JoinColumn(name = "currencyid", insertable = false, updatable = false)
	private Currency currency;
	private Long currencyid;
	
	@ManyToOne
	@JoinColumn(name = "countryid", insertable = false, updatable = false)
	private Country country;
	private Long countryid;
	
	private String city;
	private String address;
	private String zip;
	
	@ManyToOne
	@JoinColumn(name = "typeid", insertable = false, updatable = false)
	private Type type;
	private Long typeid;
	
	@ManyToOne
	@JoinColumn(name = "statusid", insertable = false, updatable = false)
	private Status status;
	private Long statusid;
	
	private Float area;
	private Integer beds;
	private Integer baths;
	private Integer garages;
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "property_amenity", joinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"),
						inverseJoinColumns = @JoinColumn(name = "amenity_id", referencedColumnName = "id"))
	private Set<Amenity> amenities;
	
	@Column(name = "first_image")
	private String firstImage;
	
	@Column(name = "second_image")
	private String secondImage;
	
	@Column(name = "third_image")
	private String thirdImage;
	
	@Column(name = "fourth_image")
	private String fourthImage;
	
	@ManyToOne
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	private User user;
	private Long userid;
	
	public Property() {}

	public Property(Long id, Integer price, Currency currency, Long currencyid, Country country, Long countryid,
			String city, String address, String zip, Type type, Long typeid, Status status, Long statusid, Float area,
			Integer beds, Integer baths, Integer garages, String description, Set<Amenity> amenities,
			String firstImage, String secondImage, String thirdImage, String fourthImage, User user, Long userid) {
		this.id = id;
		this.price = price;
		this.currency = currency;
		this.currencyid = currencyid;
		this.country = country;
		this.countryid = countryid;
		this.city = city;
		this.address = address;
		this.zip = zip;
		this.type = type;
		this.typeid = typeid;
		this.status = status;
		this.statusid = statusid;
		this.area = area;
		this.beds = beds;
		this.baths = baths;
		this.garages = garages;
		this.description = description;
		this.amenities = amenities;
		this.firstImage = firstImage;
		this.secondImage = secondImage;
		this.thirdImage = thirdImage;
		this.fourthImage = fourthImage;
		this.user = user;
		this.userid = userid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Long getCurrencyid() {
		return currencyid;
	}

	public void setCurrencyid(Long currencyid) {
		this.currencyid = currencyid;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Long getCountryid() {
		return countryid;
	}

	public void setCountryid(Long countryid) {
		this.countryid = countryid;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getStatusid() {
		return statusid;
	}

	public void setStatusid(Long statusid) {
		this.statusid = statusid;
	}

	public Float getArea() {
		return area;
	}

	public void setArea(Float area) {
		this.area = area;
	}

	public Integer getBeds() {
		return beds;
	}

	public void setBeds(Integer beds) {
		this.beds = beds;
	}

	public Integer getBaths() {
		return baths;
	}

	public void setBaths(Integer baths) {
		this.baths = baths;
	}

	public Integer getGarages() {
		return garages;
	}

	public void setGarages(Integer garages) {
		this.garages = garages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Amenity> getAmenities() {
		return amenities;
	}

	public void setAmenities(Set<Amenity> amenities) {
		this.amenities = amenities;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	public String getSecondImage() {
		return secondImage;
	}

	public void setSecondImage(String secondImage) {
		this.secondImage = secondImage;
	}

	public String getThirdImage() {
		return thirdImage;
	}

	public void setThirdImage(String thirdImage) {
		this.thirdImage = thirdImage;
	}

	public String getFourthImage() {
		return fourthImage;
	}

	public void setFourthImage(String fourthImage) {
		this.fourthImage = fourthImage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}
	
}