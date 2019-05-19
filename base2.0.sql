DROP DATABASE IF EXISTS spig;
CREATE DATABASE spig;

\c spig;

CREATE TABLE informador(
    correo TEXT NOT NULL PRIMARY KEY,
    nombre TEXT NOT NULL,
    contrasenia TEXT NOT NULL
);

CREATE TABLE tema(
    idtema SERIAL NOT NULL PRIMARY KEY,
    nombre TEXT NOT NULL UNIQUE,
    informador TEXT NOT NULL,
    CONSTRAINT fk_informador_tema FOREIGN KEY (informador) REFERENCES informador(correo) ON DELETE CASCADE
);

CREATE TABLE marcador(
    idmarcador SERIAL NOT NULL PRIMARY KEY,
    descripcion TEXT NOT NULL,
    datos_extra TEXT,
    longitud DOUBLE PRECISION NOT NULL,
    latitud DOUBLE PRECISION NOT NULL,
    tema INTEGER NOT NULL,
    CONSTRAINT fk_tema_marcador FOREIGN KEY (tema) REFERENCES tema (idtema) ON DELETE CASCADE
);

CREATE TABLE comentarista(
    correo TEXT NOT NULL PRIMARY KEY,
    nombre TEXT NOT NULL,
    contrasenia TEXT NOT NULL
);

CREATE TABLE comentario(
    idcomentario SERIAL NOT NULL PRIMARY KEY,
    contenido TEXT NOT NULL,
    calificacion DOUBLE PRECISION NOT NULL,
    comentarista TEXT NOT NULL,
    marcador INTEGER NOT NULL,
    CONSTRAINT fk_comentarista_comentario FOREIGN KEY (comentarista) REFERENCES comentarista (correo) ON DELETE CASCADE,
    CONSTRAINT fk_marcador_comentario FOREIGN KEY (marcador) REFERENCES marcador (idmarcador) ON DELETE CASCADE
);

CREATE TABLE califica(
    comentarista TEXT NOT NULL,
    comentario INTEGER NOT NULL,
    PRIMARY KEY (comentarista,comentario),
    CONSTRAINT fk_comentarista_califica FOREIGN KEY (comentarista) REFERENCES comentarista (correo) ON DELETE CASCADE,
    CONSTRAINT fk_comentario_califica FOREIGN KEY (comentario) REFERENCES comentario (idcomentario) ON DELETE CASCADE
);
