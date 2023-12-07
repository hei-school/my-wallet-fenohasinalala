import uuid
from datetime import datetime
from utils import MoneyUtils, TimeUtils


class Transaction:
    class TransactionType:
        DEPOSIT = "DEPOSIT"
        WITHDRAW = "WITHDRAW"
        TRANSFER = "TRANSFER"

    def __init__(self, amount, type, destination):
        self.id = str(uuid.uuid4())
        self.amount = amount
        self.type = type
        self.destination = destination
        self.transaction_date = TimeUtils.current_datetime()


class TransactionHistory:
    def __init__(self):
        self.id = str(uuid.uuid4())
        self.transactions = []

    def add_transaction(self, amount, type, destination):
        transaction = Transaction(amount, type, destination)
        self.transactions.append(transaction)


class Wallet:
    MAX_TRANSACTION_AMOUNT = 200000000

    def __init__(self):
        self.id = str(uuid.uuid4())
        self.balance = 0.0
        self.transaction_history = TransactionHistory()

    def deposit(self, amount):
        if MoneyUtils.is_amount_valid(amount):
            self.balance += amount
            self.transaction_history.add_transaction(amount, Transaction.TransactionType.DEPOSIT, "me")

    def withdraw(self, amount):
        if MoneyUtils.is_amount_valid_with_max_transaction(amount, self.balance, self.MAX_TRANSACTION_AMOUNT):
            self.balance -= amount
            self.transaction_history.add_transaction(amount, Transaction.TransactionType.WITHDRAW, "")

    def withdraw_to_user(self, amount, destination_user):
        if MoneyUtils.is_amount_valid_with_max_transaction(amount, self.balance, self.MAX_TRANSACTION_AMOUNT):
            self.balance -= amount
            destination_user.wallet.deposit(amount)
            self.transaction_history.add_transaction(
                amount, Transaction.TransactionType.TRANSFER, destination_user.username
            )


class User:
    def __init__(self, username, password, first_name, last_name, email):
        self.id = str(uuid.uuid4())
        self.ref = str(uuid.uuid4())
        self.username = username
        self.password = password
        self.first_name = first_name
        self.last_name = last_name
        self.email = email
        self.creation_datetime = TimeUtils.current_datetime()
        self.wallet = Wallet()
        self.phone_number = "Not specified"

    def __str__(self):
        return f"User{{ref='{self.ref}', username='{self.username}', first_name='{self.first_name}', " \
               f"last_name='{self.last_name}', email='{self.email}', phone_number='{self.phone_number}'}}"

    def view_personal_information(self):
        print(self)

    def edit_personal_information(self):
        print("Editing personal information")

    def change_password(self):
        print("Changing password")
