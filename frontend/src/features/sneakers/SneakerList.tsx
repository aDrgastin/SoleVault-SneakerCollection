import { Link } from "react-router-dom";
import useSneakers from "./useSneakers";
import { deleteSneaker } from "./sneakerApi";
import { useState } from "react";
import SneakerForm from "./SneakerForm";
import type Sneaker from "./Sneaker";

export default function SneakerList() {
    const { data, setData, loading, error, refetch } = useSneakers();
    const [showForm, setShowForm] = useState(false);
    const [selectedSneaker, setSelectedSneaker] = useState<Sneaker | null>(null);

    async function handleDelete(id: number) {
        if (!confirm('Are you sure you want to delete a sneaker?')) {
            return;
        }
        const previous = data ?? [];
        setData(previous.filter(s => s.id !== id));
        try {
            await deleteSneaker(id);
        } catch(err) {
            setData(previous);
            console.error(err instanceof Error ? err.message : 'Unknown error while deleting a sneaker');
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
    if (!data) return null;
    return (
        <>
            <div className="d-flex justify-content-between align-items-center mb-2">
                <div>
                    <h2 className="mb-0">Sneaker list</h2>
                    <small className="text-muted">{ data.length } sneakers</small>
                </div>
                {showForm ? <button className="btn btn-danger px-3 py-2 lh-1 fs-7 fw-semibold" onClick={() => {setShowForm(false); setSelectedSneaker(null);}}>Cancel</button> : <button className="btn btn-primary px-3 py-2 lh-1 fs-7 fw-semibold" onClick={() => {setShowForm(true); setSelectedSneaker(null);}}>Add</button>}
            </div>
            <div className="table-responsive">
                <table className="table table-hover align-middle mb-0">
                    <thead className="table-dark">
                        <tr>
                            <th>Id</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Size</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.map(s => 
                            <tr key={s.id}>
                                <td>{ s.id }</td>
                                <td className="fw-semibold">{ s.brand?.name ?? '-' }</td>
                                <td className="fw-semibold">{ s.model }</td>
                                <td>{ s.size }</td>
                                <td className="text-nowrap">
                                    <Link to={`/sneakers/${Number(s.id)}`} className="btn btn-light px-2 py-1 me-2"><i className="bi bi-eye-fill"></i></Link>
                                    {/*<Link to={`/sneakers/${Number(s.id)}/edit`} className="btn btn-warning px-2 py-1 me-2"><i className="bi bi-pencil-fill"></i></Link>*/}
                                    <button type="button" className="btn btn-warning px-2 py-1 me-2" onClick={() => {setShowForm(true); setSelectedSneaker(s);}}><i className="bi bi-pencil-fill"></i></button>
                                    <button type="button" className="btn btn-danger px-2 py-1 text-black" onClick={() => handleDelete(s.id)}><i className="bi bi-trash-fill"></i></button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
            {showForm && 
                <SneakerForm
                    key={selectedSneaker?.id}
                    sneaker={selectedSneaker}
                    updateView={() => {
                        refetch();
                        setShowForm(false);
                        setSelectedSneaker(null);
                    }}
                />
            }
        </>
    );
}