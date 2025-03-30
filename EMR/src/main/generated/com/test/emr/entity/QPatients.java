package com.test.emr.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPatients is a Querydsl query type for Patients
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPatients extends EntityPathBase<Patients> {

    private static final long serialVersionUID = -1537192154L;

    public static final QPatients patients = new QPatients("patients");

    public final StringPath birthdate = createString("birthdate");

    public final StringPath contact = createString("contact");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath gender = createString("gender");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<MedicalRecord, QMedicalRecord> medicalRecords = this.<MedicalRecord, QMedicalRecord>createList("medicalRecords", MedicalRecord.class, QMedicalRecord.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QPatients(String variable) {
        super(Patients.class, forVariable(variable));
    }

    public QPatients(Path<? extends Patients> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPatients(PathMetadata metadata) {
        super(Patients.class, metadata);
    }

}

