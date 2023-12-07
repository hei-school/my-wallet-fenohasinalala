class AccountService:
    @staticmethod
    def find_user_by_username(username, users):
        for user in users:
            if user.username == username:
                return user
        return None

    @staticmethod
    def view_personal_information(user):
        print("Personal Information:")
        print("Username: ", user.username)
        print("First Name: ", user.first_name)
        print("Last Name: ", user.last_name)
        print("Email: ", user.email)
        print("Phone Number: ", user.phone_number)

    @staticmethod
    def edit_personal_information(user):
        print("Enter new Last Name:")
        last_name = input()
        print("Enter new First Name:")
        first_name = input()
        print("Enter new Email:")
        email = input()
        print("Enter new Phone Number:")
        phone_number = input()

        user.last_name = last_name
        user.first_name = first_name
        user.email = email
        user.phone_number = phone_number

        AccountService.view_personal_information(user)

    @staticmethod
    def change_password(user):
        print("Enter current password:")
        current_password = input()
        print("Enter new password:")
        new_password = input()
        print("Repeat new password:")
        repeated_password = input()

        if current_password == user.password and new_password == repeated_password:
            user.password = new_password
            print("Password updated")
        else:
            print("Wrong password")

