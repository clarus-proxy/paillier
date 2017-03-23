package eu.clarussecure.encryption.paillier;

import java.math.BigInteger;

/**
 * Created by Alberto Blanco on 23/03/2017.
 */
public class PublicKey {
    private BigInteger n;
    private BigInteger n2;
    private BigInteger g;

    public PublicKey(BigInteger n, BigInteger g) {
        this.n = n;
        this.n2 = n.multiply(n);
        this.g = g;
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getN2() {
        return n2;
    }

    public BigInteger getG() {
        return g;
    }

    public boolean equals(PublicKey b) {
        return (this.n.equals(b.n) && this.g.equals(b.g));
    }
}
