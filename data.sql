INSERT INTO users_table (username, role, password)
VALUES ('andrey', 'USER', '1'),
       ('ivan', 'USER', '1'),
       ('sergey', 'USER', '1'),
       ('storage', 'STORAGE_USER', '1'),
       ('fedor', 'USER', '1'),
       ('alex', 'USER', '1'),
       ('storage_sharpen', 'STORAGE_USER', '1'),
       ('admin', 'ADMIN', 'admin'),
       ('bad_tool', 'STORAGE_USER', '1');

INSERT INTO workers_table (id, first_name, last_name, patronymic, type, join_date, department, login)
VALUES (1, 'Андрей', 'Иванов', 'Иванович', 'WORKER', '2019-03-01', 'DEPARTMENT_19', 'andrey'),
       (2, 'Иван', 'Сергеев', 'Иванович', 'WORKER', '2022-05-01', 'DEPARTMENT_19', 'ivan'),
       (3, 'Сергей', 'Фирсов', 'Сергеевич', 'WORKER', '2011-06-01', 'DEPARTMENT_19', 'sergey'),
       (4, 'Кладовая', 'Кладовая', 'Кладовая', 'STORAGE_WORKER', '1900-03-01', 'DEPARTMENT_19', 'storage'),
       (5, 'Федор', 'Цаплин', 'Сергеевич', 'WORKER', '2015-04-01', 'DEPARTMENT_19', 'fedor'),
       (6, 'Алексей', 'Федоров', 'Сергеевич', 'WORKER', '2023-03-01', 'DEPARTMENT_19', 'alex'),
       (7, 'Заточка', 'Заточка', 'Заточка', 'STORAGE_WORKER', '1900-03-01', 'SHARPENING', 'storage_sharpen'),
       (8, 'ADMIN', 'ADMIN', 'ADMIN', 'ADMIN', '1900-03-01', 'DEPARTMENT_19', 'admin'),
       (9, 'Списание', 'Списание', 'Списание', 'STORAGE_WORKER', '1900-03-01', 'STORAGE_OF_DECOMMISSIONED_TOOLS',
        'bad_tool');
(10, 'Главный склад', 'Главный склад', 'Главный склад', 'STORAGE_WORKER', '1900-03-01', 'MAIN_STORAGE',
    'main_storage');

INSERT INTO tools_table (code, additional_info, description, icon, name, place_column, place_row, place_shelf, type)
VALUES ('2004-9060', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'CNMG120408', '1', '1', '1', 'CUTTING'),
       ('2004-1001', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'DNMG150608', '1', '1', '2', 'CUTTING'),
       ('2004-10111', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'VCMT160408', '1', '1', '3', 'CUTTING'),
       ('2004-7480', 'some info', 'some inner',
        'https://5.imimg.com/data5/SELLER/Default/2023/6/320127867/TQ/JD/GT/2304796/turning-inserts-500x500.jpg',
        'GRIP5025', '1', '1', '4', 'CUTTING'),
       ('8700-0001', 'some info', 'some measure tool',
        'https://www.beta-tools.com/media/catalog/category/x10_news_05.jpg', 'Measure tool #1', '2', '2', '2',
        'MEASURE'),
       ('8700-2001', 'some info', 'some measure tool',
        'https://www.beta-tools.com/media/catalog/category/x10_news_05.jpg', 'Measure tool #2', '2', '2', '3',
        'MEASURE'),
       ('8700-0987', 'some info', 'some measure tool',
        'https://www.beta-tools.com/media/catalog/category/x10_news_05.jpg', 'Measure tool #3', '2', '2', '4',
        'MEASURE'),
       ('6331-2222', 'some info', 'some helper tool',
        'https://ae04.alicdn.com/kf/S530fdd8cb379432a9732144100d99820w.jpg', 'Block for tools', '3', '3', '3',
        'HELPERS'),
       ('6075-1331', 'some info', 'some helper tool',
        'https://ae04.alicdn.com/kf/S530fdd8cb379432a9732144100d99820w.jpg', 'Center for machine', '3', '3', '3',
        'HELPERS'),
       ('6331-8065', 'some info', 'some helper tool',
        'https://ae04.alicdn.com/kf/S530fdd8cb379432a9732144100d99820w.jpg', 'Helper #1', '3', '3', '5', 'HELPERS');


INSERT INTO storage_records_table (amount, tool_code, worker_id)
VALUES (400, '2004-9060', 4),
       (500, '2004-1001', 4),
       (200, '2004-10111', 4),
       (1000, '2004-7480', 4),
       (20, '8700-0001', 4),
       (30, '8700-2001', 4),
       (25, '8700-0987', 4),
       (10, '6331-2222', 4),
       (15, '6075-1331', 4),
       (5, '6331-8065', 4);
