import uuid
from TransactionHistory import TransactionHistory
from MoneyUtils import MoneyUtils


class Wallet:
    MAX_TRANSACTION_AMOUNT = 200000000

    def __init__(self):
        self.id = str(uuid.uuid4())
        self.balance = 0.0
        self.transaction_history = TransactionHistory()

    def deposit(self, amount):
        if MoneyUtils.is_amount_valid(amount):
            self.balance += amount
            self.transaction_history.add_transaction(amount, "Deposit", "me")

    def withdraw(self, amount):
        if MoneyUtils.is_amount_valid(amount, self.balance, self.MAX_TRANSACTION_AMOUNT):
            self.balance -= amount
            self.transaction_history.add_transaction(amount, "Withdraw", "")
            return True
        return False

    def transfer(self, amount, destination_user):
        if MoneyUtils.is_amount_valid(amount, self.balance, self.MAX_TRANSACTION_AMOUNT):
            self.balance -= amount
            destination_user.wallet.deposit(amount)
            self.transaction_history.add_transaction(amount, "Transfer", destination_user.username)
            return True
        return False

