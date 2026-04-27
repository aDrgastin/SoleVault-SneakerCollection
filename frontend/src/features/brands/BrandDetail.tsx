import { useEffect, useState } from "react";
import type Brand from "./Brand";
import { Link, useNavigate, useParams } from "react-router-dom";
import { deleteBrand, getById } from "./brandApi";

export default function BrandDetail() {
    const { id } = useParams();
    const [brand, setBrand] = useState<Brand | null>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        setError(null);
        if (id) getById(Number(id)).then(setBrand).catch(err => {
            setError(err.message);
        }).finally(() => setLoading(false));
    }, [id]);

    async function handleDelete(id: number) {
        if (!confirm('Are you sure you want to delete a brand?')) {
            return;
        }
        try {
            await deleteBrand(id);
            setBrand(null);
            navigate('../');
        } catch(err) {
            setError(err instanceof Error ? err.message : 'Unknown error while deleting a brand');
        }
    }

    if (loading) return (
        <div className="d-flex justify-content-center py-5">
            <div className="spinner-border text-dark" role="status">
                <span className="visually-hidden"></span>
            </div>
        </div>
    );
    if (error) return <div className="container mt-4 text-danger">{ error }</div>;
    if (!brand) return null;
    return (
        <>
            <div className="w-100 container-fluid mt-3 ms-0" style={{ maxWidth: '600px' }}>
                <div className="row gx-1 mb-3">
                    <div className="col">
                        <h3 className="mb-0">{ brand.name }</h3>
                        <h5 className="mb-0">{ brand.country }</h5>
                        <p className="mb-2">Since { brand.founded.toLocaleString() }</p>
                        <img src={brand.logoUrl || undefined} className="img-fluid" style={{ maxWidth: '100px' }} alt="Logo" />
                    </div>
                </div>
                <div className="row">
                    <div className="col d-flex flex-wrap gap-2">
                        <Link to={`/brands/${Number(id)}/edit`} className="btn btn-warning px-3 py-2 fs-7 fw-semibold"><i className="bi bi-pencil-fill"></i> Edit</Link>
                        <button type="button" className="btn btn-danger px-3 py-2 text-black fs-7 fw-semibold" onClick={() => handleDelete(brand.id)}><i className="bi bi-trash-fill"></i> Delete</button>
                        <Link to={`../`} className="btn btn-light px-3 py-2 fs-7"><i className="bi bi-arrow-left"></i> Back</Link>
                    </div>
                </div>
            </div>
        </>
    );
}