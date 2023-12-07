const { v4: uuidv4 } = require('uuid');
const { Wallet } = require('./Wallet');

class User {
    constructor(username, password, firstName, lastName, email) {
        this.id = uuidv4();
        this.ref = uuidv4();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = "Not specified";
        this.creationDatetime = new Date().toISOString();
        this.wallet = new Wallet();
    }
}

module.exports = { User };
