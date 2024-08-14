import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Product product = new Product();

        DbUsers dbUsers = new DbUsers();
        dbUsers.addUser(new User("Nuradil", "Abdynazarov", "Nuradil@gmail.com", "Nura123", product));
        dbUsers.addUser(new User("Atai", "Aftandilov", "Atai@gmail.com", "Atai333", product));
        dbUsers.addUser(new User("Kalyk", "Kanybekov", "Kalyk@gmail.com", "Kalyk666", product));

        System.out.println("Welcome!");

        while (true) {
            System.out.println("""
                    
                    Write command
                    1. Register
                    2. Login
                    3. Change password
                    4. Database info
                    5. Exit""");

            String move = scan.nextLine();

            switch (move) {
                case "1":
                    dbUsers.addUser(register(dbUsers));
                    break;
                case "2":
                    if (!login(dbUsers)) {
                        System.out.println("Try again later!");
                    }
                    break;
                case "3":
                    changePassword(dbUsers);
                    break;
                case "4":
                    System.out.println(dbUsers);
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static User register(DbUsers dbUsers) {
        User newUser = new User();
        Scanner scan = new Scanner(System.in);

        System.out.print("Write name: ");
        newUser.setFirstName(scan.nextLine());

        System.out.print("Write surname: ");
        newUser.setLastName(scan.nextLine());

        do {
            System.out.print("Write email: ");
            newUser.setEmail(scan.nextLine());
        } while (checkBd(dbUsers, newUser));

        System.out.print("Write password: ");
        newUser.setPassword(scan.nextLine());

        System.out.println("\nYou have successfully registered!");
        return newUser;
    }

    private static boolean checkBd(DbUsers dbUsers, User user) {
        for (User dbUser : dbUsers.getDbUsers()) {
            if (user.getEmail().equals(dbUser.getEmail())) {
                System.out.println("An account with this email already exists.");
                return true;
            }
        }
        return false;
    }

    private static boolean login(DbUsers dbUsers) {
        Scanner scanner = new Scanner(System.in);
        boolean checkEmail = false;
        boolean checkPassword = false;

        for (int i = 0; i < 3; i++) {
            System.out.println();
            System.out.print("Write email: ");
            String email = scanner.nextLine();
            System.out.print("Write password: ");
            String password = scanner.nextLine();

            for (User dbUser : dbUsers.getDbUsers()) {
                if (email.equals(dbUser.getEmail())) {
                    checkEmail = true;
                }
                if (password.equals(dbUser.getPassword())) {
                    checkPassword = true;
                }
            }

            if (checkEmail && checkPassword) {
                System.out.println("You have successfully logged in! Welcome");
                return true;
            } else {
                System.err.println("Wrong login or password");
            }
        }
        return false;
    }

    private static void changePassword(DbUsers dbUsers) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write email: ");
        String email = scanner.nextLine();
        System.out.print("Write current password: ");
        String currentPassword = scanner.nextLine();

        // Поиск пользователя по email и паролю
        User userToUpdate = null;
        for (User dbUser : dbUsers.getDbUsers()) {
            if (email.equals(dbUser.getEmail()) && currentPassword.equals(dbUser.getPassword())) {
                userToUpdate = dbUser;
                break;
            }
        }

        if (userToUpdate == null) {
            System.out.println("Email or current password is incorrect.");
            return;
        }

        // Если пользователь найден, запрашиваем новый пароль и его подтверждение
        while (true) {
            System.out.print("Write new password: ");
            String newPassword = scanner.nextLine();
            System.out.print("Confirm new password: ");
            String confirmPassword = scanner.nextLine();

            if (newPassword.equals(confirmPassword)) {
                if (userToUpdate.isValidPassword(newPassword)) { // Проверка на валидность нового пароля
                    userToUpdate.setPassword(newPassword);
                    System.out.println("Password has been updated successfully.");
                    break;
                } else {
                    System.out.println("New password is invalid. It must contain at least one capital letter, one number, and be at least 4 characters long.");
                }
            } else {
                System.out.println("Passwords do not match. Please try again.");
            }
        }
    }
}
