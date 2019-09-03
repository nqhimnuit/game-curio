package game.curio.business;

import javax.ejb.Stateless;

import game.curio.entities.DeveloperEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 */
@Stateless
public class DeveloperService extends ServiceSupport {

	private static final Logger LOG = LogManager.getLogger(DeveloperService.class);

	public DeveloperEntity getDeveloperById(Long id) {
		return em.find(DeveloperEntity.class, id);
	}

	public Long insertDeveloper(String name) {
		DeveloperEntity dev = new DeveloperEntity();
		dev.setName(name);
		trx.begin();
		em.persist(dev);
		try {
			trx.commit();
		}
		catch (Exception e) {
			LOG.error("ERROR: error during creating developer entity: {}", e.getMessage());
			trx.rollback();
		}
		LOG.info("Created Developer: {}", dev.toString());
		return dev.getId();
	}

	public void deleteDeveloper(Long id) {
		DeveloperEntity dev = getDeveloperById(id);
		trx.begin();
		em.remove(dev);
		try {
			trx.commit();
		}
		catch (Exception e) {
			LOG.error("ERROR: error during deleting developer entity: {}", e.getMessage());
			trx.rollback();
		}
		LOG.info("Deleted Developer: {}", dev.toString());
	}

}
