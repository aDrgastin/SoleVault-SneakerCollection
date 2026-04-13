import type Sneaker from "./Sneaker";

export default function SneakerList({ sneakers, loading, error, selectSneaker }: { 
    sneakers: Sneaker[], loading: boolean, error: string | null, selectSneaker: (id: number) => void 
}) {
    if (loading) return (
        <div className="d-flex justify-content-center py-5">
            <div className="spinner-border text-dark" role="status">
                <span className="visually-hidden"></span>
            </div>
        </div>
    );
    if (error) return <div className="container mt-4 text-danger">{ error }</div>;
    return (
        <>
            <div className="mb-2">
                <h2 className="mb-0">Sneaker list</h2>
                <small className="text-muted">{ sneakers.length } sneakers</small>
            </div>
            <div className="table-responsive">
                <table className="table table-hover align-middle mb-0">
                    <thead className="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Size</th>
                        </tr>
                    </thead>
                    <tbody>
                        { sneakers.map((s, ind) => 
                            <>
                            <tr className="position-relative">
                                <td>{ ind }</td>
                                <td className="fw-semibold">{ s.brand }</td>
                                <td className="fw-semibold">
                                    <button type="button" className="btn btn-link p-0 fw-semibold stretched-link" style={{fontSize: '16px'}} onClick={ () => selectSneaker(sneakers.indexOf(s)) }>{ s.model }</button>
                                </td>
                                <td>{ s.size }</td>
                            </tr>
                            </>)
                        }
                    </tbody>
                </table>
            </div>
        </>
    );
}