package com.david.fxserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FxServerApplicationTests {

	@Test
	void testTradeCommand() {
		//Need to test in event sourcing way
		//reference - https://docs.axoniq.io/reference-guide/implementing-domain-logic/command-handling/testing
		//Lazy to configure the Fixture, so skipping
	}

}
