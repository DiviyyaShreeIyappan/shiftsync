import styles from './LeaveRequests.module.css'

const mockLeaveRequests = [
  {
    id: '1',
    staffName: 'John Smith',
    startDate: '14 Aug',
    endDate: '18 Aug',
    days: 5,
    reason: 'Family holiday',
    status: 'PENDING',
    approvedBy: ''
  },
  {
    id: '2',
    staffName: 'Maria Lee',
    startDate: '20 Aug',
    endDate: '22 Aug',
    days: 3,
    reason: 'Medical appointment',
    status: 'APPROVED',
    approvedBy: 'Sarah'
  }
]

export default function LeaveRequests() {
  const pending = mockLeaveRequests.filter(r => r.status === 'PENDING')
  const approved = mockLeaveRequests.filter(r => r.status === 'APPROVED')

  return (
    <div className={styles.wrapper}>
      <h2 className={styles.title}>Leave Requests</h2>

      {/* pending section */}
      <h4 className={styles.sectionTitle}>Pending ({pending.length})</h4>
      {pending.map(request => (
        <div key={request.id} className={styles.card}>
          <div className={styles.cardHeader}>
            <span className={styles.name}>{request.staffName}</span>
            <span className={styles.badge}>Pending</span>
          </div>
          <p className={styles.dates}>
            {request.startDate} - {request.endDate} · {request.days} days
          </p>
          <p className={styles.reason}>{request.reason}</p>
          <div className={styles.actions}>
            <button className={styles.approveBtn}>Approve</button>
            <button className={styles.declineBtn}>Decline</button>
          </div>
        </div>
      ))}

      {/* approved section */}
      <h4 className={styles.sectionTitle}>Approved ({approved.length})</h4>
      {approved.map(request => (
        <div key={request.id} className={`${styles.card} ${styles.approvedCard}`}>
          <div className={styles.cardHeader}>
            <span className={styles.name}>{request.staffName}</span>
            <span className={styles.approvedBadge}>Approved</span>
          </div>
          <p className={styles.dates}>
            {request.startDate} - {request.endDate} · {request.days} days
          </p>
          <p className={styles.reason}>{request.reason}</p>
          <p className={styles.approvedBy}>✓ Approved by {request.approvedBy}</p>
        </div>
      ))}
    </div>
  )
}