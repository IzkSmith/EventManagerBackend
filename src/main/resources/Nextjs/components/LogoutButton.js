import React, { Component } from 'react';
import AuthService from '../Service/AuthService';
import withAuth from './withAuth';

const Auth = new AuthService();

class App extends Component {

    handleLogout(){
        Auth.logout()
        window.location.href = '/';
    }

    render() {
        debugger
        return (
            <div >
                    <button type="button" className = "logout" onClick={this.handleLogout.bind(this)}>Logout</button>
            </div>
        );
    }
}

export default withAuth(App);