class MoneyUtils {
    static isAmountValid(amount, balance = null, maxTransaction = null) {
        if (amount < 0) {
            console.log("Amount must be > 0 MGA");
            return false;
        }

        if (balance !== null && amount > balance) {
            console.log("Amount must be <= Balance:", balance, "MGA");
            return false;
        }

        if (maxTransaction !== null && amount > maxTransaction) {
            console.log("Amount must be <= Max Transaction:", maxTransaction, "MGA");
            return false;
        }

        return true;
    }
}

module.exports = { MoneyUtils };
