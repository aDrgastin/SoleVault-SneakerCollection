import type Brand from "./Brand";

export const mockBrands: Brand[] = [
    { name: 'Nike', country: 'USA', founded: new Date(1964, 0, 25), logoUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a6/Logo_NIKE.svg/960px-Logo_NIKE.svg.png?_=20220908234918' },
    { name: 'Adidas', country: 'Germany', founded: new Date(1949, 7, 18), logoUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Adidas_2022_logo.svg/960px-Adidas_2022_logo.svg.png?_=20250212205514' },
    { name: 'Puma', country: 'Germany', founded: new Date(1948, 7, 18), logoUrl: 'https://upload.wikimedia.org/wikipedia/en/thumb/d/da/Puma_complete_logo.svg/330px-Puma_complete_logo.svg.png' }
];