package kr.co.study.api.common.attach;

import kr.co.study.api.common.attach.form.AttachForm.Response;
import kr.co.study.api.common.engine.test.SuperTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.transaction.annotation.Transactional;

/**
 * @since       2020.02.25
 * @author      lucas
 * @description attach test
 **********************************************************************************************************************/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class AttachTest extends SuperTest {

	@Test
	public void t01_add() {
		AttachHelper.add("/test/attach/chungha.jpeg");
	}

	@Test
	public void t02_remove() {
		Response.FindOne attach = AttachHelper.add("/test/attach/chungha.jpeg");
		AttachHelper.remove(AttachHelper.removeAttach(attach));
	}
}