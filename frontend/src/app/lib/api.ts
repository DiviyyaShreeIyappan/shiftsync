const BASE_URL ='http://localhost:8080'

export async function getAllStaff(){
    const response = await fetch(`${BASE_URL}/api/staff`)
    if(!response.ok) throw new Error('Failed to fetch staff')
    return response.json()
    }

export async function createAssignment(data: {
    eventId: string
      staffId: string
      department: string
      date: string
      startTime: string
      endTime: string
      assignedById: string
    }) {
        const response = await fetch(`${BASE_URL}/api/assignments`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
          })
          if (!response.ok) throw new Error('Failed to create assignment')
          return response.json()
        }

export async function getUnresolvedConflicts() {
  const response = await fetch(`${BASE_URL}/api/conflicts/unresolved`)
  if (!response.ok) throw new Error('Failed to fetch conflicts')
  return response.json()
}
export async function generateSchedule(
  department: string,
  weekStart: string,
  managerId: string
) {
  const response = await fetch(
    `${BASE_URL}/api/schedules/generate?department=${department}&weekStart=${weekStart}&managerId=${managerId}`,
    { method: 'POST' }
  )
  if (!response.ok) throw new Error('Failed to generate schedule')
  return response.json()
}
export async function getAllLeaveRequests() {
  const response = await fetch(`${BASE_URL}/api/unavailability/leave?date=2026-07-28`)
  if (!response.ok) throw new Error('Failed to fetch leave requests')
  return response.json()
}