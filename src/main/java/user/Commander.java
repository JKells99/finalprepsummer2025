package user;

/**
 * Represents a Commander user role in the system.
 * Extends the base User class and sets the role to "Commander".
 */
public class Commander extends User {
    /**
     * Constructs a Commander with the specified username and password.
     * @param username the username of the Commander
     * @param password the password of the Commander
     */
    public Commander(String username, String password) {
        super(username, password, "Commander");
    }

    /**
     * Default constructor for Commander.
     */
    public Commander() {
        super();
    }
}
