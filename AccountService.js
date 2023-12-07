class AccountService {
    static findUserByUsername(username, users) {
        return users.find(user => user.username === username);
    }

    static viewPersonalInformation(user) {
        console.log("Personal Information:");
        console.log("Username:", user.username);
        console.log("First Name:", user.firstName);
        console.log("Last Name:", user.lastName);
        console.log("Email:", user.email);
        console.log("Phone Number:", user.phoneNumber);
    }

    static editPersonalInformation(user) {
        console.log("Enter new Last Name:");
        const lastName = prompt();
        console.log("Enter new First Name:");
        const firstName = prompt();
        console.log("Enter new Email:");
        const email = prompt();
        console.log("Enter new PhoneNumber:");
        const phoneNumber = prompt();

        user.lastName = lastName;
        user.firstName = firstName;
        user.email = email;
        user.phoneNumber = phoneNumber;

        AccountService.viewPersonalInformation(user);
    }

    static changePassword(user) {
        console.log("Enter current password:");
        const currentPassword = prompt();
        console.log("Enter new password:");
        const newPassword = prompt();
        console.log("Repeat new password:");
        const repeatedPassword = prompt();

        if (currentPassword === user.password && newPassword === repeatedPassword) {
            user.password = newPassword;
            console.log("Password updated");
        } else {
            console.log("Wrong password");
        }
    }
}

module.exports = { AccountService };
