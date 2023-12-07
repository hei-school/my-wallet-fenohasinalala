
from User import User


class AuthenticationService:
    def __init__(self, users):
        self.users = users

    def sign_up_form(self):
        print("Enter Last Name:")
        last_name = input()
        print("Enter First Name:")
        first_name = input()
        print("Enter Email:")
        email = input()
        print("Enter Username:")
        username = input()
        print("Enter Password:")
        password = input()

        return self.sign_up(last_name, first_name, email, username, password)

    def sign_up(self, last_name, first_name, email, username, password):
        for user in self.users:
            if user.username == username:
                print("Username is already taken. Please choose a different username.")
                return False

        new_user = User(username, password, first_name, last_name, email)
        self.users.append(new_user)

        print("User created successfully! You can sign in now!")
        return True

    def sign_in_form(self):
        print("Enter Username:")
        username = input()
        print("Enter Password:")
        password = input()

        return self.sign_in(username, password)

    def sign_in(self, username, password):
        for user in self.users:
            if user.username == username and user.password == password:
                print("Sign In successful! You can manage your wallet now!")
                return user

        print("Invalid credentials. Sign In failed.")
        return None

