import { useEffect, useState } from "react";
import type Brand from "./Brand";
import { mockBrands } from "./mockBrandData";

export default function useBrands() {
    const [data, setData] = useState<Brand[] | null>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        setLoading(true);
        setError(null);
        setTimeout(() => {
            setData(mockBrands);
            setLoading(false);
        }, 400);
    }, []);

    return { data, loading, error };
}