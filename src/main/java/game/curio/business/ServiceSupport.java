package game.curio.business;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author AdNovum Informatik AG
 */
abstract class ServiceSupport {

	static final EntityManager em;

	static {
		em = Persistence.createEntityManagerFactory("CURIO-PU-MARIADB").createEntityManager();
	}

}
