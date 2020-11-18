package nc.opt.exercice7batch;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.OutputCaptureRule;

import java.util.logging.Logger;

@SpringBootTest
class Exercice7BatchApplicationTests {

	@Rule
	public final OutputCaptureRule output = new OutputCaptureRule();

	@Test
	void testDefaultSettings() {
		int v = SpringApplication.exit(SpringApplication.run(Exercice7BatchApplication.class));
		Logger.getGlobal().info("val exit = "+v);
		System.out.println("Exit code: ");
		Assertions.assertThat(SpringApplication.exit(SpringApplication.run(Exercice7BatchApplication.class))).isEqualTo(0);
		Assertions.assertThat(this.output.getOut().contains("SimpleJob: [name=exercice7]"));
	}

	@Test
	void contextLoads() {
	}

}
