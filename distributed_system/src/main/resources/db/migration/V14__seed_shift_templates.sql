INSERT INTO shift_templates (department, start_time, end_time, staff_required, applicable_days, active)
VALUES
    ('DELI', '06:00', '14:00', 1, 'MON,TUE,WED,THU,FRI', true),
    ('DELI', '08:00', '15:00', 1, 'MON,TUE,WED,THU,FRI', true),
    ('PRODUCTION', '12:00', '19:00', 3, 'MON,TUE,WED,THU,FRI', true),
    ('PRODUCTION', '09:00', '17:00', 3, 'SAT', true),
    ('PRODUCTION', '09:00', '17:00', 3, 'SUN', true),
    ('SHOPFLOOR', '07:00', '15:00', 1, 'MON,TUE,WED,THU,FRI', true),
    ('SHOPFLOOR', '12:00', '20:00', 1, 'MON,TUE,WED,THU,FRI', true),
    ('SHOPFLOOR', '09:00', '17:00', 2, 'SAT', true),
    ('SHOPFLOOR', '09:00', '17:00', 2, 'SUN', true),
    ('SUBWAY', '08:00', '14:00', 2, 'MON,TUE,WED,THU,FRI', true),
    ('SUBWAY', '14:00', '20:00', 2, 'MON,TUE,WED,THU,FRI', true),
    ('SUBWAY', '09:00', '17:00', 2, 'SAT', true),
    ('SUBWAY', '10:00', '17:00', 2, 'SUN', true),
    ('SALADS', '07:00', '15:00', 1, 'MON,TUE,WED,THU,FRI', true),
    ('SALADS', '09:00', '17:00', 1, 'SAT', true);