package basicjava;

import java.util.ArrayList;
import java.util.Arrays;

public class CCArrays {
	
	
	public static void replace(char[] charArray, char toReplace, char replaceWith) {	
		
		// Get index when iterating through the array
		int index = Arrays.asList(charArray).indexOf(charArray);
		
		for (char i : charArray) {	
			
			// If character in array matches replacement character, then replace
			if(i == toReplace) {
				charArray[index] = replaceWith;
			}
				
			// If character in array is lower-case, convert any possible upper-case replacement character to lower-case
			if (i == Character.toLowerCase(toReplace) || i == Character.toUpperCase(toReplace)) {
				charArray[index] = replaceWith;
			}
			
			index++;
	
		}			
	
	}
	
	public static void sortAlphabetic(String[] strArray) {
		
	}

}
