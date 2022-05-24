package package1;

import java.io.*;

import java.util.*;

/*Erik Nielsen
erik.nielsen@bellevuecollege.edu
Student ID: 950696790 
May 30th 2019
This program will read a list of usernames and write a new username to a file that fits
the correct criteria*/

public class UserNames {

	public static void main(String[] args) throws FileNotFoundException {
		String userstxt = "users.txt";
		String passwordtxt = "passwords.txt";
		boolean Usernames = true; // These will be used to tell the methods which type of file they are dealing with.
		boolean Passwords = false;
		String[] usernameArray = makeArray(userstxt); //Make Array finds length of file and makes an array to match it.
		usernameArray = readFile(usernameArray, userstxt); //Read file will put the file contents into an array and decrypt the password file.
		String[] passwordArray = makeArray(passwordtxt);
		passwordArray = readFile(passwordArray, passwordtxt);
		login(usernameArray, passwordArray); //Lets user login to make changes using one of the existing user name and password combinations.
		printArray(usernameArray, Usernames); //Outputs array info to console
		printArray(passwordArray, Passwords);
		String newuser = newUsername(usernameArray); //Lets user input a new user name
		String newpassword = newPassword(); //Lets user input a new password
		writeArray(newuser, usernameArray); //Writes new info to arrays
		writeArray(newpassword, passwordArray);
		printArray(usernameArray, Usernames);
		printArray(passwordArray, Passwords);
		writefile(usernameArray, userstxt); //Writes updated arrays to files
		writefile(passwordArray, passwordtxt);
	}

	public static String[] makeArray(String file) throws FileNotFoundException {
		Scanner input = new Scanner(new File(file));
		int length = 1;
		while (input.hasNextLine()) {
			input.nextLine();
			length++;
		}
		System.out.println(length);
		String[] users = new String[length];
		return users;
	}

	public static String[] readFile(String[] users, String file) throws FileNotFoundException {
		Scanner input = new Scanner(new File(file));
		for (int i = 0; i < users.length - 1; i++) {
			users[i] = input.nextLine();
			if (file.equals("passwords.txt")) { //The inside of this if statement decrypts the password file and turns it back into a usable string
				char[] passwords = new char[users[i].length() / 3];
				int k = -1;
				for (int j = 0; j <= users[i].length() - 1; j++) {

					if (users[i].charAt(j) != '[' && users[i].charAt(j) != ']' && users[i].charAt(j) != ' '
							&& users[i].charAt(j) != ',') {
						k++;
						passwords[k] = (char) (users[i].charAt(j) - 2);
					}
				}

				users[i] = new String(passwords);

			}
		}

		return users;
	}

	public static void printArray(String[] users, boolean user) {
		if (user) {
			System.out.println("List of Usernames");
		} else {
			System.out.println("List of Passwords");
		}
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				System.out.println(users[i]);
			}
		}
	}

	public static String newUsername(String[] users) {
		boolean correctinput = true;
		String NewUsername = "0";
		do {
			correctinput = true;
			System.out.print("\nCreate a new user: ");
			Scanner input = new Scanner(System.in);
			NewUsername = input.next();
			int firstchar = NewUsername.charAt(0) + 0;
			if (firstchar >= 48 && firstchar <= 57) {
				System.out.print("Username must not start with a number. ");
				correctinput = false;
			}
			if (NewUsername.length() < 4) {
				System.out.print("Username must have four or more characters. ");
				correctinput = false;
			}
			if (NewUsername.length() > 6) {
				System.out.print("Username must have six or less characters. ");
				correctinput = false;
			}
			if (!NewUsername.matches(".*\\d.*")) {
				System.out.print("Username must contain a number. ");
				correctinput = false;
			}
			boolean UpperCase = false;
			boolean LowerCase = false;
			for (int i = 0; i <= NewUsername.length() - 1; i++) {
				char Usernamechar = NewUsername.charAt(i);
				if (Usernamechar >= 65 && Usernamechar <= 90) {
					UpperCase = true;
				}
				if (Usernamechar >= 97 && Usernamechar <= 122) {
					LowerCase = true;
				}
			}
			if (!LowerCase || !UpperCase) {
				System.out.print("Username must have an upper and lower case letter. ");
				correctinput = false;
			}
			for (int i = 0; i <= users.length - 2; i++) {
				String userline = users[i];
				if (userline.equals(NewUsername)) {
					System.out.print("Username has already been taken. ");
					correctinput = false;
				}
			}

		} while (!correctinput);
		return NewUsername;
	}

	public static void writeArray(String newusername, String[] usernamearray) {
		int length = usernamearray.length - 1;
		usernamearray[length] = newusername;
		System.out.println("User " + newusername + " added successfully!\n");
	}

	public static void writefile(String[] array, String file) throws FileNotFoundException {
		PrintStream output = new PrintStream(new File(file));
		for (int i = 0; i <= array.length - 1; i++) {
			if (file.equals("users.txt")) {
				output.println(array[i]);
			}
			if (file.equals("passwords.txt")) {
				char[] passwords = array[i].toCharArray();
				for (int j = 0; j <= passwords.length - 1; j++) {
					passwords[j] = (char) (passwords[j] + 2);
				}
				output.println(Arrays.toString(passwords));
			}
		}
	}

	public static void login(String[] username, String[] password) {
		boolean correctlogin = false;

		do {
			Scanner input = new Scanner(System.in);
			System.out.print("\nPlease type in username: ");
			String inputusername = input.next();
			System.out.print("\nPlease type in password: ");
			String inputpassword = input.next();

			for (int i = 0; i <= username.length - 1; i++) {
				if (inputusername.equals(username[i]) && inputpassword.equals(password[i])) {
					correctlogin = true;
				}
			}
			if (!correctlogin) {
				System.out.println("Incorrect Username or Password.");
			}
		} while (!correctlogin);
		System.out.println("Succesfull log in\n");
	}

	public static String newPassword() {
		Scanner input = new Scanner(System.in);
		System.out.print("\nPlease enter new password: ");
		String newpassword = input.next();
		return newpassword;
	}

}
