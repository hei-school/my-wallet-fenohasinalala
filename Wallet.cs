using System;
using System.Linq;

namespace WalletApp
{
    public class Wallet
    {
        public string Id { get; }
        public double Balance { get; private set; }
        public static double MaxTransactionAmount = 200_000_000;
        public User Account { get; set; }
        public TransactionHistory TransactionHistory { get; }

        public Wallet()
        {
            Id = Guid.NewGuid().ToString();
            Balance = 0.0;
            TransactionHistory = new TransactionHistory();
        }

        public void Deposit(double amount)
        {
            if (MoneyUtils.IsAmountValid(amount))
            {
                Balance += amount;
                TransactionHistory.AddTransaction(amount, Transaction.TransactionType.Deposit, "me");
            }
        }

        public bool Withdraw(double amount)
        {
            if (MoneyUtils.IsAmountValid(amount, Balance, MaxTransactionAmount))
            {
                Balance -= amount;
                TransactionHistory.AddTransaction(amount, Transaction.TransactionType.Withdraw, "");
                return true;
            }
            return false;
        }

        public bool Withdraw(double amount, User destinationUser)
        {
            if (MoneyUtils.IsAmountValid(amount, Balance, MaxTransactionAmount))
            {
                Balance -= amount;
                destinationUser.Wallet.Deposit(amount);
                TransactionHistory.AddTransaction(amount, Transaction.TransactionType.Transfer, destinationUser.Username);
                return true;
            }
            return false;
        }
    }
}
