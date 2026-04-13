import { useState } from 'react'
import './App.css'
import SneakerList from './features/sneakers/SneakerListComponent'
import SneakerDetail from './features/sneakers/SneakerDetailComponent'
import useSneakers from './features/sneakers/useSneakers'
import BrandList from './features/brands/BrandListComponent'
import BrandDetail from './features/brands/BrandDetailComponent'
import useBrands from './features/brands/useBrands'

function App() {
    const [selectedSneakerId, setSelectedSneakerId] = useState<number | null>(null);
    const { data: sneakers, loading: loadingSneakers, error: errorSneakers } = useSneakers();
    const [selectedBrandId, setSelectedBrandId] = useState<number | null>(null);
    const { data: brands, loading: loadingBrands, error: errorBrands } = useBrands();

    return (
        <>
            <nav className="navbar navbar-dark bg-dark mb-4">
                <div className="container">
                    <a className="navbar-brand fw-bold" href='#'>
                        <img className='d-inline-block me-2' src="/sneaker-icon.png" alt="SoleVault logo" />
                        SoleVault
                    </a>
                </div>
            </nav>
            <div className="container py-3 border border-secondary-subtle rounded">
                <SneakerList
                    sneakers={sneakers ?? []}
                    loading={loadingSneakers}
                    error={errorSneakers}
                    selectSneaker={(id: number) => setSelectedSneakerId(id)} />
                { selectedSneakerId !== null && 
                    <SneakerDetail 
                        sneakers={sneakers ?? []} 
                        selectedSneakerId={selectedSneakerId} 
                        unselectSneaker={() => setSelectedSneakerId(null)} 
                    /> 
                }
                <BrandList 
                    data={brands ?? []}
                    loading={loadingBrands}
                    error={errorBrands}
                    selectBrand={(id: number) => setSelectedBrandId(id)}
                />
                { selectedBrandId !== null &&
                    <BrandDetail 
                        brands={brands ?? []}
                        selectedBrandId={selectedBrandId}
                        unselectBrand={() => setSelectedBrandId(null)}
                    />
                }
            </div>
            <footer className="bg-dark text-white text-center py-3 mt-5">

            </footer>
        </>
    );
}

export default App
