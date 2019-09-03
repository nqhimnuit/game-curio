package game.curio.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author AdNovum Informatik AG
 */
@Entity
@Table(name = "game")
public class GameEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "release_date")
	private Date releaseDate;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Double price;

	@ManyToMany
	@JoinTable(name = "game_dev_ref",
			joinColumns = @JoinColumn(name = "game_id"),
			inverseJoinColumns = @JoinColumn(name = "developer_id")
	)
	private List<DeveloperEntity> developers;

	@ManyToMany
	@JoinTable(name = "game_pub_ref",
			joinColumns = @JoinColumn(name = "game_id"),
			inverseJoinColumns = @JoinColumn(name = "publisher_id")
	)
	private List<PublisherEntity> publishers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<DeveloperEntity> getDevelopers() {
		return developers;
	}

	public void setDevelopers(List<DeveloperEntity> developers) {
		this.developers = developers;
	}

	public List<PublisherEntity> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<PublisherEntity> publishers) {
		this.publishers = publishers;
	}

	@Override
	public String toString() {
		return "GameEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", releaseDate=" + releaseDate +
				", description='" + description + '\'' +
				", price=" + price +
				'}';
	}
}
