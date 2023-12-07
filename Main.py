from AuthenticationService import AuthenticationService
from AccountService import AccountService
from WalletService import WalletService
from User import User
from MoneyUtils import MoneyUtils

users = [User("mockUser1", "mockPass1", "mockUser1", "", "mockUser1@mai.com")]

auth_manager = AuthenticationService(users)

while True:
    print("--- HOME ---")
    print("1. Sign Up")
    print("2. Sign In")
    print("3. Exit")

    choice = int(input("Enter your choice: "))

    if choice == 1:
        auth_manager.sign_up_form()
    elif choice == 2:
        current_user = auth_manager.sign_in_form()
        if current_user:
            while True:
                print(" ")
                print("1. Account - View Personal Information")
                print("2. Account - Edit Personal Information")
                print("3. Account - Change Password")
                print("4. Check Balance")
                print("5. Add Funds")
                print("6. Withdraw Money")
                print("7. Peer-to-peer Transfers")
                print("8. Transaction History")
                print("9. Logout")

                inner_choice = int(input("Enter your choice: "))

                if inner_choice == 1:
                    AccountService.view_personal_information(current_user)
                elif inner_choice == 2:
                    AccountService.edit_personal_information(current_user)
                elif inner_choice == 3:
                    AccountService.change_password(current_user)
                elif inner_choice == 4:
                    WalletService.check_balance(current_user)
                elif inner_choice == 5:
                    WalletService.add_funds(current_user)
                elif inner_choice == 6:
                    WalletService.withdraw_money(current_user)
                elif inner_choice == 7:
                    WalletService.peer_to_peer_transfers(current_user, users)
                elif inner_choice == 8:
                    WalletService.transaction_history(current_user)
                elif inner_choice == 9:
                    break
                else:
                    print("Invalid choice. Please try again.")
    elif choice == 3:
        print("Exiting application. Goodbye!")
        break
    else:
        print("Invalid choice. Please try again.")
