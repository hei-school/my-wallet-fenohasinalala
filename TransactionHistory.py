import uuid

from Transaction import Transaction

class TransactionHistory:
    def __init__(self):
        self.id = str(uuid.uuid4())
        self.transactions = []

    def add_transaction(self, amount, transaction_type, destination):
        transaction = Transaction(amount, transaction_type, destination)
        self.transactions.append(transaction)

