package basicjava;

public class CCStringsIfAndWhile {

	public static boolean isDigit(char aChar) {
		
		// Checks if character is a digit between 0-9
		if (aChar >= '0' && aChar <= '9') {
			return true;
		} else {
			return false;
		}
	}

	public static int count(String str, String chars) {	
		
		int totalCount = 0;
		
		// Converts possible upper-case letters to lower-case
		String newChars = chars.toLowerCase();
		String newStr = str.toLowerCase();
		
		// Find what characters exists in 2nd string
		for (char s2 : newChars.toCharArray()) {
			
			// Check for similar characters in 1st string
			for (char s1 : newStr.toCharArray()) {
				if (s1 == s2) {
					totalCount++;
				}				
			}		
		}
		
		return totalCount;
	}

	public static int smallestDigit(int num) {		
		
		// Assume and set smallest digit possible as 9
		int smallestDigit = 9;
				
		// Checks if num is a zero digit
		if (num == 0) {
			smallestDigit = num;
		}
				
		// Checks if num is negative, and convert to positive
		if (num < 0) {
			num = -num;
		}
				
		// Checks for smallest digit in num
		while (num > 0) {
					
			// Remainder gets the right most digit from num
			// Assume remainder digit is largest
			int largestDigit = num % 10;
					
			if (smallestDigit > largestDigit) {
				smallestDigit = largestDigit;
			}
					
			// Division removes the right most digit from num	
			num = num / 10;		
				
		}
		
		return smallestDigit;			
	}
	
	
}