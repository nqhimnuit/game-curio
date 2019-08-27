package game.curio.business;

import javax.ejb.Stateless;
import javax.transaction.Transactional;

import game.curio.entities.GameEntity;

/**
 * @author AdNovum Informatik AG
 */
@Stateless
@Transactional
public class GameService extends ServiceSupport {

	public GameEntity getGameById(Long id) {
		return emf.createEntityManager().find(GameEntity.class, id);
	}

}
