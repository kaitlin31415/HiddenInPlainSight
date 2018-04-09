package HIPS.testing;

import java.io.IOException;

//HiddenInPlainSight Project imports
import HIPS.backend.*;

// JUnit imports
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;

public class RunTesting {
	
/*******************************************************************
* BASIC TESTS
*******************************************************************/	
	
	
    //File file = new File("./TestImages/Black_Test.png");
	//String dirPath = file.getAbsoluteFile().getParentFile().getAbsolutePath();
	
	@Test
	public void test_SameMessage() throws IOException{
		
		String messageIn = "Hello World";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		//System.out.println(messageOut);
		
		assertEquals("Message should be Hello World", messageIn, messageOut);
	}
	
	
	@Test 
	public void test_JPEG() throws IOException{
		
		String messageIn = "Testing String";
		// .jpg file extension
		String path = "Images/testing.jpg";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("jpg should be useable image type", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RedImage() throws IOException{
		
		String messageIn = "Testing String";
		// Image with 255 red RGB value
		String path = "Images/Red_Test.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}	
	
	
	@Test 
	public void test_BlueImage() throws IOException{
		
		String messageIn = "Testing String";
		// Image with 255 blue RGB value
		String path = "Images/Blue_Test.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_GreenImage() throws IOException{
		
		String messageIn = "Testing String";
		// Image with 255 green RGB value
		String path = "Images/Green_Test.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_BlackImage() throws IOException{
		
		String messageIn = "Testing String";
		// Image with 0 in all RGB values
		String path = "Images/Black_Test.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_WhiteImage() throws IOException{
		
		String messageIn = "Testing String";
		// Image with 255 in all RGB values
		String path = "Images/White_Test.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
/*******************************************************************
* TESTING isLengthValid METHOD
*******************************************************************/ 

 	@Test
	public void test_isLengthValidLengthOver() throws IOException{
		
		// 120 character long message
		String messageIn = "TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isLengthValid();
		
		assertEquals("", boo, false);
	} 	
	
	
	@Test
	public void test_isLengthValidLengthUnder() throws IOException{
		
		// 4 character long message
		String messageIn = "Test";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isLengthValid();
		
		assertEquals("", boo, true);
	} 
	
	
		@Test
	public void test_isLengthValidLengthBoundary() throws IOException{
		
		// 120 character long message
		String messageIn = "123456789012345678901234567890123456789012345678901234567890123456789012";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isLengthValid();
		
		assertEquals("", boo, true);
	}
	
	
	@Test
	public void test_isValidLengthBoundaryPlusOne() throws IOException{
		
		// 120 character long message
		String messageIn = "1234567890123456789012345678901234567890123456789012345678901234567890123";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isLengthValid();
		
		assertEquals("", boo, false);
	}
	
	
	@Test
	public void test_IsValidLengthBoundaryMinusOne() throws IOException{
		
		// 120 character long message
		String messageIn = "12345678901234567890123456789012345678901234567890123456789012345678901";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isLengthValid();
		
		assertEquals("", boo, true);
	}

	
/*******************************************************************
* TESTING isCharacterValid METHOD
*******************************************************************/
	
	@Test 
	public void test_isCharacterValidBasicAscii() throws IOException{
		
		// Unicode Characters 753, 760 and 770
		String messageIn = "' !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isCharacterValid();
		
		assertEquals("", true, boo);	
	}	
	
	
	@Test 
	public void test_isCharacterValidBoundryExtendedAscii() throws IOException{
		
		// Extended Ascii Character 128
		String messageIn = "Ç";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isCharacterValid();
		
		assertEquals("", false, boo);	
	}
	

	@Test 
	public void test_isCharacterValidExtendedAscii() throws IOException{
		
		// Extended Ascii Character 128
		String messageIn = "Ä¿ê";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isCharacterValid();
		
		assertEquals("", false, boo);	
	}
	
	
	@Test 
	public void test_isCharacterValidBoundryUnicode() throws IOException{
		
		// Unicode 128
		String messageIn = "Ĩ";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isCharacterValid();
		
		assertEquals("", false, boo);	
	}
	
	
	@Test 
	public void test_isCharacterValidUnicode() throws IOException{
		
		// Unicode Characters 753, 760 and 770
		String messageIn = "ݠݰݓ";
		String path = "Images/2.png";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn);
		boolean boo = myEncryptor.isCharacterValid();
		
		assertEquals("", false, boo);	
	}

/*******************************************************************
* TESTING ALL BASIC ASCII
*******************************************************************/ 
	
	@Test
	public void test_ASCIIWithRGBCode0() throws IOException{
		
		// characters from extended Ascii
		String messageIn = "' !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
		String path = "Images/testing.jpg";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn, 0);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("Should handle characters in extended ascii", messageIn, messageOut);	
	}
	
	
	@Test
	public void test_ASCIIWithRGBCode1() throws IOException{
		
		// characters from extended Ascii
		String messageIn = "' !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
		String path = "Images/testing.jpg";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn, 1);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("Should handle characters in extended ascii", messageIn, messageOut);	
	}
	
	
	@Test
	public void test_ASCIIWithRGBCode2() throws IOException{
		
		// characters from extended Ascii
		String messageIn = "' !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
		String path = "Images/testing.jpg";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn, 2);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("Should handle characters in extended ascii", messageIn, messageOut);	
	}
	
	
	@Test
	public void test_ASCIIWithRGBCode3() throws IOException{
		
		// characters from extended Ascii
		String messageIn = "' !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
		String path = "Images/testing.jpg";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn, 3);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("Should handle characters in extended ascii", messageIn, messageOut);	
	}
	
	
	@Test
	public void test_ASCIIWithRGBCode4() throws IOException{
		
		// characters from extended Ascii
		String messageIn = "' !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
		String path = "Images/testing.jpg";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn, 4);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("Should handle characters in extended ascii", messageIn, messageOut);	
	}
	
	
	@Test
	public void test_ASCIIWithRGBCode5() throws IOException{
		
		// characters from extended Ascii
		String messageIn = "' !\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
		String path = "Images/testing.jpg";
		
		Encryptor myEncryptor = new Encryptor(path, messageIn, 5);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("Should handle characters in extended ascii", messageIn, messageOut);	
	}


/*******************************************************************
* TESTING RED IMAGE (225,0,0)
*******************************************************************/ 
	
	@Test 
	public void test_RGBCode0WithRed() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Red_Test.png";
		
		// set RGBCode to 0
		Encryptor myEncryptor = new Encryptor(path, messageIn, 0);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode1WithRed() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Red_Test.png";
		
		// set RGBCode to 1
		Encryptor myEncryptor = new Encryptor(path, messageIn, 1);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode2WithRed() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Red_Test.png";
		
		// set RGBCode to 2
		Encryptor myEncryptor = new Encryptor(path, messageIn, 2);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}


	@Test 
	public void test_RGBCode3WithRed() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Red_Test.png";
		
		// set RGBCode to 3
		Encryptor myEncryptor = new Encryptor(path, messageIn, 3);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode4WithRed() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Red_Test.png";
		
		// set RGBCode to 4
		Encryptor myEncryptor = new Encryptor(path, messageIn, 4);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode5WithRed() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Red_Test.png";
		
		// set RGBCode to 5
		Encryptor myEncryptor = new Encryptor(path, messageIn, 5);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
/*******************************************************************
* TESTING BLUE IMAGE (0,0,225)
*******************************************************************/ 	

	@Test 
	public void test_RGBCode0WithBlue() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Blue_Test.png";
		
		// set RGBCode to 0
		Encryptor myEncryptor = new Encryptor(path, messageIn, 0);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	@Test 
	public void test_RGBCode1WithBlue() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Blue_Test.png";
		
		// set RGBCode to 1
		Encryptor myEncryptor = new Encryptor(path, messageIn, 1);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode2WithBlue() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Blue_Test.png";
		
		// set RGBCode to 2
		Encryptor myEncryptor = new Encryptor(path, messageIn, 2);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode3WithBlue() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Blue_Test.png";
		
		// set RGBCode to 3
		Encryptor myEncryptor = new Encryptor(path, messageIn, 3);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode4WithBlue() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Blue_Test.png";
		
		// set RGBCode to 4
		Encryptor myEncryptor = new Encryptor(path, messageIn, 4);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode5WithBlue() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Blue_Test.png";
		
		// set RGBCode to 5
		Encryptor myEncryptor = new Encryptor(path, messageIn, 5);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}

	
/*******************************************************************
* TESTING GREEN IMAGE (0,225,0)
*******************************************************************/ 

	@Test 
	public void test_RGBCode0WithGreen() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Green_Test.png";
		
		// set RGBCode to 0
		Encryptor myEncryptor = new Encryptor(path, messageIn, 0);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	

	@Test 
	public void test_RGBCode1WithGreen() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Green_Test.png";
		
		// set RGBCode to 1
		Encryptor myEncryptor = new Encryptor(path, messageIn, 1);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode2WithGreen() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Green_Test.png";
		
		// set RGBCode to 2
		Encryptor myEncryptor = new Encryptor(path, messageIn, 2);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode3WithGreen() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Green_Test.png";
		
		// set RGBCode to 3
		Encryptor myEncryptor = new Encryptor(path, messageIn, 3);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode4WithGreen() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Green_Test.png";
		
		// set RGBCode to 4
		Encryptor myEncryptor = new Encryptor(path, messageIn, 4);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode5WithGreen() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Green_Test.png";
		
		// set RGBCode to 5
		Encryptor myEncryptor = new Encryptor(path, messageIn, 5);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}


/*******************************************************************
* TESTING WHITE IMAGE (225,225,255)
*******************************************************************/ 

	@Test 
	public void test_RGBCode0WithWhite() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/White_Test.png";
		
		// set RGBCode to 0
		Encryptor myEncryptor = new Encryptor(path, messageIn, 0);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode1WithWhite() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/White_Test.png";
		
		// set RGBCode to 1
		Encryptor myEncryptor = new Encryptor(path, messageIn, 1);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode2WithWhite() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/White_Test.png";
		
		// set RGBCode to 2
		Encryptor myEncryptor = new Encryptor(path, messageIn, 2);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode3WithWhite() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/White_Test.png";
		
		// set RGBCode to 3
		Encryptor myEncryptor = new Encryptor(path, messageIn, 3);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode4WithWhite() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/White_Test.png";
		
		// set RGBCode to 4
		Encryptor myEncryptor = new Encryptor(path, messageIn, 4);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode5WithWhite() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/White_Test.png";
		
		// set RGBCode to 5
		Encryptor myEncryptor = new Encryptor(path, messageIn, 5);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
/*******************************************************************
* TESTING BLACK IMAGE (0,0,0)
*******************************************************************/ 
	
	@Test 
	public void test_RGBCode0WithBlack() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Black_Test.png";
		
		// set RGBCode to 0
		Encryptor myEncryptor = new Encryptor(path, messageIn, 0);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode1WithBlack() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Black_Test.png";
		
		// set RGBCode to 1
		Encryptor myEncryptor = new Encryptor(path, messageIn, 1);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode2WithBlack() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Black_Test.png";
		
		// set RGBCode to 2
		Encryptor myEncryptor = new Encryptor(path, messageIn, 2);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode3WithBlack() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Black_Test.png";
		
		// set RGBCode to 3
		Encryptor myEncryptor = new Encryptor(path, messageIn, 3);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode4WithBlack() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Black_Test.png";
		
		// set RGBCode to 4
		Encryptor myEncryptor = new Encryptor(path, messageIn, 4);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	
	
	@Test 
	public void test_RGBCode5WithBlack() throws IOException{
		
		String messageIn = "Testing String";
		String path = "Images/Black_Test.png";
		
		// set RGBCode to 5
		Encryptor myEncryptor = new Encryptor(path, messageIn, 5);
		myEncryptor.encrypt();
		
        Decryptor myDecryptor = new Decryptor("hidden.png");
        String messageOut = myDecryptor.decrypt();
		
		assertEquals("", messageIn, messageOut);		
	}
	

}
