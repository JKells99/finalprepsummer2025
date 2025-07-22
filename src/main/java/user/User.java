package user;

/**
 * Represents a user in the system.
 * Stores user information such as id, username, password, and role.
 */
public class User {

    /** The unique identifier for the user. */
    private int id;
    /** The username of the user. */
    private String username;
    /** The password of the user (hashed). */
    private String password;
    /** The role of the user (e.g., Chief, Commander). */
    private String role;

    /**
     * Constructs a User with the specified username, password, and role.
     * @param username the username of the user
     * @param password the password of the user
     * @param role the role of the user
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Gets the user's unique identifier.
     * @return the user's id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     * @param id the user's id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role the role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
