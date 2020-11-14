package javaForTest;

import java.util.ArrayList;

public class Topic_03_Data_Types {
	public static void main(String[] args) {
		// Kieu du lieu ky tu
		char a = 'A';
		
		// kieu du lieu so nguyen duong/am
		byte first_num = -15;
		short second_num = 12124;
		
		// kieu du lieu chuoi
		String fullname= "Nguyen Van A";
		
		// kieu mang
		String[] addresses= {"Ha Noi", "Ho Chi Minh", "Da Nang"};
		int[] prices= {20000,50000,600000};	
		
		//Kieu class
		Topic_03_Data_Types topic_03 = new Topic_03_Data_Types();
		
		// Kieu object (Javascript executor)
		
		// Kieu collection (ArrayList/Hashmap)
		
		//CRUD: Create, Read, Update, Delete
		ArrayList<String> address = new ArrayList<String>();
		address.add("Ho Chi Minh");
	}
	
	public void setAddress() {
		
	}
	
	public String getAddress() {
		return "";
	}
}
