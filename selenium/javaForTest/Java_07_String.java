package javaForTest;

public class Java_07_String {
	public static void main(String[] args) {
		String text ="Viewing 72 of 1879 results";
		
		String[] subString = text.trim().split(" ");
		for (int i=0; i < subString.length; i++) {
			System.out.println("Vi tri thu" + i +": "+subString[i]);
		}
		
		System.out.println(extractNumberFromString(text, 1));
		System.out.println(extractNumberFromString(text, 3));
		
	}
	
	public static int extractNumberFromString(String text, int number) {
		String[] subString = text.split(" ");
		int result = Integer.parseInt(subString[number]);
		return result;
	}
}
