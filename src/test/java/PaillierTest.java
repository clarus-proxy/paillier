import static org.junit.Assert.assertEquals;

import eu.clarussecure.encryption.paillier.EncryptedInteger;
import eu.clarussecure.encryption.paillier.KeyPair;
import eu.clarussecure.encryption.paillier.Paillier;
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
        } catch (Exception e) {};

        EncryptedInteger eA_plus_b = eA.sum(b);
        EncryptedInteger eA_times_b = eA.multiply(b);

        assertEquals(a.add(b), Paillier.decrypt(kp.getSecret(), eA_plus_eB));
        assertEquals(a.add(b), Paillier.decrypt(kp.getSecret(), eA_plus_b));
        assertEquals(a.multiply(b), Paillier.decrypt(kp.getSecret(), eA_times_b));
    }
}
