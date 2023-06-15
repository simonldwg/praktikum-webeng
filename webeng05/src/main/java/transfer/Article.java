package transfer;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.ebean.annotation.Length;
import io.ebean.annotation.NotNull;

@Entity
@Table(name = "ARTICLES")
public class Article {

	@NotNull @Length(30)
	String name;
	float price;
	int quantity;
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false, columnDefinition = "BIGINT auto_increment")
	private Long id;

	public Article(long id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Article(long id, String name, float price, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public Article(String name, float price, int quantity) {
		super();

		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	public Article(Article article) {

		this.id = article.id;
		this.name = article.name;
		this.price = article.price;
		this.quantity = article.quantity;
	}
	public Article() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return "Artikel \"" + name + "\", Artikelnr. " + id + ", " + price + "€, Lagerbestand: " + quantity;
	}
}
