import React from "react";
import TextField from './TextField';

export default class SimpleForm extends React.Component {
    state = {
        firstName: "",
        firstNameError: "",
        email: "",
        emailError: ""
    };

    validateName = name => {
        const regex = /[A-Za-z]{3,}/;

        return !regex.test(name)
            ? "The name must contain at least three letters. Numbers and special characters are not allowed."
            : "";
    };

    validateEmail = email => {
        const regex = /[A-Za-z]{3,}/;

        return !regex.test(email)
            ? "The Email must contain at least three letters and @.Special characters are not allowed."
            : "";
    };
    onFirstNameBlur = () => {
        const { firstName } = this.state;

        const firstNameError = this.validateName( firstName );

        return this.setState({ firstNameError });
    };

    onFirstNameChange = event =>
        this.setState({
            firstName: event.target.value
        });

    onEmailBlur = () => {
        const { email } = this.state;

        const emailError = this.validateEmail (email);

        return this.setState({ emailError: emailError });
    };

    onEmailChange = event =>
        this.setState({
            email: event.target.value
        });

    render() {
        const { firstNameError, firstName, email, emailError } = this.state;

        return (
            <div>
                <TextField name="firstName"
                           type="text"
                           placeholder="Username"
                           onChange={this.onFirstNameChange}
                           onBlur={this.onFirstNameBlur}
                           error={firstNameError} />

                <TextField name="email"
                           type="email"
                           placeholder="Email"
                           onChange={this.onEmailChange}
                           onBlur={this.onEmailBlur}
                           error={emailError} />
                <TextField name="password"
                           type="password"
                           placeholder="Password"
                           />
                <TextField name="Repeat password"
                           type="password"
                           placeholder="Repeat password"
                />
            </div>
        );
    }
}