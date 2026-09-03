'use client'

import { useState } from 'react';
import styles from './StepperTabs.module.css';
import ScheduleMaker from '../ScheduleMaker/ScheduleMaker';
import ConflictsTab from '../ScheduleMaker/ConflictsTab';

export default function StepperTabs(){
    const [activeTab,setActiveTab] =useState('schedule');
    const tabs=['Schedule','Conflicts','Review','Post']
    return(
        <div className={styles.wrapper}>
         <div className={styles.tabs}>
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
                 <div className={styles.content}>
                                 {activeTab === 'Schedule' && <div><ScheduleMaker /></div>}
                                 {activeTab === 'Conflicts' && <div><ConflictsTab /></div>}
                                 {activeTab === 'Review' && <div>Review Content</div>}
                                 {activeTab === 'Post' && <div>Post content</div>}
                                 </div>
        </div>
       
    )
}