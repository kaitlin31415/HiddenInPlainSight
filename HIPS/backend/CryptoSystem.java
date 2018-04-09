package HIPS.backend;

import java.util.Random;
import java.util.ArrayList;

/**
 * Parent class to Function and Cryptography classes. Encapsulates common
 * instance variables among Function, Encryptor and Decryptor classes.
 *
 * @author JARK
 */
public abstract class CryptoSystem {

    protected int functionCode;
    protected int RGBCode;
    protected int messageLength;
    protected int[][][] pxRGBArray;
    protected int[] numberArray;
    protected int width;
    protected int height;

    /**
     * Constructor Instantiates instance variables for Function. In particular
     * for the use with the encryption side of Function.
     *
     * @param initialpxRGBArray, pixel array of original image.
     * @param initialnumberArray, array of message ascii codes .
     */
    public CryptoSystem(int[][][] initialpxRGBArray, int[] initialnumberArray) {

        this.width = initialpxRGBArray.length;
        this.height = initialpxRGBArray[0].length;
        this.pxRGBArray = deepCopyPxRGBArray(initialpxRGBArray);
        this.numberArray = initialnumberArray;
        this.messageLength = initialnumberArray.length;
        this.functionCode = functionChooser();
        this.RGBCode = (int) (Math.random() * 6);
    }

    /**
     * Constructor Instantiates instance variables for Function. In particular
     * for the use with the decryption side of Function.
     *
     * @param initialpxRGBArray, pixel array of encoded image.
     * @param initialfunctionCode, int corresponding to the function used in
     * encryption.
     * @param initialRGBCode, int corresponding to how ascii were stored in RGB
     * values.
     * @param initialMessageLength, length of the message.
     */
    public CryptoSystem(int[][][] initialpxRGBArray, int initialfunctionCode, int initialRGBCode, int initialMessageLength) {

        this.width = initialpxRGBArray.length;
        this.height = initialpxRGBArray[0].length;
        this.pxRGBArray = deepCopyPxRGBArray(initialpxRGBArray);
        this.functionCode = initialfunctionCode;
        this.RGBCode = initialRGBCode;
        this.messageLength = initialMessageLength;
        this.numberArray = new int[this.messageLength];
    }

    /**
     * Constructor Instantiates instance variables for Function. In particular
     * for the use with testing the Function class.
     *
     * @param initialpxRGBArray, pixel array of original image.
     * @param initialnumberArray, array of message ascii codes.
     * @param initialRGBCode, chosen to test that RGB code.
     */
    public CryptoSystem(int[][][] initialpxRGBArray, int[] initialnumberArray, int chosenRGB) {

        this.width = initialpxRGBArray.length;
        this.height = initialpxRGBArray[0].length;
        this.pxRGBArray = deepCopyPxRGBArray(initialpxRGBArray);
        this.numberArray = initialnumberArray;
        this.messageLength = initialnumberArray.length;
        this.functionCode = functionChooser();
        this.RGBCode = chosenRGB;
    }

    /**
     * Constructor Instantiates instance variables for Encryptor.
     *
     * @param imagePath the directory path where the image is kept.
     * @param initialMessage contains the message.
     */
    public CryptoSystem(String imagePath, String initialMessage) {

        HipsImage image = new HipsImage(imagePath);
        this.height = image.getHeight();
        this.width = image.getWidth();
        this.messageLength = initialMessage.length();
        this.numberArray = new int[this.messageLength];
        this.pxRGBArray = image.getPxRGBArray();
    }

    /**
     * Constructor Instantiates instance variables for Decryptor.
     *
     * @param path, is the path of the image file.
     */
    public CryptoSystem(String imagePath) {

        HipsImage image = new HipsImage(imagePath);
        this.RGBCode = -1;
        this.functionCode = -1;
        this.messageLength = -1;
        this.pxRGBArray = image.getPxRGBArray();
        this.height = this.pxRGBArray[0].length;
        this.width = this.pxRGBArray.length;
    }

    /**
     ******************************************************************
     * Function Chooser
     * *****************************************************************
     */
    /**
     * Chooses functionCode based on message length and image size.
     */
    private int functionChooser() {

        ArrayList<Integer> funcList = new ArrayList<Integer>();
        if ((((((this.width / 3) * 3) * (((this.height - 1) / 3) * 3)) / 9) * 8) >= this.messageLength) {
            funcList.add(0);
        }

        Random r = new Random();
        return (funcList.get(r.nextInt(funcList.size())));
    }

    /**
     ******************************************************************
     * 3D Array Copy
     * *****************************************************************
     */
    /**
     * Creates a deep copy of 3D array.
     *
     * @param initialpxRGBArray, pixel array of original image.
     * @return copyPxRGArray, copies the pixel RGB array.
     */
    protected final int[][][] deepCopyPxRGBArray(int[][][] startPxRGBArray) {

        int[][][] copyPxRGBArray = new int[startPxRGBArray.length][startPxRGBArray[0].length][startPxRGBArray[0][0].length];
        for (int i = 0; i < startPxRGBArray.length; i++) {
            for (int j = 0; j < startPxRGBArray[i].length; j++) {
                for (int h = 0; h < startPxRGBArray[i][j].length; h++) {
                    copyPxRGBArray[i][j][h] = startPxRGBArray[i][j][h];
                }
            }
        }
        return copyPxRGBArray;
    }

}
