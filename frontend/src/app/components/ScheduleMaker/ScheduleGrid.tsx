'use client'

import { useState } from 'react'
import styles from './ScheduleGrid.module.css'

const departments = ['DELI', 'PRODUCTION', 'SHOPFLOOR', 'SUBWAY', 'SALADS']
const days = ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT']

const deptColors: Record<string, string> = {
  DELI: '#E8604C',
  PRODUCTION: '#D4763B',
  SHOPFLOOR: '#7A9E3B',
  SUBWAY: '#9B6B9E',
  SALADS: '#F5C518',
}

export default function ScheduleGrid() {
  const [schedule, setSchedule] = useState<Record<string, string>>({})

  const handleDrop = (e: React.DragEvent, dept: string, day: string) => {
    e.preventDefault()
    const staffName = e.dataTransfer.getData('staffName')
    const cellKey = `${dept}-${day}`
    setSchedule(prev => ({ ...prev, [cellKey]: staffName }))
  }

   const handleRemove = (cellKey: string) => {
     setSchedule(prev => {
       const updated = { ...prev }
       delete updated[cellKey]
       return updated
     })
   }
  return (
    <div className={styles.wrapper}>
    <div className={styles.grid}>
    <div className={styles.gridInner}>
      {/* header row */}
      <div className={styles.headerRow}>
        <div className={styles.deptHeader}></div>
        {days.map(day => (
          <div key={day} className={styles.dayHeader}>{day}</div>
        ))}
      </div>

      {/* department rows */}
      {departments.map(dept => (
        <div key={dept} className={styles.row}>
          <div
            className={styles.deptLabel}
            style={{ borderLeft: `3px solid ${deptColors[dept]}` }}
          >
            {dept}
          </div>

          {days.map(day => {
            const cellKey = `${dept}-${day}`
            const assigned = schedule[cellKey]

            return (
              <div
                key={cellKey}
                className={`${styles.cell} ${assigned ? styles.filled : styles.empty}`}
                onDragOver={(e) => e.preventDefault()}
                onDrop={(e) => handleDrop(e, dept, day)}
              >
                {assigned ? (
                  <div className={styles.chipWrapper}>
                    <span
                      className={styles.assignedChip}
                      style={{ backgroundColor: deptColors[dept] + '33' }}
                    >
                      {assigned}
                    </span>
                    <button
                      className={styles.removeBtn}
                      onClick={() => handleRemove(cellKey)}
                    >
                      ✕
                    </button>
                  </div>
                ) : (
                  <span className={styles.placeholder}>+</span>
                )}
              </div>
            )
          })}
        </div>
      ))}
</div>
    </div>
    <div className={styles.actions}>
          <button className={styles.generateBtn}>
             Generate Schedule
          </button>
        </div>
    </div>
  )
}