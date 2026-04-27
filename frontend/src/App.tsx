import './App.css'
import SneakerList from './features/sneakers/SneakerList'
import SneakerDetail from './features/sneakers/SneakerDetail'
import { Route, Routes } from 'react-router-dom'
import Layout from './components/Layout'
import Home from './components/Home'
import BrandList from './features/brands/BrandList'
import BrandDetail from './features/brands/BrandDetail'

function App() {
    return (
        <Routes>
            <Route element={<Layout />}>
                <Route index element={<Home />} />
                <Route path="/sneakers">
                    <Route index element={<SneakerList />} />
                    <Route path=":id" element={<SneakerDetail />} />
                </Route>
                <Route path="/brands">
                    <Route index element={<BrandList />} />
                    <Route path=":id" element={<BrandDetail />} />
                </Route>
                <Route path="*"  />
            </Route>
        </Routes>
    );
}

export default App
