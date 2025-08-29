CREATE TABLE roles (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre      VARCHAR(100) NOT NULL,
    titulo      VARCHAR(100) NOT NULL
);
CREATE INDEX idx_roles_nombre ON roles(nombre);

CREATE TABLE clients (
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    identificacion      VARCHAR(50) UNIQUE NOT NULL,
    nombres             VARCHAR(150) NOT NULL,
    apellidos           VARCHAR(150) NOT NULL,
    fecha_nacimiento    DATE NOT NULL,
    direccion           VARCHAR(255),
    telefono            VARCHAR(20),
    salario_base        NUMERIC(20,2) DEFAULT 0,
    correo_electronico  VARCHAR(150) UNIQUE NOT NULL,
    password            VARCHAR(255) NOT NULL,
    role_id             UUID,
    CONSTRAINT fk_clients_roles FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE SET NULL
);

-- Indexes for better performance
CREATE INDEX idx_clients_identificacion ON clients(identificacion);
CREATE INDEX idx_clients_correo ON clients(correo_electronico);
CREATE INDEX idx_clients_role_id ON clients(role_id);
