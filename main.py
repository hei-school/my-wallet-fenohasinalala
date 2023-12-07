from model import User, Wallet, Transaction, TransactionHistory
from service import AuthenticationService, WalletService
from utils import MoneyUtils, TimeUtils


def display_home_menu():
    print("\n--- HOME ---")
    print("1. Sign Up")
    print("2. Sign In")
    print("3. Exit")
    print("\nEnter your choice: ", end="")


def display_wallet_menu():
    print("\n1. Account - View Personal Information")
    print("2. Account - Edit Personal Information")
    print("3. Account - Change Password")
    print("4. Check Balance")
    print("5. Add Funds")
    print("6. Withdraw Money")
    print("7. Peer-to-peer Transfers")
    print("8. Transaction History")
    print("9. Logout")
    print("\nEnter your choice: ", end="")


def main():
    account_list = [User("mockUser1", "mockPass1", "mockUser1", "", "mockUser1@mai.com")]

    auth_manager = AuthenticationService(account_list)

    while True:
        display_home_menu()
        choice = int(input())

        if choice == 1:
            auth_manager.sign_up_form()
        elif choice == 2:
            current_user = auth_manager.sign_in_form()
            if current_user:
                main_wallet_menu(current_user, account_list)
        elif choice == 3:
            print("Exiting application. Goodbye!")
            break
        else:
            print("Invalid choice. Please try again.")


def main_wallet_menu(current_user, account_list):
    while True:
        display_wallet_menu()
        choice = int(input())

        if choice == 1:
            current_user.view_personal_information()
        elif choice == 2:
            current_user.edit_personal_information()
        elif choice == 3:
            current_user.change_password()
        elif choice == 4:
            WalletService.check_balance(current_user)
        elif choice == 5:
            WalletService.add_funds(current_user)
        elif choice == 6:
            WalletService.withdraw_money(current_user)
        elif choice == 7:
            WalletService.peer_to_peer_transfers(current_user, account_list)
        elif choice == 8:
            WalletService.transaction_history(current_user)
        elif choice == 9:
            return  # Return to the main menu
        else:
            print("Invalid choice. Please try again.")


if __name__ == "__main__":
    main()
