import styles from './ScheduleMaker.module.css';
import StaffPanel from './StaffPanel';
import ScheduleGrid from './ScheduleGrid';

export default function ScheduleMaker(){

    return(
        <div className={styles.container}>
            <StaffPanel />
                  <ScheduleGrid />
        </div>
        )
    }