import user.Chief;
import user.Commander;
import user.UserService;

import java.io.IOException;

public class UserMain {

    public static void main(String[] args) throws IOException {
        UserService userService = new UserService();
        Chief chief = new Chief("chiefUsername2", "chiefPassword");
        Commander commander = new Commander("commanderUsername2", "commanderPassword");
//        userService.createNewUser(commander);
//        userService.createNewUser(chief);

        userService.printAllUsersInSystemReport();
    }
}
