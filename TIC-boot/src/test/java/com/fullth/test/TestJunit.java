package com.fullth.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
@DisplayName("A special test case")
public class TestJunit {
	
	@BeforeAll
    void initAll() {
		System.out.println("@BeforeAll");
    }
	
	@BeforeEach
    void init() {
		System.out.println("@BeforeEach");
    }
	
    @Test
    void succeedingTest() {
    	System.out.println("@Test");
    }
    
    @Test
    void failingTest() {
        //fail("a failing test");
    }
    
    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }
    
	@AfterEach
    void tearDown() {
		System.out.println("@Disabled");
    }

    @AfterAll
    static void tearDownAll() {
    	System.out.println("@AfterAll");
    }
    
    @Test
    @DisplayName("Custom test name containing spaces")
    void testWithDisplayNameContainingSpaces() {
    }

    @Test
    @DisplayName("╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
    }
 
    @Test
    @DisplayName("😱")
    void testWithDisplayNameContainingEmoji() {
    }
    
    @Test
    void standardAssertions() {
    	assertEquals(1,1);
    	assertTrue(1<2);
    }

}
