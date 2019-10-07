import css from "../index.css"

import Link from 'next/link';
import EventsComponent from "../components/EventsComponent";
import LogoutButton from "../components/LogoutButton";

const Events = () => (
    <div>
        <div className="events-box">
            <h1>Events</h1>
                <p>Name / Date / CityId / maxMembers</p>
                <EventsComponent/>
        </div>
        <div>
            <LogoutButton/>
            <Link href="/CreateNewEvent"><input className = "create" type = "submit" name = "" value = "Create new event" ></input></Link>
        </div>
    </div>
)

export default Events