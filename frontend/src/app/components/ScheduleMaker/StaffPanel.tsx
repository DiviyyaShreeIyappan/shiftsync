import styles from './StaffPanel.module.css';



const staffByDepartment = [
  {
    department: 'DELI',
    color: '#E8604C',
    staff: ['John', 'Maria']
  },
  {
    department: 'PRODUCTION',
    color: '#D4763B',
    staff: ['Sarah', 'Tom']
  },
  {
    department: 'SHOPFLOOR',
    color: '#7A9E3B',
    staff: ['Lisa']
  },
  {
    department: 'SUBWAY',
    color: '#9B6B9E',
    staff: ['Mike', 'Anna']
  },
  {
    department: 'SALADS',
    color: '#F5C518',
    staff: ['Emma']
  },
]
export default function StaffPanel(){

return (
    <div className={styles.container}>
        <h4 className={styles.title}>Staff</h4>
        {staffByDepartment.map((dept)=>(
            <div key={dept.department} className={styles.department}>
            <p className={styles.deptLabel} style={{color:dept.color}}>
            {dept.department}
            </p>
            <div className={styles.chips}>
            {dept.staff.map((name)=>(
                <div key={name} className={styles.chip} style={{backgroundColor: dept.color +'22',borderColor: dept.color }} draggable onDragStart={(e) => e.dataTransfer.setData('staffName',name)}>
                {name}
                </div>
                ))}
            </div>
            </div>
            ))}

    </div>
    )
    }