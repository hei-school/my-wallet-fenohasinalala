class AuthenticationService {
    constructor(users) {
        this.users = users;
    }

    signUpForm() {
        console.log("Enter Last Name:");
        const lastName = prompt();
        console.log("Enter First Name:");
        const firstName = prompt();
        console.log("Enter Email:");
        const email = prompt();
        console.log("Enter Username:");
        const username = prompt();
        console.log("Enter Password:");
        const password = prompt();

        return this.signUp(lastName, firstName, email, username, password);
    }

    signUp(lastName, firstName, email, username, password) {
        if (this.users.some(user => user.username === username)) {
            console.log("Username is already taken. Please choose a different username.");
            return false;
        }

        const newUser = new User(username, password, firstName, lastName, email);
        this.users.push(newUser);

        console.log("User created successfully! You can sign in now!");
        return true;
    }

    signInForm() {
        console.log("Enter Username:");
        const username = prompt();
        console.log("Enter Password:");
        const password = prompt();

        return this.signIn(username, password);
    }

    signIn(username, password) {
        const user = this.users.find(user => user.username === username && user.password === password);

        if (user) {
            console.log("Sign In successful! You can manage your wallet now!");
            return user;
        }

        console.log("Invalid credentials. Sign In failed.");
        return null;
    }
}

module.exports = { AuthenticationService };
