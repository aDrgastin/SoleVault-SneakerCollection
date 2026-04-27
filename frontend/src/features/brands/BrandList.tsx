import { Link } from "react-router-dom";
import useBrands from "./useBrands";
import { useState } from "react";
import type Brand from "./Brand";
import { deleteBrand } from "./brandApi";
import BrandForm from "./BrandForm";

export default function BrandList() {
    const { data, setData, loading, error, refetch } = useBrands();
    const [showForm, setShowForm] = useState(false);
    const [selectedBrand, setSelectedBrand] = useState<Brand | null>(null);

    async function handleDelete(id: number) {
        if (!confirm('Are you sure you want to delete a brand?')) {
            return;
        }
        const previous = data ?? [];
        setData(previous.filter(b => b.id !== id));
        try {
            await deleteBrand(id);
        } catch (err) {
            setData(previous);
            console.error(err instanceof Error ? err.message : 'Unknown error while deleting a brand');
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
                    <h2 className="mb-0">Brand list</h2>
                    <small className="text-muted">{ data.length } brands</small>
                </div>
                <button className="btn btn-primary px-3 py-2 lh-1 fs-7 fw-semibold" onClick={() => {setShowForm(true); setSelectedBrand(null);}}>Add</button>
            </div>
            <div className="table-responsive">
                <table className="table table-hover align-middle mb-0">
                    <thead className="table-dark">
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Country</th>
                            <th>Founded</th>
                            <th>Logo</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {data.map(b => 
                            <tr key={b.id}>
                                <td>{ b.id }</td>
                                <td className="fw-semibold">{ b.name }</td>
                                <td className="fw-semibold">{ b.country }</td>
                                <td>{ b.founded.toLocaleString() }</td>
                                <td><img src={b.logoUrl || undefined} className="img-fluid" style={{ maxWidth: '100px' }} alt="Logo" /></td>
                                <td>
                                    <Link to={`/brands/${Number(b.id)}`} className="btn btn-light px-2 py-1 me-2"><i className="bi bi-eye-fill"></i></Link>
                                    {/*<Link to={`/sneakers/${Number(s.id)}/edit`} className="btn btn-warning px-2 py-1 me-2"><i className="bi bi-pencil-fill"></i></Link>*/}
                                    <button type="button" className="btn btn-warning px-2 py-1 me-2" onClick={() => {setShowForm(true); setSelectedBrand(b);}}><i className="bi bi-pencil-fill"></i></button>
                                    <button type="button" className="btn btn-danger px-2 py-1 text-black" onClick={() => handleDelete(b.id)}><i className="bi bi-trash-fill"></i></button>
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
            {showForm && 
                <BrandForm
                    key={selectedBrand?.id}
                    brand={selectedBrand}
                    updateView={() => {
                        refetch();
                        setShowForm(false);
                        setSelectedBrand(null);
                    }}
                />
            }
        </>
    );
}