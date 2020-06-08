package my.pkg.olegclass;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimesTest {
    @Test
    public void testPrimesFast (){
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testNonePrimes (){
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 2));
    }
    @Test (enabled = false)
    public void testPrimesLong (){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }
}
