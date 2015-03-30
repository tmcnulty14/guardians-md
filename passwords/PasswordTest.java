package passwords;

import java.util.HashMap;

public class PasswordTest {
	private HashMap<String, PasswordRecord> logins;
	public PasswordTest() {
		logins = new HashMap<>();
	}

	public static void main(String args[]) {
		PasswordTest test = new PasswordTest();
		String[] usernames = {"tmcnulty", "creid1", "tgorman2", "shakkoum"};
		String[] passwords = {"123456", "password", "qwerty", "letmein"};
		test.addAll(usernames, passwords);
		test.testLogins(usernames, passwords);
	}

	public void testLogins(String[] usernames, String[] passwords) {
		if(usernames.length != passwords.length) {
			System.out.println("Username and password list lengths do not match.");
		}

		for(int i=0; i< usernames.length; i++) {
			String username = usernames[i];
			String password = passwords[i];
			PasswordRecord pass = logins.get(username);
			if(pass == null) {
				System.out.println("User " + username + " does not exist.");
				continue;
			}
			System.out.println(pass);

			System.out.println("Check correct pw: " + login(username, password));
			System.out.println("Check incorrect pw: " + login(username, "aaaaa"));
		}
	}

	public boolean login(String username, String password) {
		PasswordRecord pass = logins.get(username);
		if(pass == null) {
			return false;
		}
		return PasswordRecord.checkLogin(password, pass.getHash(), pass.getSalt());
	}

	public void addAll(String[] usernames, String[] passwords) {
		if(usernames.length != passwords.length) {
			System.out.println("Username and password list lengths do not match.");
		}

		for(int i=0; i< usernames.length; i++) {
			String username = usernames[i];
			String password = passwords[i];
			addLogin(username, password);
		}
	}

	public void addLogin(String username, String password) {
		logins.put(username, new PasswordRecord(password));
	}
}