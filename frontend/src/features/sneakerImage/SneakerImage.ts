import type Sneaker from "../sneakers/Sneaker";

export default interface SneakerImage {
    id: number;
    url: string;
    tag: string;
    description: string;
    sneaker: Sneaker
}
