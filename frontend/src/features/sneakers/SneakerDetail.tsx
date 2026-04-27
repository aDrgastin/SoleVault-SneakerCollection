import { Link, useNavigate, useParams } from "react-router-dom";
import type Sneaker from "./Sneaker";
import { useEffect, useState } from "react";
import { deleteSneaker, getSneaker } from "./sneakerApi";

export default function SneakerDetail() {
    const { id } = useParams();
    const [sneaker, setSneaker] = useState<Sneaker | null>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        setError(null);
        if (id) getSneaker(Number(id)).then(setSneaker).catch(err => {
            setError(err.message);
        }).finally(() => setLoading(false));
    }, [id]);

    async function handleDelete(id: number) {
        if (!confirm('Are you sure you want to delete a sneaker?')) {
            return;
        }
        try {
            await deleteSneaker(id);
            setSneaker(null);
            navigate('../');
        } catch(err) {
            setError(err instanceof Error ? err.message : 'Unknown error while deleting a sneaker');
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
    if (!sneaker) return null;
    return (
        <>
            <div className="w-100 container-fluid mt-3 ms-0" style={{ maxWidth: '600px' }}>
                <div className="row gx-1 mb-3">
                    <div className="col">
                        <h5 className="mb-0">{ sneaker.brand?.name ?? '-' }</h5>
                        <h3 className="mb-0">{ sneaker.model }</h3>
                        <small>{sneaker.condition}</small>
                        <p>Size { sneaker.size }</p>
                        <p>{ sneaker.colorway }</p>
                        <h6 className="mt-1">Bought for €{ sneaker.buyPrice }, now €{sneaker.currentValue} → ({sneaker.profitLoss >= 0 ? <span className="text-primary">€+{sneaker.profitLoss}</span> : <span className="text-danger">€{sneaker.profitLoss}</span>})</h6>
                    </div>
                </div>
                <div className="row">
                    <div className="col d-flex flex-wrap gap-2">
                        <Link to={`/sneakers/${Number(id)}/edit`} className="btn btn-warning px-3 py-2 fs-7 fw-semibold"><i className="bi bi-pencil-fill"></i> Edit</Link>
                        <button type="button" className="btn btn-danger px-3 py-2 text-black fs-7 fw-semibold" onClick={() => handleDelete(sneaker.id ?? NaN)}><i className="bi bi-trash-fill"></i> Delete</button>
                        <Link to={`../`} className="btn btn-light px-3 py-2 fs-7"><i className="bi bi-arrow-left"></i> Back</Link>
                    </div>
                </div>
            </div>
        </>
    );
}