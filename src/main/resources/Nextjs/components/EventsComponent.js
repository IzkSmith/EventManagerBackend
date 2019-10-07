import React from 'react';
import axios from 'axios';
import Link from "next/link";

export default class EventsComponent extends React.Component{
    state = {
        content: []
    }

    componentDidMount() {
        axios.get(`http://localhost:8080/event/all`)
            .then(res => {
                const content = res.data.content;
                this.setState({ content });
                console.log(this.state)
            })
    }



    render() {
        return (
            <ol start="1">
                { this.state.content.map(content =>
                    <li>{content.name} /
                        {content.date} /
                        {content.cityId} /
                        {content.maxMembers}
                        <Link href="/Event{content.id}"><a> About</a></Link>
                    </li>)}
            </ol>
        )
    }
}
