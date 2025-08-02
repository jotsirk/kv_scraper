export interface PropertyTick {
  id: number,
  propertyKey: string,
  imageUrl: string,
  isFinished: boolean,
  isReserved: boolean,
  logs: PropertyLog[],
}

export interface PropertyLog {
  id: number,
  price: number,
  createdAt: string,
}
