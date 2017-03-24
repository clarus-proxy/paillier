package eu.clarussecure.encryption.paillier;

import org.apache.commons.codec.binary.Base64;

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

    public byte[] serialize() {
        return Base64.encodeInteger(lambda);
    }

    public static SecretKey load(byte[] base64encoding, PublicKey pk) {
       BigInteger lambda = Base64.decodeInteger(base64encoding);
       BigInteger mu = lambda.modInverse(pk.getN());
       return new SecretKey(lambda, mu);
    }
}
