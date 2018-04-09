package HIPS.backend;

import java.util.Random;
import java.util.ArrayList;

/**
 * Function inherits from CryptoSystem super class. Function provides functions
 * for the Encryption and Decryption of messages. Function handles the altering
 * of the Pixel Values to hide the message.
 *
 * @author JARK
 */
public class Function extends CryptoSystem {

    /**
     * Constructor Constructs a Function object for Encryption. Provides
     * Encryption class access to encoded array and corresponding FunctionCode
     * and RGBCode.
     *
     * @param initialpxRGBArray, Pixel Array of original image.
     * @param initialnumberArray, Array of message Ascii codes.
     */
    public Function(int[][][] initialpxRGBArray, int[] initialnumberArray) {

        super(initialpxRGBArray, initialnumberArray);

        enFunction();
    }

    /**
     * Constructor Constructs a Function object for Decryption. Provides
     * Decryption Class access to decrypted ASCII code int array.
     *
     * @param initialpxRGBArray, Pixel Array of encoded image.
     * @param initialfunctionCode, int corresponding to the function used in
     * encryption.
     * @param initialRGBCode, int corresponding to how ASCII were stored in RGB
     * values.
     * @param initialMessageLength, Length of the message.
     *
     */
    public Function(int[][][] initialpxRGBArray, int initialfunctionCode, int initialRGBCode, int initialMessageLength) {

        super(initialpxRGBArray, initialfunctionCode, initialRGBCode, initialMessageLength);

        deFunction();
    }

    /**
     * Constructor Constructs a Function object for Testing the RGB values.
     * Provides Decryption Class access to decrypted ASCII code int array.
     *
     * @param initialpxRGBArray, Pixel Array of encoded image.
     * @param initialRGBCode, int corresponding to how ASCII were stored in RGB
     * values.
     * @param initialMessageLength, Length of the message.
     *
     */
    public Function(int[][][] initialpxRGBArray, int[] initialnumberArray, int initialRGBCode) {

        super(initialpxRGBArray, initialnumberArray, initialRGBCode);

        enFunction();
    }

    /**
     ******************************************************************
     * GETTERS *****************************************************************
     */
    /**
     * Gets functionCode.
     *
     * @return functioncode
     */
    public int getFunctionCode() {

        return this.functionCode;
    }

    /**
     * Gets RGBCode.
     *
     * @return RGBCode.
     */
    public int getRGBCode() {

        return this.RGBCode;
    }

    /**
     * Gets deep copy of image pixel array.
     *
     * @return Deep copy of image pixel array.
     */
    public int[][][] getPxRGBArray() {

        int[][][] copyPxRGBArray = deepCopyPxRGBArray(this.pxRGBArray);

        return copyPxRGBArray;
    }

    /**
     * Gets array of ASCII codes.
     *
     * @return Array of ASCII codes.
     */
    public int[] getNumberArray() {

        return this.numberArray;
    }

    /**
     ******************************************************************
     * ENCRYPTION CODE
     * *****************************************************************
     */
    /**
     * Directs flow to chosen encryption function using the functionCode.
     */
    private void enFunction() {

        switch (this.functionCode) {

            case 0:
                enEightToOne();
                break;

        }
    }

    /**
     * Directs flow to chosen RGB encryption method using the RGBCode.
     *
     * @param refPixel, Array of reference pixel's RGBA values.
     * @param ASCII, Letter in ASCII.
     * @return Pixel with ASCII code stored in RGB values.
     */
    private int[] enRGBCode(int[] refPixel, int ASCII) {

        switch (this.RGBCode) {

            //RGB
            case 0:
                return enRGB(refPixel, ASCII, 0, 1, 2);
            //RBG
            case 1:
                return enRGB(refPixel, ASCII, 0, 2, 1);
            //BRG	
            case 2:
                return enRGB(refPixel, ASCII, 2, 0, 1);
            //BGR
            case 3:
                return enRGB(refPixel, ASCII, 2, 1, 0);
            //GRB
            case 4:
                return enRGB(refPixel, ASCII, 1, 0, 2);
            //GBR
            case 5:
                return enRGB(refPixel, ASCII, 1, 2, 0);
        }

        System.out.print("RGBCODE FAILED");
        int[] black = {0, 0, 0, 255};

        return black;
    }

    /**
     * Adds ASCII code digits to RGB values of reference pixel. (unless adding
     * would go over 255, max RGB value, then substraction is used).
     *
     * @param refPixel, Array of reference pixel's RGBA values.
     * @param ASCII, Letter in ASCII.
     * @param x, Can be 0, 1 or 2 depending on order of R, G or B values to be
     * extracted.
     * @param y, Can be 0, 1 or 2 depending on order of R, G or B values to be
     * extracted.
     * @param z, Can be 0, 1 or 2 depending on order of R, G or B values to be
     * extracted.
     * @return Pixel with ASCII code stored in RGB values.
     */
    private int[] enRGB(int[] refPixel, int ASCII, int x, int y, int z) {

        int[] newPixel = new int[4];

        if (refPixel[x] <= 245) {
            newPixel[x] = refPixel[x] + ((ASCII / 100) % 10);
        } else {
            newPixel[x] = refPixel[x] - ((ASCII / 100) % 10);
        }

        if (refPixel[y] <= 245) {
            newPixel[y] = refPixel[y] + ((ASCII / 10) % 10);
        } else {
            newPixel[y] = refPixel[y] - ((ASCII / 10) % 10);
        }

        if (refPixel[z] <= 245) {
            newPixel[z] = refPixel[z] + (ASCII % 10);
        } else {
            newPixel[z] = refPixel[z] - (ASCII % 10);
        }

        newPixel[3] = refPixel[3];

        return newPixel;
    }

    /**
     * Function that stores ASCII code in the image's pixel array. The function
     * moves through nine pixels at a time systematically. The eight edge pixels
     * reference the one at their center.
     */
    private void enEightToOne() {

        int[] refPixel;
        int[] newPixel;

        int col = 1;
        int row = 1;

        int count = 0;

        for (int i = 0; i < numberArray.length; i++) {

            if (count == 8) {

                count = 0;

                if (col + 4 < this.width) {
                    col += 3;
                } else {
                    col = 1;
                    row += 3;
                }
            }

            refPixel = this.pxRGBArray[col][row];

            newPixel = enRGBCode(refPixel, numberArray[i]);

            switch (count) {

                case 0:
                    this.pxRGBArray[col][row + 1] = newPixel;
                    break;

                case 1:
                    this.pxRGBArray[col - 1][row + 1] = newPixel;
                    break;

                case 2:
                    this.pxRGBArray[col - 1][row] = newPixel;
                    break;

                case 3:
                    this.pxRGBArray[col - 1][row - 1] = newPixel;
                    break;

                case 4:
                    this.pxRGBArray[col][row - 1] = newPixel;
                    break;

                case 5:
                    this.pxRGBArray[col + 1][row - 1] = newPixel;
                    break;

                case 6:
                    this.pxRGBArray[col + 1][row] = newPixel;
                    break;

                case 7:
                    this.pxRGBArray[col + 1][row + 1] = newPixel;
                    break;
            }

            count++;
        }
    }


    /**
     ******************************************************************
     * DECRYPTION CODE
     * *****************************************************************
     */
    /**
     * Directs flow to chosen decryption function using the functionCode.
     */
    private void deFunction() {

        switch (this.functionCode) {

            case 0:
                deEightToOne();
                break;
        }
    }

    /**
     * Directs flow to chosen RGB encryption method using the RGBCode.
     *
     * @param refPixel, Array of reference pixel's RGBA values.
     * @param newPixel, Array of pixel storing ASCII code character.
     * @return Character in ASCII code.
     */
    private int deRGBCode(int[] refPixel, int[] newPixel) {

        switch (this.RGBCode) {

            //RGB
            case 0:
                return deRGB(refPixel, newPixel, 0, 1, 2);
            //RBG
            case 1:
                return deRGB(refPixel, newPixel, 0, 2, 1);
            //BRG		
            case 2:
                return deRGB(refPixel, newPixel, 2, 0, 1);
            //BGR
            case 3:
                return deRGB(refPixel, newPixel, 2, 1, 0);
            //GRB
            case 4:
                return deRGB(refPixel, newPixel, 1, 0, 2);
            //GBR
            case 5:
                return deRGB(refPixel, newPixel, 1, 2, 0);
        }
        return 88;
    }

    /**
     * Finds ASCII code using difference between the refpixel and newPixel RGB
     * values.
     *
     * @param refPixel, Array of reference pixel's RGBA values.
     * @param newPixel, Array of pixel storing ASCII code character.
     * @param x, Can be 0, 1 or 2 depending on order of R, G or B values to be
     * extracted.
     * @param y, Can be 0, 1 or 2 depending on order of R, G or B values to be
     * extracted.
     * @param z, Can be 0, 1 or 2 depending on order of R, G or B values to be
     * extracted.
     */
    private int deRGB(int[] refPixel, int[] newPixel, int x, int y, int z) {

        String ASCIIStr = "";

        if (refPixel[x] <= 245) {
            ASCIIStr += newPixel[x] - refPixel[x];
        } else {
            ASCIIStr += refPixel[x] - newPixel[x];
        }

        if (refPixel[y] <= 245) {
            ASCIIStr += newPixel[y] - refPixel[y];
        } else {
            ASCIIStr += refPixel[y] - newPixel[y];
        }

        if (refPixel[z] <= 245) {
            ASCIIStr += newPixel[z] - refPixel[z];
        } else {
            ASCIIStr += refPixel[z] - newPixel[z];
        }

        return Integer.parseInt(ASCIIStr);
    }

    /**
     * Function that extracts ASCII code in the image's pixel array. The
     * function moves through nine pixels at a time systematically. The eight
     * edge pixels reference the one at their center.
     */
    private void deEightToOne() {

        int[] refPixel;
        int[] newPixel;

        int col = 1;
        int row = 1;

        int count = 0;

        for (int i = 0; i < this.messageLength; i++) {

            if (count == 8) {

                count = 0;

                if (col + 4 < this.width) {
                    col += 3;
                } else {
                    col = 1;
                    row += 3;
                }
            }

            refPixel = this.pxRGBArray[col][row];

            switch (count) {

                case 0:
                    newPixel = this.pxRGBArray[col][row + 1];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;

                case 1:
                    newPixel = this.pxRGBArray[col - 1][row + 1];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;

                case 2:
                    newPixel = this.pxRGBArray[col - 1][row];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;

                case 3:
                    newPixel = this.pxRGBArray[col - 1][row - 1];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;

                case 4:
                    newPixel = this.pxRGBArray[col][row - 1];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;

                case 5:
                    newPixel = this.pxRGBArray[col + 1][row - 1];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;

                case 6:
                    newPixel = this.pxRGBArray[col + 1][row];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;

                case 7:
                    newPixel = this.pxRGBArray[col + 1][row + 1];
                    this.numberArray[i] = deRGBCode(refPixel, newPixel);
                    break;
            }

            count++;
        }
    }


}
