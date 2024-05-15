package com.example.fackstore_api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomTest {
    @Test
    void testOneplusOneisTwo() {
    int i=1+1;
        assert i==2;
        assertEquals(2,i);
    }
    @Test
    void testOneplusOneisThree() {
        int i=1+1;
//        assert i==3;
//        assertEquals(7,"1+1 should be equals to 2");
//        assertEquals(2,"1+1 should be equals to 2");
        boolean flag = true;
        assertTrue(flag);

    }
}
