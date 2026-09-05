'use client'



import styles from './Sidebar.module.css';
import LeaveRequests from '../ScheduleMaker/LeaveRequests';
import Substitution from '../ScheduleMaker/Substitution';
 const sidebarItems=['Schedule','Leave Requests','Calender','Substitution','Payroll'];
     interface SidebarProps {
  activeSection: string
  onSectionChange: (section: string) => void
}
interface SidebarProps {
  activeSection: string
  onSectionChange: (section: string) => void
  isOpen: boolean
}
export default function Sidebar({ activeSection, onSectionChange,isOpen }: SidebarProps) {
  return (
    <aside className={`${styles.sidebar} ${isOpen ? styles.open : ''}`} role="navigation" aria-label="Main navigation">
      {sidebarItems.map((item) => (
        <button
          key={item}
          className={`${styles.navItem} ${activeSection === item ? styles.active : ''}`}
          onClick={() => onSectionChange(item)}
          aria-current={activeSection === item ? 'page' : undefined}
           onKeyDown={(e) => {
    if (e.key === 'Enter' || e.key === ' ') {
      onSectionChange(item)
    }
  }}    
        >
          {item}
        </button>
      ))}
    </aside>
  )
}