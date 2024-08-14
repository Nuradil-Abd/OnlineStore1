import java.util.Scanner;

public class User {

    private String firstName, lastName, email, password;
    private Product products;

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Product products) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.products = products;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = validateString(firstName, "Name cannot be empty");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = validateString(lastName, "Surname cannot be empty");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Scanner scan = new Scanner(System.in);
        while (!isValidEmail(email)) {
            System.out.print("\n(must be at least 14 characters and end with @gmail.com)\nWrite email: ");
            email = scan.nextLine();
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        while (!isValidPassword(password)) {
            System.out.println("\nPassword must contain at least one capital letter, one number, and at least 4 characters!");
            System.out.print("Write password: ");
            password = new Scanner(System.in).nextLine();
        }
        this.password = password;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "     " +
                "\nfirst name: " + firstName +
                "\nlast name: " + lastName +
                "\nemail: " + email +
                "\npassword: " + password +
                "\nproducts: " + products +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~";
    }

    private String validateString(String value, String errorMessage) {
        Scanner scan = new Scanner(System.in);
        while (value.isEmpty()) {
            System.out.print(errorMessage + "\nWrite value: ");
            value = scan.nextLine();
        }
        return value;
    }

    private boolean isValidEmail(String email) {
        return email.length() >= 14 && email.endsWith("@gmail.com");
    }


    public boolean isValidPassword(String password) {
        boolean size = false;
        boolean hasDigit = false;
        boolean hasUppercase = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (password.length() >= 4){
                size = true;
            }
            if (hasDigit && hasUppercase && size) {
                return true;
            }
        }
        return false;
    }

}
