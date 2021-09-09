//main class aand method that call validate email method
class email {
    public static void main(String[] args) {
        
    }
    //?method to validate the input  email using regex and returns a boolean indicating whether the email is valid or not
    public static boolean validateEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
} 
