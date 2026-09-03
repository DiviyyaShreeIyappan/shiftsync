import styles from './ConflictsTab.module.css';

const mockConflicts=[
    {
        id:'1',
        staffName:'John Smith',
        department: 'DELI',
        date: 'Mon 28 Jul',
        time: '06:00 - 14:00',
        manager1: 'Sarah (Main Manager)',
        manager2: 'Mike (Subway Manager)',
        }
    ]

export default function ConflictsTab(){
    return(
        <div className={styles.wrapper}>
            <h3 className={styles.title}>Active Conflicts</h3>
            {mockConflicts.map((conflict)=>(
                <div key={conflict.id} className={styles.card}>
                    <div className={styles.cardHeader}>
                        <span className={styles.staffName}>{conflict.staffName}</span>
                        <span className={styles.department}>{conflict.department}</span>
                    </div>
                <div className={styles.dateInfo}>
                        <p className={styles.dateTime}>{conflict.date} · {conflict.time}</p>
                        <p className={styles.manager}> {conflict.manager1}</p>
                        <p className={styles.manager}> {conflict.manager2}</p>
                </div>
                <div className={styles.actions}>
                            <button className={styles.resolveBtn}>
                              Keep {conflict.manager1.split(' ')[0]}'s
                            </button>
                            <button className={styles.resolveBtn}>
                              Keep {conflict.manager2.split(' ')[0]}'s
                            </button>
                          </div>
                </div>
                ))}
            {mockConflicts.length === 0 && (
                    <p className={styles.empty}>No active conflicts ✓</p>
                  )}
        </div>
        );

    }