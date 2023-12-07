from model import User, Wallet
from utils import MoneyUtils


class AuthenticationService:
    def __init__(self, account_list):
        self.account_list = account_list

    def sign_up_form(self):
        last_name = input("Enter Last Name: ")
        first_name = input("Enter First Name: ")
        email = input("Enter Email: ")
        username = input("Enter Username: ")
        password = input("Enter Password: ")

        self.sign_up(last_name, first_name, email, username, password)

    def sign_in_form(self):
        username = input("Enter Username: ")
        password = input("Enter Password: ")

        return self.sign_in(username, password)

    def sign_up(self, last_name, first_name, email, username, password):
        for user in self.account_list:
            if user.username == username:
                print("Username is already taken. Please choose a different username.")
                return False

        new_user = User(username, password, first_name, last_name, email)
        self.account_list.append(new_user)

        print("User created successfully! You can sign in now!")
        return True

    def sign_in(self, username, password):
        for user in self.account_list:
            if user.username == username and user.password == password:
                print("Sign In successful! You can manage your wallet now!")
                return user

        print("Invalid credentials. Sign In failed.")
        return None


class WalletService:
    @staticmethod
    def check_balance(user):
        print("Wallet Balance:", user.wallet.balance, MoneyUtils.MONEY_UNIT)

    @staticmethod
    def add_funds(user):
        amount = float(input("Enter the amount (in" + MoneyUtils.MONEY_UNIT + ") to add: "))

        if MoneyUtils.is_amount_valid(amount):
            user.wallet.deposit(amount)
            print("Funds added successfully. New balance:", user.wallet.balance, MoneyUtils.MONEY_UNIT)

    @staticmethod
    def withdraw_money(user):
        amount = float(input("Enter the amount (in" + MoneyUtils.MONEY_UNIT + ") to withdraw: "))

        if MoneyUtils.is_amount_valid_with_max_transaction(amount,user.wallet.balance,Wallet.MAX_TRANSACTION_AMOUNT):
            user.wallet.withdraw(amount)
            print("Withdrawal successful. New balance:", user.wallet.balance, MoneyUtils.MONEY_UNIT)

    @staticmethod
    def peer_to_peer_transfers(user, account_list):
        destination_username = input("Enter the destination username: ")
        destination_user = find_user_by_username(destination_username, account_list)

        if destination_user:
            amount = float(input("Enter the amount (in" + MoneyUtils.MONEY_UNIT + ") to transfer: "))

            if MoneyUtils.is_amount_valid_with_max_transaction(amount,user.wallet.balance,Wallet.MAX_TRANSACTION_AMOUNT):
                user.wallet.withdraw_to_user(amount, destination_user)
                print(amount, MoneyUtils.MONEY_UNIT, "send to:", destination_username)
                print("Transfer successful. New balance:", user.wallet.balance, MoneyUtils.MONEY_UNIT)
        else:
            print("User not found with the provided username.")

    @staticmethod
    def transaction_history(user):
        transactions = user.wallet.transaction_history.transactions

        print("Transaction History:")
        for transaction in transactions:
            print("Transaction ID:", transaction.id)
            print("Amount:", transaction.amount, MoneyUtils.MONEY_UNIT)
            print("Type:", transaction.type)
            print("Destination:", transaction.destination)
            print("Transaction Date:", transaction.transaction_date)
            print("------")


def find_user_by_username(username, account_list):
    for user in account_list:
        if user.username == username:
            return user
    return None
