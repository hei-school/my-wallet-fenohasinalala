from datetime import datetime


class MoneyUtils:
    MONEY_UNIT = " MGA"
    @staticmethod
    def is_amount_valid(amount):
        if amount < 0:
            print("Amount must be > 0" + MoneyUtils.MONEY_UNIT)
            return False
        return True

    @staticmethod
    def is_amount_valid_with_balance(amount, balance):
        if not MoneyUtils.is_amount_valid(amount):
            return False
        if amount > balance:
            print("Amount must be <= Balance:", balance, MoneyUtils.MONEY_UNIT)
            return False
        return True

    @staticmethod
    def is_amount_valid_with_max_transaction(amount, balance, max_transaction):
        if not MoneyUtils.is_amount_valid_with_balance(amount, balance):
            return False
        if amount > max_transaction:
            print("Amount must be <= Max Transaction:", max_transaction, MoneyUtils.MONEY_UNIT)
            return False
        return True


class TimeUtils:
    @staticmethod
    def current_datetime():
        return datetime.now().strftime("%Y-%m-%d %H:%M:%S")
