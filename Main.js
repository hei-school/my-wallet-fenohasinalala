const { AuthenticationService } = require('./AuthenticationService');
const { AccountService } = require('./AccountService');
const { WalletService } = require('./WalletService');
const { User } = require('./User');
const { MoneyUtils } = require('./MoneyUtils');

const users = [new User("mockUser1", "mockPass1", "mockUser1", "", "mockUser1@mai.com")];
const authManager = new AuthenticationService(users);

while (true) {
    console.log("--- HOME ---");
    console.log("1. Sign Up");
    console.log("2. Sign In");
    console.log("3. Exit");

    const choice = parseInt(prompt("Enter your choice: "));

    if (choice === 1) {
        authManager.signUpForm();
    } else if (choice === 2) {
        const currentUser = authManager.signInForm();
        if (currentUser) {
            while (true) {
                console.log(" ");
                console.log("1. Account - View Personal Information");
                console.log("2. Account - Edit Personal Information");
                console.log("3. Account - Change Password");
                console.log("4. Check Balance");
                console.log("5. Add Funds");
                console.log("6. Withdraw Money");
                console.log("7. Peer-to-peer Transfers");
                console.log("8. Transaction History");
                console.log("9. Logout");

                const innerChoice = parseInt(prompt("Enter your choice: "));

                if (innerChoice === 1) {
                    AccountService.viewPersonalInformation(currentUser);
                } else if (innerChoice === 2) {
                    AccountService.editPersonalInformation(currentUser);
                } else if (innerChoice === 3) {
                    AccountService.changePassword(currentUser);
                } else if (innerChoice === 4) {
                    WalletService.checkBalance(currentUser);
                } else if (innerChoice === 5) {
                    WalletService.addFunds(currentUser);
                } else if (innerChoice === 6) {
                    WalletService.withdrawMoney(currentUser);
                } else if (innerChoice === 7) {
                    WalletService.peerToPeerTransfers(currentUser, users);
                } else if (innerChoice === 8) {
                    WalletService.transactionHistory(currentUser);
                } else if (innerChoice === 9) {
                    break;
                } else {
                    console.log("Invalid choice. Please try again.");
                }
            }
        }
    } else if (choice === 3) {
        console.log("Exiting application. Goodbye!");
        break;
    } else {
        console.log("Invalid choice. Please try again.");
    }
}
