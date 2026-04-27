import { useEffect, useState } from "react";
import type Brand from "./Brand";
import { getAll } from "./brandApi";

export default function useBrands() {
    const [data, setData] = useState<Brand[] | null>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const fetchBrands = async () => {
        setLoading(true);
        setError(null);
        try {
            const brands = await getAll();
            setData(brands);
        } catch (err) {
            setError(err instanceof Error ? err.message : 'Unknown error');
        } finally {
            setLoading(false);
        }
    };
    useEffect(() => { fetchBrands() }, []);

    return { data, setData, loading, error, refetch: fetchBrands };
}