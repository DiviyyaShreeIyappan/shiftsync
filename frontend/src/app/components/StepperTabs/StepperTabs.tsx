'use client'

import { useState } from 'react';
import styles from './StepperTabs.module.css';

export default function StepperTabs(){
    const [activeTab,setActiveTab] =useState('schedule');
    const tabs=['Schedule','Conflicts','Review','Post']
    return(
        <div className={styles.stepperTabs}>
            {tabs.map((tab)=>(
                <div
        key={tab}
        className={`${styles.tab} ${activeTab === tab ? styles.active : ''}`}
        onClick={() => setActiveTab(tab)}
      >
        {tab}
      </div>
            ))
            }
        </div>
       
    )
}