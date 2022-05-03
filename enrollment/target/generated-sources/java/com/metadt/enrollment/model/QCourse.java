package com.metadt.enrollment.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = -886271554L;

    public static final QCourse course = new QCourse("course");

    public final StringPath code = createString("code");

    public final NumberPath<Integer> credits = createNumber("credits", Integer.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public QCourse(String variable) {
        super(Course.class, forVariable(variable));
    }

    public QCourse(Path<? extends Course> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCourse(PathMetadata metadata) {
        super(Course.class, metadata);
    }

}

