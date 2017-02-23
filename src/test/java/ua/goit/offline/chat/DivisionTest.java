package ua.goit.offline.chat;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by andreymi on 2/13/2017.
 */
public class DivisionTest {

    @Test
    public void testCorrect() {
        Assert.assertEquals(2, div(4, 2), 0.01);
    }

    @Test
    public void testIncorrect() {
        Assert.assertFalse(div(6, 2) == 2);
    }

    @Test(expected = ArithmeticException.class)
    public void testLimits() {
        div(1, 0);
    }

    private double div(int a, int b) {
        return  a / b;
    }

    private double div(double a, double b) {
        return a / b;
    }
}
