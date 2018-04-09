package HIPS.backend;

/**
 * Decryptor inherits from Cryptography super class. Decryptor uses the values
 * in the Pixel RGB Array to find the encrypted message.
 *
 * @author JARK
 */
public class Decryptor extends Cryptography {

    /**
     * Constructor Constructs a Decryptor object with an image. Sets default
     * values for other instance varaibles.
     *
     * @param path, Path of the image file.
     */
    public Decryptor(String imagePath) {

        super(imagePath);
    }

    /**
     ******************************************************************
     * PUBLIC DECRYPT METHOD
     * *****************************************************************
     */
    /**
     * decrpyt takes values hidden in the image and returns the hidden message.
     *
     * @return The hidden message.
     */
    public String decrypt() {

        findFunctionCode();
        findRGBCode();
        findMessageLength();

        int[][][] newPxRGBArray = deepCopyPxRGBArray(this.pxRGBArray);

        Function func = new Function(newPxRGBArray, this.functionCode, this.RGBCode, this.messageLength);

        this.numberArray = func.getNumberArray();
        toMessage();

        return this.message;
    }

    /**
     ******************************************************************
     * PRIVATE HELPER METHODS
     * *****************************************************************
     */
    /**
     * Sets RGBCode to the function code that is stored in the image. The
     * RGBCode is kept two pixel left from the bottom right corner.
     */
    private void findRGBCode() {

        int[] refPixel = this.pxRGBArray[this.width - 4][this.height - 1];
        int[] newPixel = this.pxRGBArray[this.width - 3][this.height - 1];

        this.RGBCode = pixelRGBDecrypt(refPixel, newPixel);
    }

    /**
     * Sets functionCode to the function code that is stored in the image. The
     * functionCode is kept one pixel left from the bottom right corner.
     */
    private void findFunctionCode() {

        int[] refPixel = this.pxRGBArray[this.width - 4][this.height - 1];
        int[] newPixel = this.pxRGBArray[this.width - 2][this.height - 1];

        this.functionCode = this.RGBCode = pixelRGBDecrypt(refPixel, newPixel);
    }

    /**
     * Sets messageLength to the length of the message stored in the image. The
     * messageLength is kept in the bottom right corner.
     */
    private void findMessageLength() {

        int[] refPixel = this.pxRGBArray[this.width - 4][this.height - 1];
        int[] newPixel = this.pxRGBArray[this.width - 1][this.height - 1];

        this.messageLength = pixelRGBDecrypt(refPixel, newPixel);
    }

    /**
     * Takes array of ascii numbers in this.numberarray. Converts the array to a
     * String, this.message.
     */
    private void toMessage() {

        this.message = "";

        String convert;

        for (int i = 0; i < this.messageLength; i++) {

            int pix = this.numberArray[i];

            convert = new Character((char) pix).toString();

            this.message += convert;
        }
    }

    /**
     * Uses the difference between the refpixel's and newPixel RGB values to
     * find number hidden in pixel values.
     *
     * @param refPixel, Array of reference pixel's RGBA values.
     * @param newPixel, Array of pixel storing number.
     * @return Hidden integer.
     */
    private int pixelRGBDecrypt(int[] refPixel, int[] newPixel) {

        String numStr = "";

        if (refPixel[0] <= 245) {
            numStr += newPixel[0] - refPixel[0];
        } else {
            numStr += refPixel[0] - newPixel[0];
        }

        if (refPixel[1] <= 245) {
            numStr += newPixel[1] - refPixel[1];
        } else {
            numStr += refPixel[1] - newPixel[1];
        }

        if (refPixel[2] <= 245) {
            numStr += newPixel[2] - refPixel[2];
        } else {
            numStr += refPixel[2] - newPixel[2];
        }

        return Integer.parseInt(numStr);
    }

}
