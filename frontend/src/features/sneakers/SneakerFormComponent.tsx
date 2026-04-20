import React, { useState } from "react";
import type Sneaker from "./Sneaker";
import { createSneaker, updateSneaker } from "./sneakerApi";
import { mockBrands } from "../brands/mockBrandData";

export default function SneakerForm({ sneaker, updateView }: { sneaker: Sneaker | null, updateView: () => void }) {
    const isEditing = sneaker !== null;
    const [formErrors, setFormErrors] = useState<Record<string, string>>({});
    const [submitError, setSubmitError] = useState<string | null>(null);

    function validateForm(data: FormData) {
        const errors: Record<string, string> = {};
        if (!data.get('model')) errors.model = 'Model is required';
        if (!data.get('brand-id')) errors.brandId = 'Brand is required';
        const size = Number(data.get('size'));
        if (!size) errors.size = 'Size is required';
        else if (size < 35 || size > 50) errors.size = 'Size must be between 35 and 50';
        if (!data.get('colorway')) errors.colorway = 'Colorway is required';
        const buyPrice = Number(data.get('buy-price'));
        if (!buyPrice) errors.buyPrice = 'Buy price is required';
        else if (buyPrice <= 0) errors.buyPrice = 'Buy price must be greater than 0';
        if (!data.get('condition')) errors.condition = 'Condition is required';
        
        return errors;
    }

    async function handleSubmit(e: React.SubmitEvent<HTMLFormElement>) {
        e.preventDefault();
        setSubmitError(null);
        const formData = new FormData(e.currentTarget);
        const errors = validateForm(formData);
        if (Object.keys(errors).length > 0) {
            setFormErrors(errors);
            return;
        }
        try {
            setFormErrors({});
            if (isEditing) {
                await updateSneaker(sneaker.id, {
                    model: formData.get('model')?.toString() ?? '',
                    brandId: Number(formData.get('brand-id')),
                    size: Number(formData.get('size')),
                    colorway: formData.get('colorway')?.toString() ?? '',
                    buyPrice: Number(formData.get('buy-price')),
                    condition: formData.get('condition')?.toString() ?? ''
                });
            } else {
                await createSneaker({ 
                    model: formData.get('model')?.toString() ?? '',
                    brandId: Number(formData.get('brand-id')),
                    size: Number(formData.get('size')),
                    colorway: formData.get('colorway')?.toString() ?? '',
                    buyPrice: Number(formData.get('buy-price')),
                    condition: formData.get('condition')?.toString() ?? ''
                });
            }
            updateView();
        } catch (err) {
            if (err instanceof Error) {
                try {
                    const apiErrors = JSON.parse(err.message);
                    setFormErrors(apiErrors);
                } catch (error) {
                    setSubmitError(err.message);
                }
            }
        }
    }

    return (
        <form className="w-100 mt-4 mx-auto px-4" style={{ maxWidth: '600px' }} onSubmit={handleSubmit}>
            <h3 className="w-fit-content mb-3">{isEditing ? 'Update' : 'Add'} sneaker</h3>
            <fieldset className="form-group mb-2">
                <label htmlFor="model" className="form-label mb-1">Model</label>
                <input type="text" name="model" id="model" className="form-control" defaultValue={sneaker?.model} />
                {formErrors.model && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.model}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group mb-2">
                <label htmlFor="brand" className="form-label mb-1">Brand</label>
                <select name="brand-id" id="brand" className="form-select w-fit-content" defaultValue={sneaker?.brand?.id}>
                    <option value="">Choose</option>
                    {mockBrands.map(b => <option key={b.id} value={b.id}>{b.name}</option>)}
                </select>
                {formErrors.brandId && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.brandId}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group mb-2">
                <label htmlFor="size" className="form-label mb-1">Size</label>
                <input type="number" name="size" id="size" className="form-control w-fit-content" step=".25" defaultValue={sneaker?.size} />
                {formErrors.size && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.size}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group mb-2">
                <label htmlFor="colorway" className="form-label mb-1">Colorway</label>
                <input type="text" name="colorway" id="colorway" className="form-control" defaultValue={sneaker?.colorway} />
                {formErrors.colorway && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.colorway}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group mb-2">
                <label htmlFor="buy-price" className="form-label mb-1">Buy price</label>
                <input type="number" name="buy-price" id="buy-price" className="form-control w-fit-content" step=".01" defaultValue={sneaker?.buyPrice} />
                {formErrors.buyPrice && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.buyPrice}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group">
                <label htmlFor="condition" className="form-label mb-1">Condition</label>
                <select name="condition" id="condition" className="form-select w-fit-content" defaultValue={sneaker?.condition}>
                    <option value="">Choose</option>
                    <option value="NEW">New</option>
                    <option value="USED">Used</option>
                </select>
                {formErrors.condition && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.condition}</p>
                    </div>
                )}
            </fieldset>
            {submitError && <p className="alert alert-danger mt-3 mb-0">{submitError}</p>}
            <button type="submit" className="btn btn-primary mt-3">{isEditing ? 'Edit' : <><i className="bi bi-plus-lg"></i> Add</>}</button>
        </form>
    );
}