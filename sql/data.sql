INSERT INTO roles (nombre, titulo, u_creacion, u_actualizacion, f_creacion, f_actualizacion)
VALUES
    ('ADMIN',   'Administrador', 'system', NULL, NOW(), NULL),
    ('CLIENTE', 'Cliente',       'system', NULL, NOW(), NULL),
    ('ASESOR',  'Asesor',        'system', NULL, NOW(), NULL);