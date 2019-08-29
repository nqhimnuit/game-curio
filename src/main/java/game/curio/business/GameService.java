package game.curio.business;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import game.curio.entities.GameEntity;
import game.curio.entities.GameEntity_;

/**
 * @author AdNovum Informatik AG
 */
@Stateless
public class GameService extends ServiceSupport {

	public GameEntity getGameById(Long id) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<GameEntity> query = cb.createQuery(GameEntity.class);
		Root<GameEntity> root = query.from(GameEntity.class);
		query.where(cb.equal(root.get(GameEntity_.id), id));
		return getSingleResult(em.createQuery(query).getResultList());
	}

}
