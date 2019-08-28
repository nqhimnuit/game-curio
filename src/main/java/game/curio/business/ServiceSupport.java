package game.curio.business;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author AdNovum Informatik AG
 */
abstract class ServiceSupport {

	@PersistenceContext(unitName = "CURIO-PU")
	protected EntityManager em;

	//@PersistenceUnit(unitName = "CURIO-PU")
	//EntityManagerFactory emf;

}
