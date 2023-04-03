package basicjava;

import java.util.ArrayList;
import java.util.Arrays;

public class CCArrays {
	
	public static void replace(char[] charArray, char toReplace, char replaceWith) {
		
		for (char i : charArray) {
			
			//char[] copy = charArray.clone();
			
			//bypass case sensitivity
			//set default to all char being lower case
			
			if (Character.toLowerCase(i) == toReplace) {
				Arrays.copyOfRange(charArray, replaceWith, toReplace);
			}
			
		
		}
			
	
	}
	
	public static void sortAlphabetic(String[] strArray) {
		
	}

}
