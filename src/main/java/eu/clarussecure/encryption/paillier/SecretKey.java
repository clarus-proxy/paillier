package eu.clarussecure.encryption.paillier;

import java.math.BigInteger;

/**
 * Created by Alberto Blanco on 23/03/2017.
 */
public class SecretKey {
    private BigInteger lambda;
    private BigInteger mu;

    public SecretKey(BigInteger lambda, BigInteger mu) {
        this.lambda = lambda;
        this.mu = mu;
    }

    public BigInteger getLambda() {
        return lambda;
    }

    public BigInteger getMu() {
        return mu;
    }
}
