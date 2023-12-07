class MoneyUtils:
    MONEY_UNIT = " MGA"

    @staticmethod
    def is_amount_valid(amount, balance=None, max_transaction=None):
        if amount < 0:
            print("Amount must be > 0", MoneyUtils.MONEY_UNIT)
            return False

        if balance is not None and amount > balance:
            print("Amount must be <= Balance:", balance, MoneyUtils.MONEY_UNIT)
            return False

        if max_transaction is not None and amount > max_transaction:
            print("Amount must be <= Max Transaction:", max_transaction, MoneyUtils.MONEY_UNIT)
            return False

        return True

