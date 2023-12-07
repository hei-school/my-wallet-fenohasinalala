import uuid
from enum import Enum
from datetime import datetime


class TransactionType(Enum):
    DEPOSIT = "Deposit"
    WITHDRAW = "Withdraw"
    TRANSFER = "Transfer"


class Transaction:
    def __init__(self, amount, transaction_type, destination):
        self.id = str(uuid.uuid4())
        self.amount = amount
        self.type = transaction_type
        self.destination = destination
        self.transaction_date = datetime.now()

