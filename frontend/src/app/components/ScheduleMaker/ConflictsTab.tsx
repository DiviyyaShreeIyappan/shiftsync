'use client'
import {useState, useEffect} from 'react'
import styles from './ConflictsTab.module.css';

interface Conflict {
  id: string
  staff: { name: string }
  assignmentId1: { department: string, date: string, startTime: string, endTime: string, assignedBy: { name: string } }
  assignmentId2: { assignedBy: { name: string } }
  detectedAt: string
  resolvedAt: string
}

export default function ConflictsTab(){
    const [conflicts, setConflicts] = useState<Conflict[]>([])
    const [loading, setLoading] = useState(true)

    useEffect(() => {
      fetch('http://localhost:8080/api/conflicts/unresolved')
        .then(res => res.json())
        .then(data => {
          console.log('Conflict data:', data)  // add this
          setConflicts(data)
          setLoading(false)
        })
        .catch((err) => {
          console.error('Error:', err)  // add this
          setLoading(false)
        })
    }, [])
    return(
        <div className={styles.wrapper}>
            <h3 className={styles.title}>Active Conflicts</h3>
            {conflicts.map((conflict)=>(
                <div key={conflict.id} className={styles.card}>
                    <div className={styles.cardHeader}>
                        <span className={styles.staffName}>{conflict.staff.name}</span>
                        <span className={styles.department}>{conflict.assignmentId1.department}</span>
                    </div>
                <div className={styles.dateInfo}>
                        <p className={styles.dateTime}>{conflict.assignmentId1.date} · {conflict.assignmentId1.startTime} - {conflict.assignmentId1.endTime}</p>
                        <p className={styles.manager}> {conflict.assignmentId1.assignedBy.name}</p>
                        <p className={styles.manager}> {conflict.assignmentId2.assignedBy.name}</p>
                </div>
                <div className={styles.actions}>
                            <button className={styles.resolveBtn}>
                              Keep {conflict.assignmentId1.assignedBy.name.split(' ')[0]}'s
                            </button>
                            <button className={styles.resolveBtn}>
                              Keep {conflict.assignmentId2.assignedBy.name.split(' ')[0]}'s
                            </button>
                          </div>
                </div>
                ))}
            {conflicts.length === 0 && (
                    <p className={styles.empty}>No active conflicts ✓</p>
                  )}
        </div>
        );

    }