package com.globallogic.bci.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Encryptor {

    /**
     * Generates KeyPair for encryption (temp) just for demo purposes
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generateRSAPublicKey() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);

        KeyPair keyPair = generator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Convert to PEM format
        String publicKeyPEM = "-----BEGIN PUBLIC KEY-----\n" +
                Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(publicKey.getEncoded()) +
                "\n-----END PUBLIC KEY-----";

        String privateKeyPEM = "-----BEGIN PRIVATE KEY-----\n" +
                Base64.getMimeEncoder(64, new byte[]{'\n'}).encodeToString(privateKey.getEncoded()) +
                "\n-----END PRIVATE KEY-----";

        return publicKeyPEM;
    }

    /**
     * Encrypts a string given the public key and the secret string message
     * based on OpenSSL key pair
     * @param data  The data string to encrypt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encryptStringSSLRSA(String data) throws NoSuchAlgorithmException, InvalidKeySpecException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String publicKeyPEM = generateRSAPublicKey();

        String receivedPublicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        // Convert public key to RSAPublicKey
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedPublicKey = Base64.getDecoder().decode(receivedPublicKeyPEM);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        // Cipher object for encryption
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // doFinal method to encrypt
        byte[] secretMessageBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);

        // Encode with Base64 Alphabet
        String encodedString = Base64.getEncoder().encodeToString(encryptedMessageBytes);

        return encodedString;
    }
}
