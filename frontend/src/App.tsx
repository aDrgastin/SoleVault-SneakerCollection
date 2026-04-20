import './App.css'
import SneakerList from './features/sneakers/SneakerListComponent'
import SneakerDetail from './features/sneakers/SneakerDetailComponent'
import { Route, Routes } from 'react-router-dom'
import Layout from './components/Layout'
import Home from './components/Home'

function App() {
    return (
        <Routes>
            <Route element={<Layout />}>
                <Route index element={<Home />} />
                <Route path="/sneakers">
                    <Route index element={<SneakerList />} />
                    <Route path=":id" element={<SneakerDetail />} />
                </Route>
                <Route path="*"  />
            </Route>
        </Routes>
    );
}

export default App
