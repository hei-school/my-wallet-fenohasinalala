using System;
using System.Collections.Generic;

namespace WalletApp
{
    public class TransactionHistory
    {
        public string Id { get; }
        public List<Transaction> Transactions { get; }

        public TransactionHistory()
        {
            Id = Guid.NewGuid().ToString();
            Transactions = new List<Transaction>();
        }

        public void AddTransaction(double amount, Transaction.TransactionType type, string destination)
        {
            var transaction = new Transaction(amount, type, destination);
            Transactions.Add(transaction);
        }
    }
}
