export interface WorkshopJoinRequest {
    id: string;
    createdAt: Date;
    decidedAt: Date;
    workshopId: string;
    userId: string;
    status: string;
  }