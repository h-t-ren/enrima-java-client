package at.ac.iiasa.ime.enrima.client.auth;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//author Hongtao Ren
public class MD5Encoder {
	
	//private final String salt="enrima_GUI_client";
	private final String algorithm="MD5";
	private final  int iterations = 1;
    private final boolean encodeHashAsBase64 = false;
    /**
     * Encodes the rawPass using a MessageDigest.
     * If a salt is specified it will be merged with the password before encoding.
     *
     * @param rawPass The plain text password
     * @param salt The salt to sprinkle
     * @return Hex string of password digest (or base64 encoded string if encodeHashAsBase64 is enabled.
     */
    public  String encodePassword(String rawPass) {


        MessageDigest messageDigest = getMessageDigest();

        byte[] digest;

        try {
            digest = messageDigest.digest(rawPass.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported!");
        }

        // "stretch" the encoded value if configured to do so
        for (int i = 1; i < iterations; i++) {
            digest = messageDigest.digest(digest);
        }

        if (encodeHashAsBase64) {
            return new String(Base64.encode(digest));
        } else {
            return new String(Hex.encode(digest));
        }
    }
    
    /**
     * Get a MessageDigest instance for the given algorithm.
     * Throws an IllegalArgumentException if <i>algorithm</i> is unknown
     *
     * @return MessageDigest instance
     * @throws IllegalArgumentException if NoSuchAlgorithmException is thrown
     */
    protected final MessageDigest getMessageDigest() throws IllegalArgumentException {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }
    

}