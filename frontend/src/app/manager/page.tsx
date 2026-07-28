'use client'
import { useState } from 'react';
import Header from '../components/Header/Header';
import Sidebar from '../components/Sidebar/Sidebar';
import styles from './page.module.css'

export default function ManagerDashboard() {
  const [activeSection, setActiveSection] = useState('Schedule');
  return (
    <div className={styles.page}>
      <Header />
     <div className={styles.body}>
        <Sidebar
          activeSection={activeSection}
          onSectionChange={setActiveSection}
        />
        <main className={styles.main}>
          {activeSection === 'Schedule' && <div>Schedule content here</div>}
          {activeSection === 'Leave Requests' && <div>Leave Requests here</div>}
          {activeSection === 'Calender' && <div>Calendar here</div>}
          {activeSection === 'Substitution' && <div>Substitution here</div>}
          {activeSection === 'Payroll' && <div>Payroll here</div>}
        </main>
      </div>
    </div>
  )
}