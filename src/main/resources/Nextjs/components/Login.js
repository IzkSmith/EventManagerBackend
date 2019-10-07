import React, { Component } from 'react';
import AuthService from '../Service/AuthService';
import Link from "next/link";

class Login extends Component {
    constructor(){
        super();
        this.handleChange = this.handleChange.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.Auth = new AuthService();
    }
    componentDidMount(){
        if(this.Auth.loggedIn())
            window.location.href =('/Events');
    }
    render() {
        return (
            <div className="box" >
                    <h1>Login</h1>
                    <form onSubmit={this.handleFormSubmit}>
                        <input
                            placeholder="Username"
                            name="username"
                            type="text"
                            onChange={this.handleChange}
                        />
                        <input
                            placeholder="Password"
                            name="password"
                            type="password"
                            onChange={this.handleChange}
                        />
                        <input
                            value="submit"
                            type="submit"
                        />
                        <p>don't have an account yet? <Link href="/registration"><a>Sign up</a></Link></p>
                    </form>
            </div>
        );
    }

    handleFormSubmit(e){
        e.preventDefault();

        this.Auth.login(this.state.username,this.state.password)
            .then(res =>{
                window.location.href = '/Events';
            })
            .catch(err =>{
                alert(err);
            })
    }

    handleChange(e){
        this.setState(
            {
                [e.target.name]: e.target.value
            }
        )
    }
}

export default Login;