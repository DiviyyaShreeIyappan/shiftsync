 'use client'

 import {useState} from 'react';
 import styles from './Header.module.css';

 interface HeaderProps {
  onMenuClick: () => void
  isSidebarOpen: boolean
}
 export default function Header({onMenuClick,isSidebarOpen}:HeaderProps){
    const [isDark, setIsDark]=useState(false);

    const toggleTheme=()=>{
        setIsDark(!isDark)
        document.documentElement.setAttribute(
            'data-theme',
             isDark ? 'light' :'dark'
        )
    }
    return (
        <header className={styles.header}>
            {/* Hamburger — mobile only */}
<button
  className={styles.hamburger}
  onClick={onMenuClick}
  aria-label="Toggle menu"
>
  {isSidebarOpen ? '✕' : '☰'}
</button>
            <div className={styles.logo}>
                <span className={styles.logoText}>ShiftSync</span>
                <span className={styles.badge}>Manager</span>
                </div>
             <div className={styles.controls}>
        <span className={styles.managerName}>Sarah Manager</span>
         <button className={styles.themeToggle} onClick={toggleTheme}>
          {isDark ? '☀️ Light' : '🌙 Dark'}
        </button>

        <button className={styles.logout}>
          Logout
        </button>
        </div>
            </header>
    )
 }