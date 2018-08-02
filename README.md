# UofC 1stYear CPSC233 Project
Hidden In Plain Sight 
This program allows the user to encrypt/hide text in images and decrypt/uncover text from images. 

### Dependencies:
Make sure you have java 8.0 downloaded and set to your path. 
## Deployment:

### Text-Based 
Open command prompt and set the directory to the HiddenInPlainSight file directory.

#### To Complie: 
Type: 
```
javac HIPS/backend/*.java 
```
into the command prompt or terminal and hit enter.
Type: 
```
javac HIPS/Runner/*.java 
```
into the command prompt or terminal and hit enter.
	
#### To Run: 
Type:
```
java HIPS.Runner.HiddenInPlainSight 
```
into the command prompt or terminal and hit enter.

You will be prompted:
```
Hello!
Do you want to Encrypt or Decrypt? Or Q to quit.
```
From there follow the onscreen instructions to Encrypt or Decrypt. 


### GUI 
Open command prompt and set the directory to the HiddenInPlainSight file directory.

Double click the the runme windows batch file in the main directory.

The Gui will pop up. 

-or- 

To run the Gui manually


To Complie: Type: 
```
javac HIPS/backend/*.java
```
into the command prompt or terminal and hit enter. Type: 
```
javac HIPS/Runner/*.java 
```

into the command prompt or terminal and hit enter.

To Run: Type: 
```
java HIPS.Runner.Gui
```    
into the command prompt or terminal and hit enter.

## Testing 

How to Run the Tests using junit:
Open command prompt and set the directory to the HiddenInPlainSight file directory.

#### Windows:

1. junit-4.12.jar and hamcrest-core-1.3.jar are already in the folder (you do not need to redownload these).
2. Compile your code and the test code using the commands
```
javac -cp .;junit-4.12.jar;hamcrest-core-1.3.jar HIPS/testing/*.java
javac HIPS/backend/*.java 
javac HIPS/Runner/*.java
``` 
3. Run the test using the command 
```
java -cp .;junit-4.12.jar;hamcrest-core-1.3.jar org.junit.runner.JUnitCore HIPS.testing.RunTesting
```
#### Unix:

1. junit-4.12.jar and hamcrest-core-1.3.jar are already in the folder (you do not need to redownload these).
2. Compile your code and the test code using the commands 
```
javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar HIPS/testing/*.java
javac HIPS/backend/*.java 
javac HIPS/Runner/*.java
``` 
3. Run the test using the command 
```
java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore HIPS.testing.RunTesting
```



Built With:
java8.0
javaFx


Authors:
Kaitlin de Chastelain Finnigan
Roxanne Duchesne
Jason Lagasca
Alexander Chao
