'use client'

import {useState, useEffect} from 'react'
import styles from './StaffPanel.module.css';

interface Staff{
    id: string
    name: string
    role: string
    contractType: string
}
const deptColors: Record<string, string> = {
  DELI: '#E8604C',
  PRODUCTION: '#D4763B',
  SHOPFLOOR: '#7A9E3B',
  SUBWAY: '#9B6B9E',
  SALADS: '#F5C518',
}
export default function StaffPanel(){
const [staff, setStaff] = useState<Record<string, Staff[]>>({})
const [loading, setLoading] = useState(true)

useEffect(() => {
  fetch('http://localhost:8080/api/staff/by-department')
    .then(res => res.json())
    .then(data => {
      console.log('Staff data:', data)  // add this
      setStaff(data)
      setLoading(false)
    })
    .catch((err) => {
      console.error('Error:', err)  // add this
      setLoading(false)
    })
}, [])

if(loading) return <div className={styles.container}>Loading staff...</div>
return (
  <div className={styles.container}>
    <h4 className={styles.title}>Staff</h4>
    {Object.entries(staff).map(([dept, members]) => {
        console.log('dept:', dept, 'color:', deptColors[dept])
        return(
             <div key={dept} className={styles.department}>
                    <p className={styles.deptLabel} style={{ color: deptColors[dept] }}>
                      {dept}
                    </p>
                    <div className={styles.chips}>
                      {members.map((member) => (
                        <div
                          key={member.id}
                          className={styles.chip}
                          style={{
                            backgroundColor: deptColors[dept] + '44',
                            borderColor: deptColors[dept]
                          }}
                          draggable
                          onDragStart={(e) => e.dataTransfer.setData('staffName', member.name)}
                        >
                          {member.name}
                        </div>
            ))}


        </div>
      </div>
      )
    })}
  </div>
)
    }