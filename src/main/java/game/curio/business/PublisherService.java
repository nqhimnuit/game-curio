package game.curio.business;

import javax.ejb.Stateless;

import game.curio.entities.PublisherEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 */
@Stateless
public class PublisherService extends ServiceSupport {

	private static final Logger LOG = LogManager.getLogger(PublisherService.class);

	public PublisherEntity getPublisherById(Long id) {
		PublisherEntity publisher = em.find(PublisherEntity.class, id);
		LOG.info("found entity: {}", publisher.toString());
		return publisher;
	}

	public void insertPublisher(String name) {
		PublisherEntity publisher = new PublisherEntity();
		publisher.setName(name);
		em.persist(publisher);
		trx.begin();
		try {
			trx.commit();
		}
		catch (Exception e) {
			trx.rollback();
			LOG.error("error when inserting publisher: {}", e.getMessage());
			return;
		}
		LOG.info("created entity: {}", publisher.toString());
	}

	public void deletePublisher(Long id) {
		PublisherEntity publisher = em.find(PublisherEntity.class, id);
		em.remove(publisher);
		trx.begin();
		try {
			trx.commit();
		}
		catch (Exception e) {
			trx.rollback();
			LOG.error("error when deleting publisher: {}", e.getMessage());
		}
		LOG.info("deleted publisher {}", publisher.toString());
	}

}
