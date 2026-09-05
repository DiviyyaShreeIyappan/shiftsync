'use client'

import {useState} from 'react'
import styles from './CalenderView.module.css'

const mockEvents = [
  {
    id: '1',
    staffName: 'John',
    type: 'LEAVE',
    startDate: 14,
    endDate: 18,
    month: 7
  },
  {
    id: '2',
    staffName: 'Maria',
    type: 'UNAVAILABLE',
    startDate: 20,
    endDate: 22,
    month: 7
  },
  {
    id: '3',
    staffName: 'Tom',
    type: 'LEAVE',
    startDate: 25,
    endDate: 27,
    month: 7
  }
]

const DAYS = ['MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN']
const MONTHS = [
  'January', 'February', 'March', 'April', 'May', 'June',
  'July', 'August', 'September', 'October', 'November', 'December'
]
export default function CalenderView(){
    const [currentMonth, setCurrentMonth]= useState(6)
    const [currentYear, setCurrentYear]= useState(2026)

    const daysInMonth= new Date(currentYear, currentMonth +1, 0).getDate()
    const firstDayOfMonth = new Date(currentYear, currentMonth, 1).getDay()
    const startOffset = firstDayOfMonth === 0 ? 6 : firstDayOfMonth - 1

    const getEventsForDay = (day: number) => {
        return mockEvents.filter(
          event =>
            event.month === currentMonth + 1 &&
            day >= event.startDate &&
            day <= event.endDate
        )
      }
   const handlePrev = () => {
      if (currentMonth === 0) {
        setCurrentMonth(11)
        setCurrentYear(currentYear - 1)
      } else {
        setCurrentMonth(currentMonth - 1)
      }
    }

    const handleNext = () => {
      if (currentMonth === 11) {
        setCurrentMonth(0)
        setCurrentYear(currentYear + 1)
      } else {
        setCurrentMonth(currentMonth + 1)
      }
    }

const cells = [
    ...Array(startOffset).fill(null),
    ...Array.from({ length: daysInMonth }, (_, i) => i + 1)
  ]

  return (
    <div className={styles.wrapper}>

      {/* header */}
      <div className={styles.header}>
        <button className={styles.navBtn} onClick={handlePrev}>◀</button>
        <h2 className={styles.monthTitle}>
          {MONTHS[currentMonth]} {currentYear}
        </h2>
        <button className={styles.navBtn} onClick={handleNext}>▶</button>
      </div>

      {/* day name headers */}
      <div className={styles.dayHeaders}>
        {DAYS.map(day => (
          <div key={day} className={styles.dayName}>{day}</div>
        ))}
      </div>

      {/* calendar grid */}
      <div className={styles.grid}>
        {cells.map((day, index) => {
          const events = day ? getEventsForDay(day) : []
          return (
            <div
              key={index}
              className={`${styles.cell} ${day ? '' : styles.empty}`}
            >
              {day && <span className={styles.dayNumber}>{day}</span>}
              {events.map(event => (
                <span
                  key={event.id}
                  className={`${styles.badge} ${event.type === 'LEAVE' ? styles.leave : styles.unavailable}`}
                >
                  {event.staffName}
                </span>
              ))}
            </div>
          )
        })}
      </div>

      {/* legend */}
      <div className={styles.legend}>
        <span className={`${styles.legendItem} ${styles.leave}`}>● Leave</span>
        <span className={`${styles.legendItem} ${styles.unavailable}`}>● Unavailable</span>
      </div>

    </div>
  )
    }