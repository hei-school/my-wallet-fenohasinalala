using System;
using System.Collections.Generic;

namespace WalletApp
{
    public class WalletService
    {
        public static void CheckBalance(User user)
        {
            Console.WriteLine("Wallet Balance: " + user.Wallet.Balance + MoneyUtils.MoneyUnit);
        }

        public static void AddFunds(User user)
        {
            Console.Write("Enter the amount (in" + MoneyUtils.MoneyUnit + ") to add: ");
            double amount = Convert.ToDouble(Console.ReadLine());

            if (MoneyUtils.IsAmountValid(amount))
            {
                user.Wallet.Deposit(amount);
                Console.WriteLine("Funds added successfully. New balance: " + user.Wallet.Balance + MoneyUtils.MoneyUnit);
            }
        }

        public static void WithdrawMoney(User user)
        {
            Console.Write("Enter the amount (in" + MoneyUtils.MoneyUnit + ") to withdraw: ");
            double amount = Convert.ToDouble(Console.ReadLine());

            if (user.Wallet.Withdraw(amount))
            {
                Console.WriteLine("Withdrawal successful. New balance: " + user.Wallet.Balance + MoneyUtils.MoneyUnit);
            }
        }

        public static void PeerToPeerTransfers(User user, List<User> users)
        {
            Console.Write("Enter the destination username: ");
            string destinationUsername = Console.ReadLine();

            User destinationUser = AccountService.FindUserByUsername(destinationUsername, users);

            if (destinationUser != null)
            {
                Console.Write("Enter the amount (in" + MoneyUtils.MoneyUnit + ") to transfer: ");
                double amount = Convert.ToDouble(Console.ReadLine());

                if (user.Wallet.Withdraw(amount, destinationUser))
                {
                    Console.WriteLine(amount + MoneyUtils.MoneyUnit + " sent to : " + destinationUsername);
                    Console.WriteLine("Transfer successful. New balance: " + user.Wallet.Balance + MoneyUtils.MoneyUnit);
                }
            }
            else
            {
                Console.WriteLine("User not found with the provided username.");
            }
        }

        public static void TransactionHistory(User user)
        {
            List<Transaction> transactions = user.Wallet.TransactionHistory.Transactions;

            Console.WriteLine("Transaction History:");
            foreach (Transaction transaction in transactions)
            {
                Console.WriteLine("Transaction ID: " + transaction.Id);
                Console.WriteLine("Amount: " + transaction.Amount + MoneyUtils.MoneyUnit);
                Console.WriteLine("Type: " + transaction.Type);
                Console.WriteLine("Destination: " + transaction.Destination);
                Console.WriteLine("Transaction Date: " + transaction.TransactionDate);
                Console.WriteLine("------");
            }
        }
    }
}
