'use client'
import { useState } from 'react';
import Header from '../components/Header/Header';
import Sidebar from '../components/Sidebar/Sidebar';
import StepperTabs from '../components/StepperTabs/StepperTabs';
import LeaveRequests from '../components/ScheduleMaker/LeaveRequests';
import Substitution from '../components/ScheduleMaker/Substitution';
import PayrollHistory from '../components/ScheduleMaker/PayrollHistory';
import CalenderView from '../components/CalenderView';
import styles from './page.module.css'

export default function ManagerDashboard() {
  const [activeSection, setActiveSection] = useState('Schedule');
  const [isSideBarOpen, setIsSideBarOpen]=useState(false);
  return (
    <div className={styles.page}>
      <Header 
        onMenuClick={() => setIsSideBarOpen(!isSideBarOpen)}
        isSidebarOpen={isSideBarOpen}
      />
     <div className={styles.body}>
        {isSideBarOpen && (
          <div
            className={styles.overlay}
            onClick={() => setIsSideBarOpen(false)}
          />
        )}
        <Sidebar
          activeSection={activeSection}
          onSectionChange={(section) => {
            setActiveSection(section)
            setIsSideBarOpen(false)
          }}
          isOpen={isSideBarOpen}
        />
        <main className={styles.main}>
          {activeSection === 'Schedule' && <div><StepperTabs /></div>}
          {activeSection === 'Leave Requests' && <div><LeaveRequests /></div>}
          {activeSection === 'Calender' && <div><CalenderView /></div>}
          {activeSection === 'Substitution' && <div><Substitution /></div>}
          {activeSection === 'Payroll' && <div><PayrollHistory /></div>}
        </main>
      </div>
    </div>
  )
}