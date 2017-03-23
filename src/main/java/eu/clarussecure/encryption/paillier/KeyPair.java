package eu.clarussecure.encryption.paillier;

/**
 * Created by Alberto Blanco on 23/03/2017.
 */
public class KeyPair {
    private PublicKey pk;
    private SecretKey sk;

    public KeyPair(PublicKey pk, SecretKey sk) {
        this.pk = pk;
        this.sk = sk;
    }

    public PublicKey getPublic() {
        return pk;
    }

    public SecretKey getSecret() {
        return sk;
    }
}
