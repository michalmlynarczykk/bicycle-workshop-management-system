export interface Order {
    id: string;
    createdAt: Date;
    collectedAt: Date;
    status: string;
    client: {
        firstName: string;
        lastName: string;
        email: string;
    };
    bike: {
        brand: string;
        model: string;
        color: string;
        frameNumber: string;
        productionYear: number;
        description: string;
    };
}


export interface ExtendedOrder {
    id: string;
    createdAt: Date;
    collectedAt: Date;
    status: string;
    client: {
        firstName: string;
        lastName: string;
        email: string;
    };
    bike: {
        brand: string;
        model: string;
        color: string;
        frameNumber: string;
        productionYear: number;
        description: string;
    };
    services: Service[];
    parts: Part[];
}

export interface Service {
    name: string;
    description: string;
    price: number;
    quantity: number;
}

export interface Part {
    name: string;
    description: string;
    price: number;
    quantity: number;
}