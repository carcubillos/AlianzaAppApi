package co.com.gml.alianza.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Entidad con la informacion de cliente.
 * 
 * @author CarlosCubillos
 *
 */
@Data
@Entity
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "sharedkey")
	private String sharedKey;

	@Column(name = "businessid")
	private String businessId;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private Long phone;

	@Column(name = "dateadded")
	private Date dateAdded;

	/**
	 * Constructor vacio
	 */
	public Client() {

	}

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param sharedKey
	 * @param businessId
	 * @param email
	 * @param phone
	 * @param dateAdded
	 */
	public Client(Long id, String sharedKey, String businessId, String email, Long phone, Date dateAdded) {
		this.id = id;
		this.sharedKey = sharedKey;
		this.businessId = businessId;
		this.email = email;
		this.phone = phone;
		this.dateAdded = dateAdded;
	}

	/**
	 * Constructor
	 * 
	 * @param sharedKey
	 * @param businessId
	 * @param email
	 * @param phone
	 */
	public Client(String sharedKey, String businessId, String email, Long phone) {
		this.sharedKey = sharedKey;
		this.businessId = businessId;
		this.email = email;
		this.phone = phone;
	}

}
