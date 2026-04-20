import { Link } from "react-router-dom";

export default function Home() {
    return (
        <div className="container justify-content-center">
            <Link to="/sneakers" className="btn btn-primary">Sneaker list</Link>
        </div>
    );
}