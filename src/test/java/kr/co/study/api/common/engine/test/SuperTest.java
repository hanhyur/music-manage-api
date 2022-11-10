package kr.co.study.api.common.engine.test;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

/**   
 * @since       2018.10.03
 * @author      lucas
 * @description super test
 **********************************************************************************************************************/
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SuperTest {

	@Autowired
	public WebApplicationContext context;

//	@Autowired
//	public FilterChainProxy proxy;

	@Rule
	public JUnitRestDocumentation document = new JUnitRestDocumentation();


	public MockMvc mock;
	public RestDocumentationResultHandler handler;


	@Before
	public void superBefore() {
		handler = MockMvcRestDocumentation.document(
				"{class-name}/{method-name}"
				, preprocessRequest (prettyPrint())
				, preprocessResponse(prettyPrint()));

		mock = MockMvcBuilders.webAppContextSetup(context)
				.apply(documentationConfiguration(document))
				.alwaysDo (handler)
//				.addFilter(proxy)
				.build();

		TestHelper.initialize(mock, handler);
	}
}