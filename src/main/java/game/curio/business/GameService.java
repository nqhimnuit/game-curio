package game.curio.business;

import javax.ejb.Stateless;

import game.curio.entities.GameEntity;

/**
 * @author AdNovum Informatik AG
 */
@Stateless
public class GameService extends ServiceSupport {

	public GameEntity getGameById(Long id) {
		return em.find(GameEntity.class, id);
	}

}
