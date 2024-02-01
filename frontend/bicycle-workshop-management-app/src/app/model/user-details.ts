export interface UserDetails {
    id: string;
    firstName: string;
    lastName: string;
    email: string;
    createdAt: Date;
    workshopAssignedAt: Date;
    positions: string[];
  }