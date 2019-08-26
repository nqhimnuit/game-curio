package game.curio.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author AdNovum Informatik AG
 */
@Entity
@Table(name = "dlc")
public class DlcEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "game_id", nullable = false)
	private GameEntity game;

	@Column(name = "price")
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GameEntity getGame() {
		return game;
	}

	public void setGame(GameEntity game) {
		this.game = game;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "DlcEntity{" +
				"id=" + id +
				", name='" + name + '\'' +
				", gameId=" + game +
				", price=" + price +
				'}';
	}
}
