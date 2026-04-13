import type Sneaker from "./Sneaker";

export default function SneakerDetail({ sneakers, selectedSneakerId, unselectSneaker }: { 
    sneakers: Sneaker[], selectedSneakerId: number, unselectSneaker: () => void 
}) {
    const sneaker = sneakers[selectedSneakerId];

    if (!sneaker) return null;
    return (
        <>
            <div className="container-fluid mt-3">
                <div className="row gx-1">
                    <div className="col-1 position-relative">
                        <button type="button" className="btn btn-lg p-0 fs-4 position-absolute top-50 start-50 translate-middle" onClick={() => unselectSneaker()}><i className="bi bi-x-square-fill"></i></button>
                    </div>
                    <div className="col">
                        <h5 className="mb-0">{ sneaker.brand }</h5>
                        <h3>{ sneaker.model }</h3>
                        <p>{ sneaker.colorway }</p>
                    </div>
                </div>
            </div>
        </>
    );
}