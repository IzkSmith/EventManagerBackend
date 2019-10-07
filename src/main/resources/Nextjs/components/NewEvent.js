import React, { Component } from 'react';
import AuthService from "../Service/NewEventService";
import LogoutButton from "./LogoutButton";

class NewEvent extends Component {
    constructor(){
        super();
        this.handleChange = this.handleChange.bind(this);
        this.handleFormSubmit = this.handleFormSubmit.bind(this);
        this.Auth = new AuthService();
    }
    render() {
        debugger
        return (
            <div>
                <div className="login-box">
                    <h1>Create new Event</h1>
                    <form onSubmit={this.handleFormSubmit}>
                        <input className="textbox"
                               name="name"
                               type="text"
                               placeholder="Event name"
                               onChange={this.handleChange}
                        />
                        <input className="textbox"
                               name="date"
                               type="datetime-local"
                               placeholder="Date"
                               onChange={this.handleChange}
                        />
                        <input className="textbox"
                               name="cityId"
                               type="number"
                               placeholder="City"
                               onChange={this.handleChange}
                        />
                        <input className="textbox"
                               name="maxMembers"
                               type="number"
                               placeholder="Max members"
                               onChange={this.handleChange}
                        />
                        <input className="textbox"
                               name="description"
                               type="text"
                               placeholder="Description"
                               onChange={this.handleChange}
                        />

                        <input type="submit" className="btn" value="submit"/>
                    </form>
                </div>
                <LogoutButton/>
            </div>
        );
    }

    handleFormSubmit(e){
        e.preventDefault();
        console.log(this.state)
        this.Auth.newEvent(this.state.name, this.state.date, this.state.cityId,  this.state.maxMembers, this.state.description)
            .then(res =>{
                window.location.href = '/events';
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

export default NewEvent;