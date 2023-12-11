using System;
using System.Collections.Generic;

namespace WalletApp
{
    public class MoneyUtils
    {
        public static bool IsAmountValid(double amount, double balance, double maxTransaction)
        {
            if (!IsAmountValid(amount, balance))
            {
                return false;
            }

            if (amount > maxTransaction)
            {
                Console.WriteLine($"Amount must be < maxTransaction: {maxTransaction} MGA");
                return false;
            }

            return true;
        }

        public static bool IsAmountValid(double amount, double balance)
        {
            if (!IsAmountValid(amount))
            {
                return false;
            }

            if (amount > balance)
            {
                Console.WriteLine($"Amount must be < Balance: {balance} MGA");
                return false;
            }

            return true;
        }

        public static bool IsAmountValid(double amount)
        {
            if (amount < 0)
            {
                Console.WriteLine($"Amount must be > 0 MGA");
                return false;
            }

            return true;
        }

        public const string MoneyUnit = " MGA";
    }

    public class TimeUtils
    {
        public static string CurrentDatetime()
        {
            return DateTime.Now.ToString();
        }
    }

    public class Transaction
    {
        public enum TransactionType
        {
            DEPOSIT,
            WITHDRAW,
            TRANSFER
        }

        public string Id { get; }
        public double Amount { get; }
        public TransactionType Type { get; }
        public string Destination { get; }
        public string TransactionDate { get; }

        public Transaction(double amount, TransactionType type, string destination)
        {
            Id = Guid.NewGuid().ToString();
            Amount = amount;
            Type = type;
            Destination = destination;
            TransactionDate = TimeUtils.CurrentDatetime();
        }
    }

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

    public class Wallet
    {
        public const double MaxTransactionAmount = 200_000_000;

        public string Id { get; }
        public double Balance { get; private set; }
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
                TransactionHistory.AddTransaction(amount, Transaction.TransactionType.DEPOSIT, "me");
            }
        }

        public bool Withdraw(double amount)
        {
            if (MoneyUtils.IsAmountValid(amount, Balance, MaxTransactionAmount))
            {
                Balance -= amount;
                TransactionHistory.AddTransaction(amount, Transaction.TransactionType.WITHDRAW, "");
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
                TransactionHistory.AddTransaction(amount, Transaction.TransactionType.TRANSFER, destinationUser.Username);
                return true;
            }

            return false;
        }
    }

    public class User
    {
        public string Id { get; }
        public string Ref { get; }
        public string Username { get; set; }
        public string Password { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public string PhoneNumber { get; set; }
        public string CreationDatetime { get; }
        public Wallet Wallet { get; }

        public User(string username, string password, string firstName, string lastName, string email)
        {
            Id = Guid.NewGuid().ToString();
            Ref = Guid.NewGuid().ToString();
            Username = username;
            Password = password;
            FirstName = firstName;
            LastName = lastName;
            Email = email;
            CreationDatetime = TimeUtils.CurrentDatetime();
            Wallet = new Wallet();
            PhoneNumber = "Not specified";
        }

        public override string ToString()
        {
            return $"User{{Ref='{Ref}', Username='{Username}', FirstName='{FirstName}', LastName='{LastName}', Email='{Email}', PhoneNumber='{PhoneNumber}'}}";
        }
    }

    public class AccountService
    {
        public static User FindUserByUsername(string username, List<User> accountList)
        {
            foreach (var user in accountList)
            {
                if (user.Username.Equals(username, StringComparison.OrdinalIgnoreCase))
                {
                    return user;
                }
            }

            return null;
        }

        public static void ViewPersonalInformation(User user)
        {
            Console.WriteLine(" ");
            Console.WriteLine("Personal Information:");
            Console.WriteLine($"Username: {user.Username}");
            Console.WriteLine($"First Name: {user.FirstName}");
            Console.WriteLine($"Last Name: {user.LastName}");
            Console.WriteLine($"Email: {user.Email}");
            Console.WriteLine($"Phone Number: {user.PhoneNumber}");
        }

        public static void EditPersonalInformation(User user)
        {
            Console.Write("Enter new Last Name: ");
            user.LastName = Console.ReadLine();

            Console.Write("Enter new First Name: ");
            user.FirstName = Console.ReadLine();

            Console.Write("Enter new Email: ");
            user.Email = Console.ReadLine();

            Console.Write("Enter new PhoneNumber: ");
            user.PhoneNumber = Console.ReadLine();

            ViewPersonalInformation(user);
        }

        public static void ChangePassword(User user)
        {
            Console.Write("Enter current password: ");
            var currentPassword = Console.ReadLine();

            Console.Write("Enter new password: ");
            var newPassword = Console.ReadLine();

            Console.Write("Repeat new password: ");
            var repeatedPassword = Console.ReadLine();

            if (currentPassword == user.Password && newPassword == repeatedPassword)
            {
                user.Password = newPassword;
                Console.WriteLine("Password updated");
            }
            else
            {
                Console.WriteLine("Wrong password");
            }
        }
    }

    public class AuthenticationService
    {
        private readonly List<User> accountList;

        public AuthenticationService(List<User> accountList)
        {
            this.accountList = accountList;
        }

        public bool SignUp(string lastName, string firstName, string email, string username, string password)
        {
            foreach (var user in accountList)
            {
                if (user.Username.Equals(username, StringComparison.OrdinalIgnoreCase))
                {
                    Console.WriteLine("Username is already taken. Please choose a different username.");
                    return false;
                }
            }

            var newUser = new User(username, password, firstName, lastName, email);
            accountList.Add(newUser);

            Console.WriteLine("User created successfully! You can sign in now!");
            return true;
        }

        public User SignIn(string username, string password)
        {
            foreach (var user in accountList)
            {
                if (user.Username.Equals(username, StringComparison.OrdinalIgnoreCase) && user.Password == password)
                {
                    Console.WriteLine("Sign In successful! You can manage your wallet now!");
                    return user;
                }
            }

            Console.WriteLine("Invalid credentials. Sign In failed.");
            return null;
        }

        public bool SignUpForm()
        {
            Console.Write("Enter Last Name: ");
            var lastName = Console.ReadLine();

            Console.Write("Enter First Name: ");
            var firstName = Console.ReadLine();

            Console.Write("Enter Email: ");
            var email = Console.ReadLine();

            Console.Write("Enter Username: ");
            var username = Console.ReadLine();

            Console.Write("Enter Password: ");
            var password = Console.ReadLine();

            return SignUp(lastName, firstName, email, username, password);
        }

        public User SignInForm()
        {
            Console.Write("Enter Username: ");
            var username = Console.ReadLine();

            Console.Write("Enter Password: ");
            var password = Console.ReadLine();

            return SignIn(username, password);
        }
    }

    public class WalletService
    {
        public static void CheckBalance(User user)
        {
            Console.WriteLine($"Wallet Balance: {user.Wallet.Balance} {MoneyUtils.MoneyUnit}");
        }

        public static void AddFunds(User user)
        {
            Console.Write($"Enter the amount (in {MoneyUtils.MoneyUnit}) to add: ");
            if (double.TryParse(Console.ReadLine(), out var amount))
            {
                if (MoneyUtils.IsAmountValid(amount))
                {
                    user.Wallet.Deposit(amount);
                    Console.WriteLine($"Funds added successfully. New balance: {user.Wallet.Balance} {MoneyUtils.MoneyUnit}");
                }
            }
            else
            {
                Console.WriteLine("Invalid input. Please enter a valid number.");
            }
        }

        public static void WithdrawMoney(User user)
        {
            Console.Write($"Enter the amount (in {MoneyUtils.MoneyUnit}) to withdraw: ");
            if (double.TryParse(Console.ReadLine(), out var amount))
            {
                if (user.Wallet.Withdraw(amount))
                {
                    Console.WriteLine($"Withdrawal successful. New balance: {user.Wallet.Balance} {MoneyUtils.MoneyUnit}");
                }
                else
                {
                    Console.WriteLine("Withdrawal failed. Please check your balance and amount.");
                }
            }
            else
            {
                Console.WriteLine("Invalid input. Please enter a valid number.");
            }
        }

        public static void PeerToPeerTransfers(User user, List<User> users)
        {
            Console.Write("Enter the destination username: ");
            var destinationUsername = Console.ReadLine();

            var destinationUser = AccountService.FindUserByUsername(destinationUsername, users);

            if (destinationUser != null)
            {
                Console.Write($"Enter the amount (in {MoneyUtils.MoneyUnit}) to transfer: ");
                if (double.TryParse(Console.ReadLine(), out var amount))
                {
                    if (user.Wallet.Withdraw(amount, destinationUser))
                    {
                        Console.WriteLine($"{amount} {MoneyUtils.MoneyUnit} sent to: {destinationUsername}");
                        Console.WriteLine($"Transfer successful. New balance: {user.Wallet.Balance} {MoneyUtils.MoneyUnit}");
                    }
                    else
                    {
                        Console.WriteLine("Transfer failed. Please check your balance and amount.");
                    }
                }
                else
                {
                    Console.WriteLine("Invalid input. Please enter a valid number.");
                }
            }
            else
            {
                Console.WriteLine("User not found with the provided username.");
            }
        }

        public static void TransactionHistory(User user)
        {
            var transactions = user.Wallet.TransactionHistory.Transactions;

            Console.WriteLine("Transaction History:");
            foreach (var transaction in transactions)
            {
                Console.WriteLine($"Transaction ID: {transaction.Id}");
                Console.WriteLine($"Amount: {transaction.Amount} {MoneyUtils.MoneyUnit}");
                Console.WriteLine($"Type: {transaction.Type}");
                Console.WriteLine($"Destination: {transaction.Destination}");
                Console.WriteLine($"Transaction Date: {transaction.TransactionDate}");
                Console.WriteLine("------");
            }
        }
    }

    public class MainClass
    {
        private static readonly List<User> AccountList = new List<User>();
        private static readonly AuthenticationService AuthManager = new AuthenticationService(AccountList);

        public static void Main(string[] args)
        {
            AccountList.Add(new User("mockUser1", "mockPass1", "mockUser1", "", "mockUser1@mai.com"));

            while (true)
            {
                DisplayHomeMenu();
                if (int.TryParse(Console.ReadLine(), out var choice))
                {
                    switch (choice)
                    {
                        case 1:
                            AuthManager.SignUpForm();
                            break;
                        case 2:
                            var currentUser = AuthManager.SignInForm();
                            if (currentUser != null)
                            {
                                MainWalletMenu(currentUser);
                            }
                            break;
                        case 3:
                            Console.WriteLine("Exiting application. Goodbye!");
                            Environment.Exit(0);
                            break;
                        default:
                            Console.WriteLine("Invalid choice. Please try again.");
                            break;
                    }
                }
                else
                {
                    Console.WriteLine("Invalid input. Please enter a number.");
                }
            }
        }

        private static void DisplayHomeMenu()
        {
            Console.WriteLine(" ");
            Console.WriteLine("--- HOME ---");
            Console.WriteLine("1. Sign Up");
            Console.WriteLine("2. Sign In");
            Console.WriteLine("3. Exit");
            Console.WriteLine(" ");
            Console.Write("Enter your choice: ");
        }

        private static void MainWalletMenu(User currentUser)
        {
            while (true)
            {
                DisplayWalletMenu();
                if (int.TryParse(Console.ReadLine(), out var choice))
                {
                    switch (choice)
                    {
                        case 1:
                            AccountService.ViewPersonalInformation(currentUser);
                            break;
                        case 2:
                            AccountService.EditPersonalInformation(currentUser);
                            break;
                        case 3:
                            AccountService.ChangePassword(currentUser);
                            break;
                        case 4:
                            WalletService.CheckBalance(currentUser);
                            break;
                        case 5:
                            WalletService.AddFunds(currentUser);
                            break;
                        case 6:
                            WalletService.WithdrawMoney(currentUser);
                            break;
                        case 7:
                            WalletService.PeerToPeerTransfers(currentUser, AccountList);
                            break;
                        case 8:
                            WalletService.TransactionHistory(currentUser);
                            break;
                        case 9:
                            return; // Return to the main menu
                        default:
                            Console.WriteLine("Invalid choice. Please try again.");
                            break;
                    }
                }
                else
                {
                    Console.WriteLine("Invalid input. Please enter a number.");
                }
            }
        }

        private static void DisplayWalletMenu()
        {
            Console.WriteLine(" ");
            Console.WriteLine("1. Account - View Personal Information");
            Console.WriteLine("2. Account - Edit Personal Information");
            Console.WriteLine("3. Account - Change Password");
            Console.WriteLine("4. Check Balance");
            Console.WriteLine("5. Add Funds");
            Console.WriteLine("6. Withdraw Money");
            Console.WriteLine("7. Peer-to-peer Transfers");
            Console.WriteLine("8. Transaction History");
            Console.WriteLine("9. Logout");
            Console.WriteLine(" ");
            Console.Write("Enter your choice: ");
        }
    }
}
