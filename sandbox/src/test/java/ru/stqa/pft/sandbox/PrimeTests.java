package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Nikolay Pechenin on 22.05.2017.
 */
public class PrimeTests {

    @Test
    public void testPrime() {
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testNonPrime() {
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));

    }

    @Test(enabled = false)
    public void testPrimeLong() {
        Assert.assertTrue(Primes.isPrimeLong(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeFast() {
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

    @Test
    public void testPrimeWhile() {
        Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
    }
}
