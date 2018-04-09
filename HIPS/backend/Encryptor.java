package HIPS.backend;

import java.io.IOException;

/**
 * Encryptor inherits from Cryptography super class. Encryptor puts RGBCode,
 * FunctionCode and MessageLength into the Pixel RGB Array. Encryptor sends the
 * image to Function to put the message into the Pixel RGB Array. Encryptor then
 * uses the HipsImage class to create an image from a Pixel RGB Array.
 *
 * @author JARK
 */
public class Encryptor extends Cryptography {

    private Function func;
    private int test;

    /**
     * A constructor setting the values.
     *
     * @param imagePath, The directory path where the image is kept.
     * @param initialMessage, Contains the message.
     */
    public Encryptor(String imagePath, String initialMessage) {

        super(imagePath, initialMessage);
        this.test = 10;
    }

    /**
     * A constructor setting the values for testing RGB codes.
     *
     * @param imagePath, The directory path where the image is kept.
     * @param initialMessage, Contains the message.
     */
    public Encryptor(String imagePath, String initialMessage, int chosenRGBCode) {

        super(imagePath, initialMessage);
        this.test = chosenRGBCode;
    }

    /**
     ******************************************************************
     * PUBLIC ENCRYPT METHOD
     * *****************************************************************
     */
    /**
     * Encrypts message string into an image.
     */
    public void encrypt() throws IOException {

        changeStringToIntArray();

        int[][][] copyPxRGBArray = deepCopyPxRGBArray(this.pxRGBArray);

        if (this.test != 10) {
            this.func = new Function(copyPxRGBArray, numberArray, test);
        } else {
            this.func = new Function(copyPxRGBArray, numberArray);
        }

        this.pxRGBArray = deepCopyPxRGBArray(func.getPxRGBArray());

        insertRGBCode();
        insertFunctionCode();
        insertMessageLength();

        int[][][] finalPxRGBArray = deepCopyPxRGBArray(this.pxRGBArray);
        image.generateImage("", this.pxRGBArray);
    }

    /**
     ******************************************************************
     * VALID ENCRYPTION
     * *****************************************************************
     */
    /**
     * Checks if message is short enough be encrypted inside of the image.
     * Checks if message contains any unicode (message shouldn't contain
     * unicode).
     *
     * @return True if valid, false otherwise.
     */
    public boolean isLengthValid() {

        if ((((((this.width / 3) * 3) * (((this.height - 1) / 3) * 3)) / 9) * 8) < this.messageLength) {

            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if message contains any characters not in basic ascii.
     *
     * @return True if message only contains basic ascii, false
     * otherwise.
     */
    public boolean isCharacterValid() {

        for (int i = 0; i < this.message.length(); i++) {
            char letter = this.message.charAt(i);
            int ascii = (int) letter;

            if (ascii > 127) {
                return false;
            }
        }
        return true;
    }

    /**
     ******************************************************************
     * PRIVATE HELPER METHODS
     * *****************************************************************
     */
    /**
     * Change a string into a chars which is put into an array.
     */
    private void changeStringToIntArray() {

        for (int pos = 0; pos < this.messageLength; pos++) {
            this.numberArray[pos] = (int) this.message.charAt(pos);
        }
    }

    /**
     * Inserts RGB code into the array.
     */
    private void insertRGBCode() {

        int[] refPixel = this.pxRGBArray[this.width - 4][this.height - 1];
        int[] newPixel = pixelRGBEncrypt(refPixel, func.getRGBCode());

        this.pxRGBArray[this.width - 3][this.height - 1] = newPixel;
    }

    /**
     * Inserts function code into the array.
     */
    private void insertFunctionCode() {

        int[] refPixel = this.pxRGBArray[this.width - 4][this.height - 1];
        int[] newPixel = pixelRGBEncrypt(refPixel, func.getFunctionCode());

        this.pxRGBArray[this.width - 2][this.height - 1] = newPixel;
    }

    /**
     * Inserts message length into array.
     */
    private void insertMessageLength() {

        int[] refPixel = this.pxRGBArray[this.width - 4][this.height - 1];
        int[] newPixel = pixelRGBEncrypt(refPixel, this.messageLength);

        this.pxRGBArray[this.width - 1][this.height - 1] = newPixel;
    }

    /**
     * Adds number's digits to RGB values of reference pixel. (unless adding
     * would go over 255, max RGB value, then subtraction is used). Returns
     * pixel with number stored in RGB values.
     *
     * @param refPixel, Array of reference pixel's RGBA values.
     * @param num, Number to be hidden.
     */
    private int[] pixelRGBEncrypt(int[] refPixel, int num) {

        int[] newPixel = new int[4];

        if (refPixel[0] <= 245) {
            newPixel[0] = refPixel[0] + ((num / 100) % 10);
        } else {
            newPixel[0] = refPixel[0] - ((num / 100) % 10);
        }

        if (refPixel[1] <= 245) {
            newPixel[1] = refPixel[1] + ((num / 10) % 10);
        } else {
            newPixel[1] = refPixel[1] - ((num / 10) % 10);
        }

        if (refPixel[2] <= 245) {
            newPixel[2] = refPixel[2] + (num % 10);
        } else {
            newPixel[2] = refPixel[2] - (num % 10);
        }

        newPixel[3] = refPixel[3];

        return newPixel;
    }

}
