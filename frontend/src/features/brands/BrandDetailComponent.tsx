import type Brand from "./Brand";

export default function BrandDetail({ brands, selectedBrandId, unselectBrand }: {
    brands: Brand[], selectedBrandId: number, unselectBrand: () => void
}) {
    const brand = brands[selectedBrandId];

    if (!brand) return null;
    return (
        <>
            <div className="container-fluid mt-3">
                <div className="row gx-3">
                    <div className="col-1 position-relative">
                        <button type="button" className="btn btn-lg p-0 fs-4 position-absolute top-50 start-50 translate-middle" onClick={() => unselectBrand()}><i className="bi bi-x-square-fill"></i></button>
                    </div>
                    <div className="col">
                        <img src={brand.logoUrl} className="img-fluid mb-2" style={{ maxWidth: '175px' }} alt="Logo" />
                        <h5 className="mb-0">{ brand.name }</h5>
                        <h3>{ brand.country }</h3>
                        <p>{ brand.founded.toLocaleDateString('hr-HR') }</p>
                    </div>
                </div>
            </div>
        </>
    );
}