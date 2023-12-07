using System;
using System.Collections.Generic;

namespace WalletApp
{
    public class AuthenticationService
    {
        private readonly List<User> accountList;

        public AuthenticationService(List<User> accountList)
        {
            this.accountList = accountList;
        }

        public bool SignUpForm()
        {
            Console.Write("Enter Last Name: ");
            string lastName = Console.ReadLine();

            Console.Write("Enter First Name: ");
            string firstName = Console.ReadLine();

            Console.Write("Enter Email: ");
            string email = Console.ReadLine();

            Console.Write("Enter Username: ");
            string username = Console.ReadLine();

            Console.Write("Enter Password: ");
            string password = Console.ReadLine();

            return SignUp(lastName, firstName, email, username, password);
        }

        public User SignInForm()
        {
            Console.Write("Enter Username: ");
            string username = Console.ReadLine();

            Console.Write("Enter Password: ");
            string password = Console.ReadLine();

            return SignIn(username, password);
        }

        public bool SignUp(string lastName, string firstName, string email, string username, string password)
        {
            if (accountList.Exists(user => user.Username == username))
            {
                Console.WriteLine("Username is already taken. Please choose a different username.");
                return false;
            }

            User newUser = new User(username, password, firstName, lastName, email);
            accountList.Add(newUser);

            Console.WriteLine("User created successfully! You can sign in now!");
            return true;
        }

        public User SignIn(string username, string password)
        {
            User user = accountList.Find(u => u.Username == username && u.Password == password);

            if (user != null)
            {
                Console.WriteLine("Sign In successful! You can manage your wallet now!");
                return user;
            }

            Console.WriteLine("Invalid credentials. Sign In failed.");
            return null;
        }
    }
}
