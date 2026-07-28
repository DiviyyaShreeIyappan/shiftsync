 'use client'

 import {useState} from 'react';
 import styles from './Header.module.css';

 export default function Header(){
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