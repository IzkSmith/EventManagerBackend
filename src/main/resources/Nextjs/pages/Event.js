import css from "../index.css"
import LogoutButton from "../components/LogoutButton";

const Event = () => (
    <div>
        <div className="events-box">
            <h1>Our event</h1>
            <p>Date </p>
            <p>City </p>
            <p>max_members / current_members</p>
            <h3>Description</h3>
            <p>Description.................................................................................</p>
        </div>
        <div>
            <LogoutButton/>
            <input className="signup"  type="submit" name="" value="sign up"/>
        </div>
    </div>
)

export default Event