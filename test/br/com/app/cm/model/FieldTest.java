package br.com.app.cm.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FieldTest {

    private Field field;

    @BeforeEach
    void initField() {
        field = new Field(3, 3);
    }

    @Test
    void testNeighborDistance1() {
        Field neighbor = new Field(3, 2);
        boolean result = field.addNeighbor(neighbor);
        Assertions.assertTrue(result);
    }

    @Test
    void testNeighborDistance2() {
        Field neighbor = new Field(2, 2);
        boolean result = field.addNeighbor(neighbor);
        Assertions.assertTrue(result);
    }

    @Test
    void testNotNeighbor() {
        Field neighbor = new Field(1, 1);
        boolean result = field.addNeighbor(neighbor);
        Assertions.assertFalse(result);
    }

}
