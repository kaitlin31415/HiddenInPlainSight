package HIPS.backend;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * HipsImage handles the creation of a Pixel RGB Array form an image. HipsImage
 * also handles the creation of an image form a Pixel RGB Array. HipsImage
 * provides Pixel RGB Arrays to Function, Encryptor and Decryptor functions.
 *
 * @author JARK
 */
public class HipsImage {

    private String filePath;
    private File img;
    private int[][][] pxRGBs;
    private int numPixels;
    private int width;
    private int height;
    private BufferedImage image;
    private BufferedImage returnedImage;
    private int[][][] returnedPxRGBs;

    private int redCode = 0;
    private int greenCode = 1;
    private int blueCode = 2;
    private int alphaCode = 3;

    public HipsImage(String filePath) {
        this.filePath = filePath;
        this.img = new File(this.filePath);

        try {
            this.image = ImageIO.read(img);
        } catch (IOException ex) {
            Logger.getLogger(HipsImage.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.returnedImage = this.image;
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        this.numPixels = this.width * this.height;

        createRGBArray();
    }

    /**
     * Sets the path of the file to the image.
     *
     * @param path String contains path to image.
     */
    public void setFilePath(String path) {
        this.filePath = path;
    }

    /**
     * Returns path of the file to the image.
     *
     * @return File path which contains path to image.
     */
    public String getFilePath() {
        return this.filePath;
    }

    private int getARGBVal(int red, int blue, int green, int alpha) {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    /**
     * Create the RGB Array that goes throughout the image.
     */
    private void createRGBArray() {
        this.pxRGBs = new int[this.width][this.height][4];

        for (int x = 0; x < this.pxRGBs.length; x++) {
            for (int y = 0; y < this.pxRGBs[0].length; y++) {
                Color myColour = new Color(this.image.getRGB(x, y));

                this.pxRGBs[x][y][0] = myColour.getRed();
                this.pxRGBs[x][y][1] = myColour.getGreen();
                this.pxRGBs[x][y][2] = myColour.getBlue();
                this.pxRGBs[x][y][3] = myColour.getAlpha();
            }
        }
    }

    /**
     * Returns a triple integer array of pixel values stored in the image.
     *
     * @return pxRGBs triple integer array of pixels.
     */
    public int[][][] getPxRGBArray() {

        return deepCopyPxRGBArray(this.pxRGBs);
    }

    /**
     * Returns a integer of the number of pixels in the image
     *
     * @return Total number of pixels.
     */
    public int getNumPixels() {
        return this.numPixels;
    }

    /**
     * Gets the number of pixels in the width of the image
     *
     * @return Number of pixels in the width of the image.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the number of pixels in the height of the image
     *
     * @return Number of pixels in the height of the image.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets the RGBA values of a specific pixel x and y to the number value
     *
     * @param x contains x coordinate from the top right hand corner of image.
     * @param y contains y coordinate from the top right hand corner of image.
     * @param r red value of pixel.
     * @param g green value of pixel.
     * @param b blue value of pixel.
     * @param a alpha value of pixel.
     */
    public void setRGBVal(int x, int y, int r, int g, int b, int a) {
        int val = this.getARGBVal(r, b, g, a);
        this.returnedImage.setRGB(x, y, val);
    }

    /**
     * Sets the RGB value of a specific pixel x and y to the number value
     *
     * @param x contains x coordinate from the top right hand corner of image.
     * @param y contains y coordinate from the top right hand corner of image.
     * @param r red value of pixel.
     * @param g green value of pixel.
     * @param b blue value of pixel.
     */
    public void setRGBVal(int x, int y, int r, int g, int b) {
        int a = 255;
        int val = this.getARGBVal(r, b, g, a);
        this.returnedImage.setRGB(x, y, val);
    }

    /**
     * Generates an image
     *
     * @param path string of the directory to save to.
     * @throws java.io.IOException
     */
    private void createImage(String path) throws IOException {
        File outputFile = new File(path + "hidden.png");
        ImageIO.write(this.returnedImage, "png", outputFile);

    }

    /**
     * Generates an image
     *
     * @param path is the directory to save to.
     * @param pixels is a 3D integer array with pixel values.
     * @throws java.io.IOException
     */
    public void generateImage(String path, int[][][] pixels) throws IOException {
        for (int x = 0; x < pixels.length; x++) {
            for (int y = 0; y < pixels[0].length; y++) {
                setRGBVal(x, y, pixels[x][y][0], pixels[x][y][1], pixels[x][y][2], pixels[x][y][3]);
            }
        }
        createImage(path);
    }

    /**
     * Creates a deep copy of 3D Array.
     *
     * @return Copied Pixel RGB Array.
     * @param initialpxRGBArray, triple integer array of pixel array of original
     * image.
     */
    private int[][][] deepCopyPxRGBArray(int[][][] startPxRGBArray) {

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
