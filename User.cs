using System;

namespace WalletApp
{
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
            PhoneNumber = "Not specified";
            CreationDatetime = DateTime.Now.ToString();
            Wallet = new Wallet();
        }
    }
}
