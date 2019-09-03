package game.curio.business;

import javax.ejb.Stateless;

import game.curio.entities.DlcEntity;
import game.curio.entities.GameEntity;

/**
 * @author AdNovum Informatik AG
 */
@Stateless
public class DlcService extends ServiceSupport {

	public DlcEntity getDlcById(Long id) {
		return em.find(DlcEntity.class, id);
	}

	public void insertDlc(Long gameId, String name, Double price) {
		DlcEntity dlc = new DlcEntity();

		dlc.setGame(em.find(GameEntity.class, gameId));
		dlc.setName(name);
		dlc.setPrice(price);

		em.persist(dlc);
		trx.begin();
		try {
			trx.commit();
		}
		catch (Exception e) {
			trx.rollback();
		}
	}

	public void deleteDlc(Long id) {
		DlcEntity dlc = getDlcById(id);
		em.remove(dlc);

		trx.begin();
		try {
			trx.commit();
		}
		catch (Exception e) {
			trx.rollback();
		}
	}

}
