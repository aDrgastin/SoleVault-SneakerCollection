import type Brand from "./Brand";

export default function BrandList({ data, loading, error, selectBrand }: {
    data: Brand[], loading: boolean, error: string | null, selectBrand: (id: number) => void
}) {

    if (loading) {
        return <>
            <div className="d-flex justify-content-center py-5">
                <div className="spinner-border text-dark" role="status">
                    <span className="visually-hidden"></span>
                </div>
            </div>
        </>;
    }
    if (error) return <div className="container mt-4 text-danger">{ error }</div>;
    if (!data) return null;
    return <>
        <div className="mt-3 mb-2">
            <h2 className="mb-0">Brand list</h2>
            <small className="text-muted">{ data.length } brands</small>
        </div>
        <div className="table-responsive">
            <table className="table table-hover align-middle mb-0">
                <thead className="table-dark">
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th>Country</th>
                        <th>Founded</th>
                    </tr>
                </thead>
                <tbody>
                    { data.map((b, ind) => <>
                        <tr className="position-relative">
                            <td>{ ind }</td>
                            <td className="fw-semibold">
                                <button type="button" className="btn btn-link p-0 fw-semibold stretched-link" style={{fontSize: '16px'}} onClick={() => selectBrand(data.indexOf(b))}>{ b.name }</button>
                            </td>
                            <td>{ b.country }</td>
                            <td>{ b.founded.toLocaleDateString('hr-HR') }</td>
                        </tr>
                    </>) }
                </tbody>
            </table>
        </div>
    </>;
}