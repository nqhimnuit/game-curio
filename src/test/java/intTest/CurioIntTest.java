package intTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;

import game.curio.business.GameService;
import game.curio.entities.GameEntity;
import game.curio.web.CurioSearch;
import game.curio.web.SteamGameSearch;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author AdNovum Informatik AG
 */
@RunWith(Arquillian.class)
public class CurioIntTest {

	@Inject
	private CurioSearch curioSearch;

	@Inject
	private GameService gameService;

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class)
				.addClasses(CurioSearch.class, SteamGameSearch.class, GameService.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void updateOutput() {
		// given:
		assertNull(curioSearch.getOutput());

		// when:
		curioSearch.setInput("");
		curioSearch.updateOutput();

		// then:
		assertEquals("Please enter a game title to search", curioSearch.getOutput());
	}

	@Test
	public void getGameById() {
		// given:
		Long gameId = 1L;

		// when:
		GameEntity game = gameService.getGameById(gameId);

		// then:
		assertNotNull(game);
		assertEquals("Batman: Arkham Asylum Game of the Year Edition", game.getTitle());
	}

}
