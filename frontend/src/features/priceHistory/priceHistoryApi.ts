import axios from "axios";
import type PriceHistory from "./PriceHistory";

const BASE_URL = `${import.meta.env.VITE_API_URL}/api/price-history`;

export async function getPriceHistoryBySneakerId(sneakerId: number): Promise<PriceHistory[]> {
    try {
        const res = await axios.get<PriceHistory[]>(`${BASE_URL}/sneaker/${sneakerId}`);
    return res.data.map(ph => {
        return { ...ph, recordedAt: new Date(ph.recordedAt) };
    });
    } catch (err) {
        if (axios.isAxiosError(err)) {
            console.error('Fetching price history failed:', {
                status: err.status,
                data: err.response?.data,
                message: err.message
            });
            throw new Error(err.response?.data?.message ?? err.message);
        }
        throw err;
    }
}
