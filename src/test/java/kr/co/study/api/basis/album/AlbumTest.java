package kr.co.study.api.basis.album;

import kr.co.study.api.common.engine.test.SuperTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since       2022.11.02
 * @author      aslan
 * @description album test
 **********************************************************************************************************************/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
@Sql("classpath:/sql/album_test.sql")
public class AlbumTest extends SuperTest {

	@Test
	public void t01_getPage() {
		AlbumHelper.add(AlbumHelper.addAlbum());
		AlbumHelper.add(AlbumHelper.addAlbum());
		AlbumHelper.getPage(AlbumHelper.findAlbum());
	}

	@Test
	public void t02_get() {
		AlbumHelper.get(AlbumHelper.add(AlbumHelper.addAlbum()));
	}

	@Test
	public void t03_add() {
		AlbumHelper.add(AlbumHelper.addAlbum());
	}

	@Test
	public void t04_modify() {
		AlbumHelper.modify(AlbumHelper.add(AlbumHelper.addAlbum()),
				AlbumHelper.modifyAlbum());
	}

	@Test
	public void t05_remove() {
		AlbumHelper.remove(AlbumHelper.add(AlbumHelper.addAlbum()));
	}

}