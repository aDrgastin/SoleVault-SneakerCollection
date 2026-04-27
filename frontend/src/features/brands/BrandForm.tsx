import React, { useState } from "react";
import type Brand from "./Brand";
import { createBrand, updateBrand } from "./brandApi";

export default function BrandForm({ brand, updateView }: { brand: Brand | null, updateView: () => void }) {
    const isEditing = brand !== null;
    const [formErrors, setFormErrors] = useState<Record<string, string>>({});
    const [submitError, setSubmitError] = useState<string | null>(null);

    function validateForm(data: FormData) {
        const errors: Record<string, string> = {};
        const name = data.get('name')?.toString();
        if (!name) errors.name = 'Name is required';
        else if (name.length > 50) errors.name = 'Name cannot be longer than 50 characters';
        const country = data.get('country')?.toString();
        if (!country) errors.country = 'Country is required';
        else if (country.length > 50) errors.country = 'Country cannot be longer than 50 characters';
        const foundedRaw = data.get('founded')?.toString();
        const founded = foundedRaw ? new Date(foundedRaw ?? '') : null;
        if (!founded) errors.founded = 'Founded is required';
        else if (founded > new Date()) errors.founded = 'Founded cannot be in the future';
        const logoUrl = data.get('logoUrl')?.toString();
        if (logoUrl && !/^(https?:\/\/.*\.(?:png|jpg|jpeg|gif|webp|svg))$/i.test(logoUrl)) errors.logoUrl = 'URL format is not valid';

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
            if (isEditing){
                await updateBrand(brand.id, {
                    name: formData.get('name')?.toString() ?? '',
                    country: formData.get('country')?.toString() ?? '',
                    founded: new Date(formData.get('founded')?.toString() ?? ''),
                    logoUrl: formData.get('logoUrl')?.toString() ?? ''
                });
            } else {
                await createBrand({
                    name: formData.get('name')?.toString() ?? '',
                    country: formData.get('country')?.toString() ?? '',
                    founded: new Date(formData.get('founded')?.toString() ?? ''),
                    logoUrl: formData.get('logoUrl')?.toString() ?? ''
                });
            }
            updateView();
        } catch (err) {
            if (err instanceof Error) {
                try {
                    const apiErrors = JSON.parse(err.message);
                    setFormErrors(apiErrors);
                } catch {
                    setSubmitError(err.message);
                }
            }
        }
    }

    return (
        <form className="w-100 mt-4 mx-auto px-4" style={{ maxWidth: '600px' }} onSubmit={handleSubmit}>
            <h3 className="w-fit-content mb-3">{isEditing ? 'Update' : 'Add'} brand</h3>
            <fieldset className="form-group mb-2">
                <label htmlFor="name" className="form-label mb-1">Name</label>
                <input type="text" name="name" id="name" className="form-control" defaultValue={brand?.name} />
                {formErrors.name && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.name}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group mb-2">
                <label htmlFor="country" className="form-label mb-1">Country</label>
                <input type="text" name="country" id="country" className="form-control" defaultValue={brand?.country} />
                {formErrors.country && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.country}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group mb-2">
                <label htmlFor="founded" className="form-label mb-1">Founded</label>
                <input type="date" name="founded" id="founded" className="form-control w-fit-content" defaultValue={brand?.founded.toString()}/>
                {formErrors.founded && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.founded}</p>
                    </div>
                )}
            </fieldset>
            <fieldset className="form-group">
                <label htmlFor="logo" className="form-label mb-1">Logo</label>
                <input type="text" name="logoUrl" id="logo" className="form-control" defaultValue={brand?.logoUrl} />
                {formErrors.logoUrl && (
                    <div className="form-control-feedback mt-2 text-danger">
                        <p className="alert alert-danger mb-0 py-2">{formErrors.logoUrl}</p>
                    </div>
                )}
            </fieldset>
            {submitError && <p className="alert alert-danger mt-3 mb-0">{submitError}</p>}
            <button type="submit" className="btn btn-primary mt-3">{isEditing ? 'Edit' : <><i className="bi bi-plus-lg"></i> Add</>}</button>
        </form>
    );
}