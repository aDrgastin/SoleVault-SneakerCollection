import type Sneaker from "./Sneaker";
import type SneakerCommand from "./SneakerCommand";

const BASE_URL = `${import.meta.env.VITE_API_URL}/api/sneakers`;

export async function getAllSneakers(): Promise<Sneaker[]> {
    const res = await fetch(BASE_URL);
    if (!res.ok) throw new Error('Failed to fetch sneakers');
    return res.json();
}

export async function getSneaker(id: number): Promise<Sneaker> {
    const res = await fetch(`${BASE_URL}/${id}`);
    if (res.status === 404) throw new Error('Not found');
    if (!res.ok) throw new Error('Failed to fetch sneaker by id');
    return res.json();
}

export async function createSneaker(command: SneakerCommand): Promise<Sneaker> {
    const res = await fetch(BASE_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(command) 
    });
    if (res.status === 400) {
        const errors = await res.json();
        throw new Error(JSON.stringify(errors));
    };
    if (res.status === 409) throw new Error('Sneaker already exists');
    if (!res.ok) throw new Error('Failed to add new sneaker');
    return res.json();
}

export async function updateSneaker(id: number, sneaker: SneakerCommand) {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(sneaker)
    });
    if (res.status === 400) {
        const errors = await res.json();
        throw new Error(JSON.stringify(errors));
    };
    if (res.status === 404) throw new Error('Sneaker not found');
    if (!res.ok) throw new Error('Failed to update sneaker');
    return res.json();
}

export async function deleteSneaker(id: number) {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE'
    });
    if (res.status === 404) throw new Error('Sneaker not found');
    if (!res.ok) throw new Error('Failed to delete sneaker');
    return;
}