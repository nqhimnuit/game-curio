package game.curio.business;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import game.curio.entities.GameEntity;
import game.curio.entities.GameEntity_;
import game.curio.web.rest.dto.GameDto;

/**
 * @author AdNovum Informatik AG
 */
@Stateless
public class GameService extends ServiceSupport {

	private static final Logger LOG = LogManager.getLogger(GameService.class);

	public GameEntity getGameById(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GameEntity> query = cb.createQuery(GameEntity.class);
		Root<GameEntity> root = query.from(GameEntity.class);
		query.where(cb.equal(root.get(GameEntity_.id), id));
		GameEntity game = getSingleResult(em.createQuery(query).getResultList());
		LOG.info("found game with id = {}: {}", id, game);
		return game;
	}

	public GameDto getGameByTitle(String title) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GameEntity> query = cb.createQuery(GameEntity.class);
		Root<GameEntity> root = query.from(GameEntity.class);
		query.where(cb.equal(root.get(GameEntity_.title), title));
		GameEntity game = getSingleResult(em.createQuery(query).getResultList());
		LOG.info("found game with title = {}: {}", title, game);
		return convertEntityToDto(game);
	}

	private GameDto convertEntityToDto(GameEntity game) {
		if (game == null) {
			return null;
		}
		GameDto result = new GameDto();
		result.setDescription(game.getDescription());
		result.setTitle(game.getTitle());
		result.setPrice(game.getPrice());
		result.setReleaseDate(game.getReleaseDate());
		return result;
	}

	public GameEntity insertGame(String title, Date releaseDate, String description, Double price) {
		LOG.info("entering insertGame....");
		GameEntity game = setGameEntity(title, releaseDate, description, price);
		trx.begin();
		try {
			em.persist(game);
			trx.commit();
		}
		catch (Exception e) {
			LOG.error("ERROR during transaction: {}", e.getMessage());
			trx.rollback();
			return null;
		}
		LOG.info("persisted {}", game.toString());
		return game;
	}

	public void deleteGame(Long gameId) {
		LOG.info("entering deleteGame....");
		GameEntity game = getGameById(gameId);
		em.remove(game);
		trx.begin();
		try {
			trx.commit();
		}
		catch (Exception e) {
			LOG.error("ERROR during delete Game entity: {}", e.getMessage());
			trx.rollback();
		}
		LOG.info("Game deleted!");
	}

	private GameEntity setGameEntity(String title, Date releaseDate, String description,
			Double price) {

		GameEntity game = new GameEntity();
		game.setTitle(title);
		game.setReleaseDate(releaseDate);
		game.setDescription(description);
		game.setPrice(price);
		return game;
	}

}
