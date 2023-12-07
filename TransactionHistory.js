const { Transaction } = require('./Transaction');
const { uuid } = require('uuidv4');

class TransactionHistory {
    constructor() {
        this.id = uuid();
        this.transactions = [];
    }

    addTransaction(amount, transactionType, destination) {
        const transaction = new Transaction(amount, transactionType, destination);
        this.transactions.push(transaction);
    }
}

module.exports = { TransactionHistory };
