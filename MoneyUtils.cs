namespace WalletApp
{
    public static class MoneyUtils
    {
        public const string MoneyUnit = " MGA";

        public static bool IsAmountValid(double amount)
        {
            if (amount < 0)
            {
                Console.WriteLine("Amount must be > 0" + MoneyUnit);
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
                Console.WriteLine("Amount must be <= Balance: " + balance + MoneyUnit);
                return false;
            }
            return true;
        }

        public static bool IsAmountValid(double amount, double balance, double maxTransaction)
        {
            if (!IsAmountValid(amount, balance))
            {
                return false;
            }

            if (amount > maxTransaction)
            {
                Console.WriteLine("Amount must be <= Max Transaction: " + maxTransaction + MoneyUnit);
                return false;
            }
            return true;
        }
    }
}
