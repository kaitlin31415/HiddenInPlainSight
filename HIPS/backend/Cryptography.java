package HIPS.backend;

/**
 * Parent class to Encryptor and Decryptor classes. Inherits from CryptoSystem
 * super class. Encapsulates common instance variables among Encryptor and
 * Decryptor classes.
 *
 * @author JARK
 */
public abstract class Cryptography extends CryptoSystem {

    protected HipsImage image;
    protected String message;

    /**
     * Constructor Instantiates instance variables for Encryptor.
     *
     * @param imagePath the directory path where the image is kept.
     * @param initialMessage contains the message.
     */
    public Cryptography(String imagePath, String initialMessage) {

        super(imagePath, initialMessage);
        this.image = new HipsImage(imagePath);
        this.message = initialMessage;
    }

    /**
     * Constructor Instantiates instance variables for Decryptor.
     *
     * @param imagePath, is the path of the image file.
     */
    public Cryptography(String imagePath) {

        super(imagePath);
        this.image = new HipsImage(imagePath);
        this.message = "This is not the string you're looking for.";
    }

}
