class WalletService {
    static checkBalance(user) {
        console.log("Wallet Balance:", user.wallet.balance);
    }

    static addFunds(user) {
        console.log("Enter the amount (in MGA) to add:");
        const amount = parseFloat(prompt());

        if (MoneyUtils.isAmountValid(amount)) {
            user.wallet.deposit(amount);
            console.log("Funds added successfully. New balance:", user.wallet.balance, "MGA");
        }
    }

    static withdrawMoney(user) {
        console.log("Enter the amount (in MGA) to withdraw:");
        const amount = parseFloat(prompt());

        if (user.wallet.withdraw(amount)) {
            console.log("Withdrawal successful. New balance:", user.wallet.balance, "MGA");
        }
    }

    static peerToPeerTransfers(user, users) {
        console.log("Enter the destination username:");
        const destinationUsername = prompt();

        const destinationUser = AccountService.findUserByUsername(destinationUsername, users);

        if (destinationUser) {
            console.log("Enter the amount (in MGA) to transfer:");
            const amount = parseFloat(prompt());

            if (user.wallet.transfer(amount, destinationUser)) {
                console.log(amount, "MGA sent to:", destinationUsername);
                console.log("Transfer successful. New balance:", user.wallet.balance, "MGA");
            }
        } else {
            console.log("User not found with the provided username.");
        }
    }

    static transactionHistory(user) {
        const transactions = user.wallet.transactionHistory.transactions;

        console.log("Transaction History:");
        transactions.forEach(transaction => {
            console.log("Transaction ID:", transaction.id);
            console.log("Amount:", transaction.amount, "MGA");
            console.log("Type:", transaction.type);
            console.log("Destination:", transaction.destination);
            console.log("Transaction Date:", transaction.transactionDate);
            console.log("------");
        });
    }
}

module.exports = { WalletService };
