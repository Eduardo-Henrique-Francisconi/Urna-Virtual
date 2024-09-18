package urna.com.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import urna.com.app.AppApplication;
//mvn jacoco:prepare-agent test install jacoco:report

@SpringBootTest(classes = AppApplication.class)
class AppApplicationTests {

	@Test
	void contextLoads() {
	}

}
