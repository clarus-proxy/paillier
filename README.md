# Paillier

## Usage
Generate a KeyPair with Paillier.Keygen(int bitlength), bitlength should be 2048 or bigger.
Get keys from KeyPair with .getPublic() and .getSecret()
Encrypt with Paillier.encrypt(PublicKey pk, BigInteger m)
Decrypt with Paillier.decrypt(SecretKey sk, EncryptedInteger c)

## Homomorphic properties
Paillier allows the sum of encrypted integers, the sum of an encrypted integer and a plaintext integer
and the product of an encrypted integer times a plaintext integer.
* D(E(m1)*E(m2)) = m1 + m2
* D(E(m1)*g^m2)) = m1 + m2
* D(E(m1)^m2)) = m1 * m2

EncryptedIntegers (returned from the Paillier.encrypt method) support these 3 kinds of homomorphic
operations.

Having:
* EncryptedInteger a = Paillier.encrypt(pk, x)
* EncryptedInteger b = Paillier.encrypt(pk, y)
* BigInteger c = z

We can compute: 
* Paillier.decrypt(sk, a.sum(b)) == x + y 
* Paillier.decrypt(sk, a.sum(c)) == x + z
* Paillier.decrypt(sk, a.multiply(c)) == x * z

## Notes
* I have not implemented any serialization/encoding for keys nor values.
* I do not recommend to use this in a production environment.
* No error handling.
