import styles from './PayrollHistory.module.css';

const mockPayrollHistory = [
  {
    id: '1',
    weekStart: '14 Jul',
    weekEnd: '20 Jul',
    status: 'SENT',
    staffCount: 8,
    shiftCount: 24
  },
  {
    id: '2',
    weekStart: '7 Jul',
    weekEnd: '13 Jul',
    status: 'SENT',
    staffCount: 7,
    shiftCount: 21
  },
  {
    id: '3',
    weekStart: '30 Jun',
    weekEnd: '6 Jul',
    status: 'SENT',
    staffCount: 9,
    shiftCount: 27
  },
  {
    id: '4',
    weekStart: '23 Jun',
    weekEnd: '29 Jun',
    status: 'SENT',
    staffCount: 8,
    shiftCount: 23
  }
]
export default function PayrollHistory(){
    return (
        <div className={styles.wrapper}>
         <h2 className={styles.title}>Payroll History</h2>
         <span className={styles.duration}>Past 4 weeks</span>
         {mockPayrollHistory.map((payroll)=>(
             <div key={payroll.id} className={styles.card}>
             <div className={styles.date}>Week of {payroll.weekStart} - {payroll.weekEnd}</div>
             <div className={styles.status}>✓ {payroll.status}</div>
             <div className={styles.stats}>Staff: {payroll.staffCount} · Shifts: {payroll.shiftCount}</div>
             <button className={styles.viewButton}>View Schedule</button>
             </div>
             ))}
        </div>

        );
    }