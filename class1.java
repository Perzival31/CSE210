package package1;

import java.util.*;

public class class1 {
	/*
	 * Erik Nielsen 
	 * erik.nielsen@bellevuecollege.edu 
	 * Student ID: 950696790 
	 * May 6th, 2019 
	 * The purpose of this program is to find the area of a simple polygon
	 */

	public static void main(String[] args) {
		System.out.print("Define a first shape (1)Rectangle, (2)Triangle, (3)Circle:");
		int type1 = getShapeIndex();
		double area1 = getArea(type1);
		System.out.println(); // Space
		System.out.print("Define a second shape (1)Rectangle, (2)Triangle, (3)Circle:");
		int type2 = getShapeIndex();
		double area2 = getArea(type2);
		System.out.println(); // Space
		String stringtype1 = printarea(type1, area1);
		String stringtype2 = printarea(type2, area2);
		comparison(type1, type2, area1, area2, stringtype1, stringtype2);
	}

	public static int getShapeIndex() { // Scans for 1,2 or 3 to determine the shape
		int right = 1;
		int input1 = 0;
		while (right == 1 || right == 2) { // Loops program if there is an invalid input
			Scanner input = new Scanner(System.in);
			if (right == 2) {
				System.out.println("Invalid Input, please input 1, 2 or 3");
			}
			input1 = input.nextInt();
			if (input1 == 1 || input1 == 2 || input1 == 3) { // Check for right input
				right = 3;
			} else { // Outcome of wrong input
				right = 2;
			}
		}
		return input1;
	}

	public static double getArea(int input1) { // Based on shape picks a method to find area and returns the area to
												// main

		if (input1 == 1) { // Go to method rectangle if input is 1
			double area = rectangle();
			return area;
		}
		if (input1 == 2) { // Go to method triangle if input is 2
			double area = triangle();
			return area;
		}
		double area = circle();
		return area;
	}

	public static double rectangle() { // Area of rectangle
		Scanner input = new Scanner(System.in);
		System.out.print("Rectangle width: ");
		double width = input.nextDouble();
		System.out.print("Rectangle hight: ");
		double hight = input.nextDouble();
		double area = width * hight;
		return area;

	}

	public static double triangle() { // Area of triangle
		Scanner input = new Scanner(System.in);
		System.out.print("Triangle base: ");
		double base = input.nextDouble();
		System.out.print("Triangle hight: ");
		double hight = input.nextDouble();
		double area = (base * hight) / 2.0;
		return area;

	}

	public static double circle() { // Area of circle
		Scanner input = new Scanner(System.in);
		System.out.print("Circle radius: ");
		double radius = input.nextDouble();
		double area = (3.14159 * radius * radius);
		return area;

	}

	public static String printarea(int type, double area) { // Prints area of each shape with correct name and returns
															// string of shape name
		if (type == 1) {
			System.out.println("Rectangle area: " + area);
			return "Rectangle";
		}
		if (type == 2) {
			System.out.println("Triangle area: " + area);
			return "Triangle";
		}
		System.out.println("Circle area: " + area); // if type == 3
		return "Circle";
	}

	public static void comparison(int type1, int type2, double area1, double area2, String stringtype1,
			String stringtype2) {
		// Final step in which the comparison is made between the shape areas
		if (area1 > area2) {
			System.out.println(
					"The area of the " + stringtype1 + "1 seems bigger than the area of the " + stringtype2 + "2");
			return;
		}
		if (area1 < area2) {
			System.out.println(
					"The area of the " + stringtype2 + "2 seems bigger than the area of the " + stringtype1 + "1");
			return;
		}
		System.out
				.println("The area of the " + stringtype1 + "1 seems the same as the area of the " + stringtype2 + "2");
	}
}