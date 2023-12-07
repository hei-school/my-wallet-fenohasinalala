using System;
using System.Collections.Generic;

namespace WalletApp
{
    class Program
    {
        static List<User> accountList = new List<User>();
        static AuthenticationService authManager = new AuthenticationService(accountList);

        static void Main(string[] args)
        {
            accountList.Add(new User("mockUser1", "mockPass1", "mockUser1", "", "mockUser1@mai.com"));

            while (true)
            {
                DisplayHomeMenu();
                int choice = Convert.ToInt32(Console.ReadLine());

                switch (choice)
                {
                    case 1:
                        authManager.SignUpForm();
                        break;
                    case 2:
                        User currentUser = authManager.SignInForm();
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
        }

        static void DisplayHomeMenu()
        {
            Console.WriteLine(" ");
            Console.WriteLine("--- HOME ---");
            Console.WriteLine("1. Sign Up");
            Console.WriteLine("2. Sign In");
            Console.WriteLine("3. Exit");
            Console.WriteLine(" ");
            Console.Write("Enter your choice: ");
        }

        static void MainWalletMenu(User currentUser)
        {
            while (true)
            {
                DisplayWalletMenu();
                int choice = Convert.ToInt32(Console.ReadLine());

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
                        WalletService.CheckBalance
