'use client'
import { useState, useEffect } from 'react'
import styles from './LeaveRequests.module.css'

interface LeaveRequest {
  id: string
  staff: { name: string }
  startDate: string
  endDate: string
  reason: string
  status: string
  approvedBy: { name: string } | null
}

export default function LeaveRequests() {
  const [leaveRequests, setLeaveRequests] = useState<LeaveRequest[]>([])
  const [loading, setLoading] = useState(true)

  const fetchLeave = () => {
    fetch('http://localhost:8080/api/leave')
      .then(res => res.json())
      .then(data => {
        setLeaveRequests(data)
        setLoading(false)
      })
      .catch(() => setLoading(false))
  }

  useEffect(() => {
    fetchLeave()
  }, [])

  const handleApprove = async (id: string) => {
    await fetch(`http://localhost:8080/api/leave/${id}/approve?managerId=790cd062-23d3-49a4-b0c4-665a34a812b2`, {
      method: 'PATCH'
    })
    fetchLeave()
  }

  const handleDecline = async (id: string) => {
    await fetch(`http://localhost:8080/api/leave/${id}/decline`, {
      method: 'PATCH'
    })
    fetchLeave()
  }

  if (loading) return <div>Loading...</div>

  const pending = leaveRequests.filter(r => r.status === 'PENDING')
  const approved = leaveRequests.filter(r => r.status === 'APPROVED')

  return (
    <div className={styles.wrapper}>
      <h2 className={styles.title}>Leave Requests</h2>

      <h4 className={styles.sectionTitle}>Pending ({pending.length})</h4>
      {pending.map(request => (
        <div key={request.id} className={styles.card}>
          <div className={styles.cardHeader}>
            <span className={styles.name}>{request.staff.name}</span>
            <span className={styles.badge}>Pending</span>
          </div>
          <p className={styles.dates}>{request.startDate} - {request.endDate}</p>
          <p className={styles.reason}>{request.reason}</p>
          <div className={styles.actions}>
            <button className={styles.approveBtn} onClick={() => handleApprove(request.id)}>
              Approve
            </button>
            <button className={styles.declineBtn} onClick={() => handleDecline(request.id)}>
              Decline
            </button>
          </div>
        </div>
      ))}

      <h4 className={styles.sectionTitle}>Approved ({approved.length})</h4>
      {approved.length === 0 && (
        <p className={styles.empty}>No approved leave yet</p>
      )}
      {approved.map(request => (
        <div key={request.id} className={`${styles.card} ${styles.approvedCard}`}>
          <div className={styles.cardHeader}>
            <span className={styles.name}>{request.staff.name}</span>
            <span className={styles.approvedBadge}>Approved</span>
          </div>
          <p className={styles.dates}>{request.startDate} - {request.endDate}</p>
          <p className={styles.reason}>{request.reason}</p>
          <p className={styles.approvedBy}>✓ Approved by {request.approvedBy?.name}</p>
        </div>
      ))}
    </div>
  )
}