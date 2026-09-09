export interface Staff {
  id: string
  name: string
  email: string
  role: string
  contractType: string
  contractedHours: number
  availableForExtraHours: boolean
  createdAt: string
}

export interface Assignment {
  id: string
  staff: Staff
  department: string
  date: string
  startTime: string
  endTime: string
  status: string
  assignedBy: Staff
  eventId: string
  createdAt: string
}

export interface Conflict {
  id: string
  staff: Staff
  assignmentId1: Assignment
  assignmentId2: Assignment
  detectedAt: string
  resolvedAt: string
  winningAssignment: Assignment
  resolvedBy: string
  resolutionReason: string
}