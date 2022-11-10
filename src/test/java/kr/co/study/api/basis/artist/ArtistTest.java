package kr.co.study.api.basis.artist;

import kr.co.study.api.common.engine.test.SuperTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since       2022.11.01
 * @author      aslan
 * @description artist test
 **********************************************************************************************************************/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class ArtistTest extends SuperTest {

	@Test
	public void t01_getPage() {
		ArtistHelper.add(ArtistHelper.addArtist());
		ArtistHelper.add(ArtistHelper.addArtist());
		ArtistHelper.getPage(ArtistHelper.findArtist());
	}

	@Test
	public void t02_get() {
		ArtistHelper.get(ArtistHelper.add(ArtistHelper.addArtist()));
	}

	@Test
	public void t03_add() {
		ArtistHelper.add(ArtistHelper.addArtist());
	}

	@Test
	public void t04_modify() {
		ArtistHelper.modify(ArtistHelper.add(ArtistHelper.addArtist()),
				ArtistHelper.modifyArtist());
	}

	@Test
	public void t05_remove() {
		ArtistHelper.remove(ArtistHelper.add(ArtistHelper.addArtist()));
	}

}