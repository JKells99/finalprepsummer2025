package user;

/**
 * Represents a Chief user role in the system.
 * Extends the base User class and sets the role to "Chief".
 */
public class Chief extends User {

    /**
     * Constructs a Chief with the specified username and password.
     * @param username the username of the Chief
     * @param password the password of the Chief
     */
    public Chief(String username, String password) {
        super(username, password, "Chief");
    }

    /**
     * Default constructor for Chief.
     */
    public Chief() {
        super();
    }

}
