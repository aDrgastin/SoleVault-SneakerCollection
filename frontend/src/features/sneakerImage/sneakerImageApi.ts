import axios from "axios";
import type SneakerImage from "./SneakerImage";

const BASE_URL = `${import.meta.env.VITE_API_URL}/api/sneaker-image`;

export async function getImagesBySneakerId(sneakerId: number): Promise<SneakerImage[]> {
    try {
        const res = await axios.get<SneakerImage[]>(`${BASE_URL}/sneaker/${sneakerId}`);
        return res.data;
    } catch (err) {
        if (axios.isAxiosError(err)) {
            console.error('Fetching sneaker images failed:', {
                status: err.status,
                data: err.response?.data,
                message: err.message
            });
            throw new Error(err.response?.data.message ?? err.message);
        }
        throw err;
    }
}
