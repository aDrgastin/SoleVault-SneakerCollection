import type Brand from "../brands/Brand";

export default interface Sneaker {
    id: number;
    model: string;
    brand: Brand;
    size: number;
    colorway: string;
    buyPrice: number;
    currentValue: number;
    profitLoss: number;
    condition: string;
}