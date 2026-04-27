import axios from "axios";
import type Brand from "./Brand";
import type BrandCommand from "./BrandCommand";

const BASE_URL = `${import.meta.env.VITE_API_URL}/api/brands`;

export async function getAll(): Promise<Brand[]> {
    try {
        const response = await axios.get<Brand[]>(BASE_URL);
        return response.data;
    } catch (err) {
        if (axios.isAxiosError(err)) {
            console.error('Fetching brands failed:', {
                status: err.status,
                data: err.response?.data,
                message: err.message
            });
            throw new Error(err.response?.data?.message ?? err.message);
        }
        throw err;
    }
}

export async function getById(id: number): Promise<Brand> {
    try {
        const response = await axios.get(`${BASE_URL}/${id}`);
        return response.data;
    } catch (err) {
        if (axios.isAxiosError(err)) {
            console.error('Fetching brand failed:', {
                status: err.status,
                data: err.response?.data,
                message: err.message
            });
            throw new Error(err.response?.data?.message ?? err.message);
        }
        throw err;
    }
}

export async function createBrand(command: BrandCommand): Promise<Brand> {
    try {
        const response = await axios.post<Brand>(BASE_URL, command);
        return response.data;
    } catch (err) {
        if (axios.isAxiosError(err)) {
            console.error('Create brand failed:', {
                status: err.response?.status,
                data: err.response?.data
            });
            if (err.response?.status === 400) {
                throw new Error(JSON.stringify(err.response.data));
            }
            if (err.response?.status === 409) throw new Error('Brand already exists');
            throw new Error(err.response?.data.message ?? 'Failed to create brand');
        }
        throw err;
    }
}

export async function updateBrand(id: number, brand: BrandCommand) {
    try {
        const response = await axios.patch(`${BASE_URL}/${id}`, brand);
        return response.data;
    } catch (err) {
        if (axios.isAxiosError(err)) {
            console.error('Update brand failed:', {
                status: err.response?.status,
                data: err.response?.data
            });
            if (err.response?.status === 400) {
                throw new Error(JSON.stringify(err.response.data));
            }
            if (err.response?.status === 404) throw new Error('Brand not found');
            throw new Error(err.response?.data.message ?? 'Failed to update brand');
        }
        throw err;
    }
}

export async function deleteBrand(id: number) {
    try {
        return await axios.delete(`${BASE_URL}/${id}`);
    } catch (err) {
        if (axios.isAxiosError(err)) {
            console.error('Delete brand failed:', {
                status: err.status,
                data: err.response?.data,
                message: err.message
            });
            throw new Error(err.response?.data?.message ?? err.message);
        }
        throw err;
    }
}