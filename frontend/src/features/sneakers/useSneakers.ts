import { useEffect, useState } from "react";
import type Sneaker from "./Sneaker";
import { getAllSneakers } from "./sneakerApi";

export default function useSneakers() {
    const [data, setData] = useState<Sneaker[] | null>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const fetchSneakers = async () => {
        setLoading(true);
        setError(null);
        try {
            const sneakers = await getAllSneakers();
            setData(sneakers);
        } catch (err) {
            setError(err instanceof Error ? err.message : 'Unknown error');
        } finally {
            setLoading(false);
        }
    };
    useEffect(() => { fetchSneakers(); }, []);

    return { data, setData, loading, error, refetch: fetchSneakers };
}