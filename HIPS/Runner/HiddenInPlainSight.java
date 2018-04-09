package HIPS.Runner;

import HIPS.backend.*;
import java.util.Scanner;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * HiddenInPlainSight is the text based user-interface. It allows the user to
 * encrypt and decrypt on the command prompt. Additionally, the user may choose
 * to encrypt or decrypt a specific image.
 *
 * @author JARK
 */
public class HiddenInPlainSight {

    /**
     * Text based menu for HiddenInPlainSight. Here the user chooses to encrypt
     * to an image or decrypt from an image.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) throws IOException {

        Scanner kbReader = new Scanner(System.in);
        System.out.println("");
        System.out.println("Hello!");
        System.out.println("");
        String choice = "";

        while (!choice.equalsIgnoreCase("q")) {

            System.out.println("Do you want to Encrypt or Decrypt? Or Q to quit.");
            System.out.println("");
            choice = kbReader.nextLine();
            System.out.println("");

            if (choice.equalsIgnoreCase("encrypt")) {

                System.out.println("What sentence do you want to hide?");
                System.out.println("");
                String myMessage = kbReader.nextLine();
                System.out.println("");
                System.out.println("If you would like to encrypt onto a specific image Enter the path now. If not hit enter.");
                System.out.println("Note: if on a windows machine use \\\\ instead of \\ for the path.");
                System.out.println("");
                String interumPath = kbReader.nextLine();
                if ( (interumPath.length() < 3 && interumPath.length()>0) && !interumPath.substring(interumPath.length()-3).equals("png") && !interumPath.substring(interumPath.length()-3).equals("jpg")){
					System.out.println("That is not a valid path.");
				}else{
					System.out.println("Encrypting: " + myMessage);
					String path = "Images/2.png";

					if (!interumPath.equals("")) {
						path = interumPath;
					}

					System.out.println(path);
					Encryptor myEncryptor = new Encryptor(path, myMessage);
			
					if (myEncryptor.isLengthValid()) {

						if (myEncryptor.isCharacterValid()) {

							myEncryptor.encrypt();
							System.out.println("If you would like to save your image to a specific directory enter that directory now. If not hit enter.");
							String p = kbReader.nextLine();
                            if (!interumPath.equals("")){
                                if (!(interumPath.substring(p.length()-1).equals("\\") || p.substring(interumPath.length()-1).equals("/"))){
									System.out.println("Not a Valid Filepath, image saved to program source folder.");
                                }
                                
                            }
							File img = new File("hidden.png");
							BufferedImage disp = ImageIO.read(img);
							File file = new File(p + "hidden.png");

							try {

								if (file != null) {

									try {
										ImageIO.write(disp, "png", file);
									} catch (IOException ex) {
									}

									ImageIO.write(disp, "png", file);
									System.out.println("Message encrypted! Check your folder for your image named hidden.png");
									System.out.println("");
								}

							} catch (IOException ex) {
								System.out.println("Not a Valid File");
							}

						} else {
							System.out.println("Invalid Character. Please input basic Ascii text.");
						}
					} else {
						System.out.println("Message Length and Image are not compatible. Choose a different image to encrypt to.");
					}
                }
            } else if (choice.equalsIgnoreCase("decrypt")) {

                System.out.println("What's the path to the image?");
                System.out.println("Note: if on a windows machine use \\\\ instead of \\ for the path.");
                String messageOut = kbReader.nextLine();
                
                if (messageOut.equals("") || (( (messageOut.length() < 3 && messageOut.length()>0) && !messageOut.substring(messageOut.length()-3).equals("png") && !messageOut.substring(messageOut.length()-3).equals("jpg")))){
                    System.out.println("That is not a valid path.");
                }else{
                    //System.out.println("Your Path: " + messageOut);
                    System.out.println("");
                    Decryptor myDecryptor = new Decryptor(messageOut);
                    System.out.println("Your decrypted message is:");
                    System.out.println(myDecryptor.decrypt());
                    System.out.println("");
                }
            } else if (choice.equalsIgnoreCase("q")) {
                System.out.println("Okay Bye!");
            }
        }
        kbReader.close();
    }

}
