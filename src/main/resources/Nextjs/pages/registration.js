import css from "../index.css"
import SimpleForm from '../components/SimpleForm';
import Link from 'next/link';

const Registration = () => (
    <div className="login-box">
        <h1>Registration</h1>
        <div><SimpleForm/></div>
        <Link href="/"><input type="button" className="btn" value="Sign up"></input></Link>
    </div>
)

export default Registration