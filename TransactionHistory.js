const { Transaction } = require('./Transaction');

class TransactionHistory {
    constructor() {
        this.id = uuidv4();
        this.transactions = [];
    }

    addTransaction(amount, transactionType, destination) {
        const transaction = new Transaction(amount, transactionType, destination);
        this.transactions.push(transaction);
    }
}

module.exports = { TransactionHistory };
