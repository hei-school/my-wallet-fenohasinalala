using System;

namespace WalletApp
{
    public class Transaction
    {
        public string Id { get; }
        public double Amount { get; }
        public TransactionType Type { get; }
        public string Destination { get; }
        public DateTime TransactionDate { get; }

        public enum TransactionType
        {
            Deposit,
            Withdraw,
            Transfer
        }

        public Transaction(double amount, TransactionType type, string destination)
        {
            Id = Guid.NewGuid().ToString();
            Amount = amount;
            Type = type;
            Destination = destination;
            TransactionDate = DateTime.Now;
        }
    }
}
