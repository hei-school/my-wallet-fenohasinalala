const { v4: uuidv4 } = require('uuid');

class Transaction {
    constructor(amount, transactionType, destination) {
        this.id = uuidv4();
        this.amount = amount;
        this.type = transactionType;
        this.destination = destination;
        this.transactionDate = new Date().toISOString();
    }
}

module.exports = { Transaction };
