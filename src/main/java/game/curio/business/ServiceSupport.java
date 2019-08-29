package game.curio.business;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author AdNovum Informatik AG
 */
abstract class ServiceSupport {

	private static final Logger LOG = LogManager.getLogger(ServiceSupport.class);

	static final EntityManager em;

	static {
		em = Persistence.createEntityManagerFactory("CURIO-PU-MARIADB").createEntityManager();
	}

	<T> T getSingleResult(List<T> results) {
		if (results.size() > 1) {
			LOG.error("found more than 1 entities with same primary key");
			return null;
		}

		if (results.size() == 0) {
			LOG.info("found 0 entities");
			return null;
		}

		return results.get(0);
	}
}
