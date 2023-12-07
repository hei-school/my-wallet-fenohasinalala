from AccountService import AccountService
from MoneyUtils import MoneyUtils


class WalletService:
    @staticmethod
    def check_balance(user):
        print("Wallet Balance:", user.wallet.balance)

    @staticmethod
    def add_funds(user):
        print("Enter the amount (in MGA) to add:")
        amount = float(input())
        if MoneyUtils.is_amount_valid(amount):
            user.wallet.deposit(amount)
            print("Funds added successfully. New balance:", user.wallet.balance, "MGA")

    @staticmethod
    def withdraw_money(user):
        print("Enter the amount (in MGA) to withdraw:")
        amount = float(input())
        if user.wallet.withdraw(amount):
            print("Withdrawal successful. New balance:", user.wallet.balance, "MGA")

    @staticmethod
    def peer_to_peer_transfers(user, users):
        print("Enter the destination username:")
        destination_username = input()

        destination_user = AccountService.find_user_by_username(destination_username, users)

        if destination_user:
            print("Enter the amount (in MGA) to transfer:")
            amount = float(input())
            if user.wallet.transfer(amount, destination_user):
                print(amount, "MGA sent to:", destination_username)
                print("Transfer successful. New balance:", user.wallet.balance, "MGA")
        else:
            print("User not found with the provided username.")

    @staticmethod
    def transaction_history(user):
        transactions = user.wallet.transaction_history.transactions

        print("Transaction History:")
        for transaction in transactions:
            print("Transaction ID:", transaction.id)
            print("Amount:", transaction.amount, "MGA")
            print("Type:", transaction.type)
            print("Destination:", transaction.destination)
            print("Transaction Date:", transaction.transaction_date)
            print("------")

