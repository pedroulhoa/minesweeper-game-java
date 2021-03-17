package br.com.app.cm.model;

import br.com.app.cm.exception.ExplosionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldTest {

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

    @Test
    void testDefaultValueToggleMark() {
        Assertions.assertFalse(field.isMarked());
    }

    @Test
    void testToggleMark() {
        field.toggleMark();
        Assertions.assertTrue(field.isMarked());
    }

    @Test
    void testToggleMarkTwoTogle() {
        field.toggleMark();
        field.toggleMark();
        Assertions.assertFalse(field.isMarked());
    }

    @Test
    void testOpenNotMineNotMark() {
        Assertions.assertTrue(field.open());
    }

    @Test
    void testOpenNotMineMark() {
        field.toggleMark();
        Assertions.assertFalse(field.open());
    }

    @Test
    void testOpenMineAndMark() {
        field.toggleMark();
        field.setMine();
        Assertions.assertFalse(field.open());
    }

    @Test
    void testOpenWithNeighbors() {
        Field neighbor1 = new Field(1, 1);
        Field neighbor2 = new Field(2, 2);
        neighbor2.addNeighbor(neighbor1);

        field.addNeighbor(neighbor2);
        field.open();

        Assertions.assertTrue(neighbor2.isOpen() && neighbor1.isOpen());
    }

    @Test
    void testOpenWithNeighbors2() {
        Field neighbor1 = new Field(1, 1);
        Field neighbor2 = new Field(1, 1);
        neighbor2.setMine();

        Field neighbor3 = new Field(2, 2);
        neighbor3.addNeighbor(neighbor1);
        neighbor3.addNeighbor(neighbor2);

        field.addNeighbor(neighbor3);
        field.open();

        Assertions.assertTrue(neighbor3.isOpen() && neighbor1.isClose());
    }

    @Test
    void testOpenMineAndNotMark() {
        field.setMine();

        Assertions.assertThrows(ExplosionException.class, () -> {
            field.open();
        });
    }

}
