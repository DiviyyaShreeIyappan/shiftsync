'use client'



import styles from './Sidebar.module.css';

 const sidebarItems=['Schedule','Leave Requests','Calender','Substitution','Payroll'];
     interface SidebarProps {
  activeSection: string
  onSectionChange: (section: string) => void
}
export default function Sidebar({ activeSection, onSectionChange }: SidebarProps) {
  return (
    <aside className={styles.sidebar} role="navigation" aria-label="Main navigation">
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