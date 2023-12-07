import uuid
from Wallet import Wallet
from TransactionHistory import TransactionHistory
from datetime import datetime


class User:
    def __init__(self, username, password, first_name, last_name, email):
        self.id = str(uuid.uuid4())
        self.ref = str(uuid.uuid4())
        self.username = username
        self.password = password
        self.first_name = first_name
        self.last_name = last_name
        self.email = email
        self.phone_number = "Not specified"
        self.creation_datetime = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
        self.wallet = Wallet()

