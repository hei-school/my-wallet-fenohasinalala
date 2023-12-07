using System;
using System.Collections.Generic;

namespace WalletApp
{
    public class AccountService
    {
        public static User FindUserByUsername(string username, List<User> accountList)
        {
            return accountList.Find(user => user.Username == username);
        }

        public static void ViewPersonalInformation(User user)
        {
            Console.WriteLine(" ");
            Console.WriteLine("Personal Information:");
            Console.WriteLine("Username: " + user.Username);
            Console.WriteLine("First Name: " + user.FirstName);
            Console.WriteLine("Last Name: " + user.LastName);
            Console.WriteLine("Email: " + user.Email);
            Console.WriteLine("Phone Number: " + user.PhoneNumber);
        }

        public static void EditPersonalInformation(User user)
        {
            Console.Write("Enter new Last Name: ");
            string lastName = Console.ReadLine();

            Console.Write("Enter new First Name: ");
            string firstName = Console.ReadLine();

            Console.Write("Enter new Email: ");
            string email = Console.ReadLine();

            Console.Write("Enter new PhoneNumber: ");
            string phoneNumber = Console.ReadLine();

            user.FirstName = firstName;
            user.LastName = lastName;
            user.Email = email;
            user.PhoneNumber = phoneNumber;

            ViewPersonalInformation(user);
        }

        public static void ChangePassword(User user)
        {
            Console.Write("Enter current password: ");
            string currentPassword = Console.ReadLine();

            Console.Write("Enter new password: ");
            string newPassword = Console.ReadLine();

            Console.Write("Repeat new password: ");
            string repeatedPassword = Console.ReadLine();

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
}
