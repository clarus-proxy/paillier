import static org.junit.Assert.assertEquals;

import cat.urv.crises.eigenpaillier.paillier.*;
import org.junit.Test;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Alberto Blanco on 23/03/2017.
 */
public class PaillierTest {

    @Test
    public void encryptDecrypt() {
        SecureRandom rng = new SecureRandom();
        KeyPair kp = Paillier.Keygen(2048);

        BigInteger m = null;
        EncryptedInteger c = null;

        for (int i = 2; i < 2048; i *= 2) {
            m = new BigInteger(i, rng);
            c = Paillier.encrypt(kp.getPublic(), m);
            assertEquals(m, Paillier.decrypt(kp.getSecret(), c));
        }
    }

    @Test
    public void homomorphicProperties() {
        SecureRandom rng = new SecureRandom();
        KeyPair kp = Paillier.Keygen(2048);

        BigInteger a = BigInteger.valueOf(rng.nextInt(100));
        BigInteger b = BigInteger.valueOf(rng.nextInt(100));

        EncryptedInteger eA = Paillier.encrypt(kp.getPublic(), a);
        EncryptedInteger eB = Paillier.encrypt(kp.getPublic(), b);

        EncryptedInteger eA_plus_eB = null;
        try {
            eA_plus_eB = eA.sum(eB);
        } catch (Exception e) {
        }
        ;

        EncryptedInteger eA_plus_b = eA.sum(b);
        EncryptedInteger eA_times_b = eA.multiply(b);

        assertEquals(a.add(b), Paillier.decrypt(kp.getSecret(), eA_plus_eB));
        assertEquals(a.add(b), Paillier.decrypt(kp.getSecret(), eA_plus_b));
        assertEquals(a.multiply(b), Paillier.decrypt(kp.getSecret(), eA_times_b));
    }

    @Test
    public void publicKeyEncoding() {
        KeyPair kp = Paillier.Keygen(2048);
        String pk_enc = kp.getPublic().serialize();
        PublicKey pk2 = PublicKey.load(pk_enc);
        assertEquals(kp.getPublic().getN(), pk2.getN());
        assertEquals(kp.getPublic().getG(), pk2.getG());

    }

    @Test
    public void secretKeyEncoding() {
        KeyPair kp = Paillier.Keygen(2048);
        String sk_enc = kp.getSecret().serialize();
        SecretKey sk2 = SecretKey.load(sk_enc, kp.getPublic());
        assertEquals(kp.getSecret().getLambda(), kp.getSecret().getLambda());
        assertEquals(kp.getSecret().getMu(), kp.getSecret().getMu());
    }

    @Test
    public void keyPairEncoding() {
        KeyPair kp = Paillier.Keygen(2048);
        String kp_enc = kp.serialize();

        KeyPair kp2 = KeyPair.load(kp_enc);

        assertEquals("N", kp.getPublic().getN(), kp2.getPublic().getN());
        assertEquals("G", kp.getPublic().getG(), kp2.getPublic().getG());
        assertEquals("L", kp.getSecret().getLambda(), kp2.getSecret().getLambda());
        assertEquals("M", kp.getSecret().getMu(), kp2.getSecret().getMu());
    }

    @Test
    public void encryptedIntegerEncoding() {
        KeyPair kp = Paillier.Keygen(2048);
        int m = 1234;
        EncryptedInteger c = Paillier.encrypt(kp.getPublic(), BigInteger.valueOf(m));
        EncryptedInteger d = EncryptedInteger.load(c.serialize(), kp.getPublic());
        assertEquals(BigInteger.valueOf(m), Paillier.decrypt(kp.getSecret(), d));

    }

}
