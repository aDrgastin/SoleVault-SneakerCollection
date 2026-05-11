import { NavLink, Outlet } from "react-router-dom";

export default function Layout() {
    return (<>
        <nav className="navbar navbar-dark bg-dark mb-4">
            <div className="container justify-content-start">
                <NavLink to="/" className={({isActive}) => isActive ? 'navbar-brand fw-bold active' : 'navbar-brand fw-bold'} end>
                    <img className='d-inline-block me-2' src="/sneaker-icon.png" alt="SoleVault logo" />
                    SoleVault
                </NavLink>
                <div className="navbar-nav flex-row column-gap-3">
                    <NavLink to="/sneakers" className={({isActive}) => isActive ? 'nav-link active' : 'nav-link'}>Sneakers</NavLink>
                    <NavLink to="/brands" className={({isActive}) => isActive ? 'nav-link active' : 'nav-link'}>Brands</NavLink>
                </div>
            </div>
        </nav>
        <main className="container py-3 border border-secondary-subtle rounded">
            <Outlet />
        </main>
        <footer className="bg-dark text-white text-center py-3 mt-5">

        </footer>
    </>);
}
