import { useEffect, useState } from "react";
import { mockSneakers } from "./mockSneakerData";
import type Sneaker from "./Sneaker";

export default function useSneakers() {
    const [data, setData] = useState<Sneaker[] | null>(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        setLoading(true);
        setError(null);
        setTimeout(() => {
            setData(mockSneakers);
            setLoading(false);
        }, 600)
    }, []);

    return { data, loading, error };
}