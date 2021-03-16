package br.com.app.cm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Teste {

    @Test
    void testSeIgualADois() {
        int a = 1 + 1;
        Assertions.assertEquals(2, a);
    }

    @Test
    void testSeIgualATres() {
        int a = 1 + 1 + 1;
        Assertions.assertEquals(3, a);
    }

}
