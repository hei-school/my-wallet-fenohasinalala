const { v4: uuidv4 } = require('uuid');
const { TransactionHistory } = require('./TransactionHistory');
const { MoneyUtils } = require('./MoneyUtils');

class Wallet {
    static MAX_TRANSACTION_AMOUNT = 200000000;

    constructor() {
        this.id = uuidv4();
        this.balance = 0.0;
        this.transactionHistory = new TransactionHistory();
    }

    deposit(amount) {
        if (MoneyUtils.isAmountValid(amount)) {
            this.balance += amount;
            this.transactionHistory.addTransaction(amount, 'DEPOSIT', 'me');
        }
    }

    withdraw(amount) {
        if (MoneyUtils.isAmountValid(amount, this.balance, Wallet.MAX_TRANSACTION_AMOUNT)) {
            this.balance -= amount;
            this.transactionHistory.addTransaction(amount, 'WITHDRAW', '');
            return true;
        }
        return false;
    }

    transfer(amount, destinationUser) {
        if (MoneyUtils.isAmountValid(amount, this.balance, Wallet.MAX_TRANSACTION_AMOUNT)) {
            this.balance -= amount;
            destinationUser.wallet.deposit(amount);
            this.transactionHistory.addTransaction(amount, 'TRANSFER', destinationUser.username);
            return true;
        }
        return false;
    }
}

module.exports = { Wallet };
