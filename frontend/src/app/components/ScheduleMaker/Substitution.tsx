import styles from './Substitution.module.css'

const mockSubstitutions = [
  {
    id: '1',
    absentStaff: 'John Smith',
    department: 'DELI',
    date: 'Mon 28 Jul',
    time: '06:00 - 14:00',
    candidate: 'Maria Lee',
    rank: 1,
    status: 'PENDING'
  },
  {
    id: '2',
    absentStaff: 'Tom Ryan',
    department: 'PRODUCTION',
    date: 'Fri 25 Jul',
    time: '12:00 - 19:00',
    candidate: 'Sarah Kim',
    rank: 1,
    status: 'CONFIRMED'
  }
]

export default function Substitution(){
    const active = mockSubstitutions.filter(r=> r.status === 'PENDING')
    const resolved= mockSubstitutions.filter(r=> r.status ==='CONFIRMED')
    return(
        <div className={styles.wrapper}>
            <h2 className={styles.title}>Substitution Status</h2>
            <h4 className={styles.sectionTitle}>Active ({active.length})</h4>
            {active.map((sub)=>(
                <div key={sub.id} className={styles.card}>
                <span className={styles.name}>{sub.absentStaff} absent - {sub.department}</span>
                <span className={styles.date}>{sub.date} : {sub.time}</span>
                <span className={styles.sub}>Status: {sub.candidate} waiting for response</span>
                </div>
                ))}
            <h4 className={styles.sectionTitle}>Resolved ({resolved.length})</h4>
            {resolved.map((sub)=>(
                <div key={sub.id} className={`${styles.card} ${styles.resolvedCard}`}>
                                <span className={styles.name}>{sub.absentStaff} absent - {sub.department}</span>
                                <span className={styles.date}>{sub.date} : {sub.time}</span>
                                <span className={styles.sub}>Covered by: {sub.candidate}</span>
                                </div>
                ))}
        </div>
        );
    }