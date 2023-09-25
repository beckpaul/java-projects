import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Beckham Paul
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_2_5_4() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(5);
        NaturalNumber pExpected = new NaturalNumber2(5);
        NaturalNumber m = new NaturalNumber2(4);
        NaturalNumber mExpected = new NaturalNumber2(4);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    @Test
    public void testWitnessToCompositness() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber w = new NaturalNumber2(5);

        boolean test = CryptoUtilities.isWitnessToCompositeness(n, w);
        assertEquals(test, true);
    }

    /*
     * Tests of isPrime1
     */

    @Test
    public void testIsPrime1() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(10);

        boolean testTrue = CryptoUtilities.isPrime1(n);
        boolean testFalse = CryptoUtilities.isPrime1(w);
        assertEquals(testTrue, true);
        assertEquals(testFalse, false);
    }

    /*
     * Tests of isPrime2
     */

    @Test
    public void testIsPrime2() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(10);

        boolean testTrue = CryptoUtilities.isPrime2(n);
        boolean testFalse = CryptoUtilities.isPrime2(w);
        assertEquals(testTrue, true);
        assertEquals(testFalse, false);
    }

    /*
     * Tests of generateNextLikelyPrime
     */

    @Test
    public void testGenerateNextLikelyPrime_5() {
        NaturalNumber n = new NaturalNumber2(5);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(CryptoUtilities.isPrime2(n), true);
        assertEquals(CryptoUtilities.isPrime1(n), true);
        assertEquals(n, 7);
    }

}