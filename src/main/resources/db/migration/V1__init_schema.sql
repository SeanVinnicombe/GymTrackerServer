-- ==================== SEQUENCES ====================

CREATE SEQUENCE public.users_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.exercise_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.program_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.program_week_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.program_day_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.program_day_exercise_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.exercise_session_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE public.set_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;

-- ==================== TABLES ====================

CREATE TABLE public.users
(
    id           int8         NOT NULL DEFAULT nextval('users_seq'),
    first_name   varchar(255) NOT NULL,
    last_name    varchar(255) NOT NULL,
    phone_number varchar(255) NULL,
    email        varchar(255) NOT NULL,
    password     varchar(255) NOT NULL,
    role         varchar(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email_unique UNIQUE (email)
);

CREATE TABLE public.exercise
(
    id           int8         NOT NULL DEFAULT nextval('exercise_seq'),
    name         varchar(255) NOT NULL,
    muscle_group varchar(255) NULL,
    CONSTRAINT exercise_pkey PRIMARY KEY (id),
    CONSTRAINT exercise_name_unique UNIQUE (name)
);

CREATE TABLE public.program
(
    id             int8         NOT NULL DEFAULT nextval('program_seq'),
    name           varchar(255) NULL,
    program_length int4         NULL,
    user_id        int8         NOT NULL,
    CONSTRAINT program_pkey PRIMARY KEY (id),
    CONSTRAINT program_users_fk FOREIGN KEY (user_id) REFERENCES public.users (id)
);

CREATE TABLE public.program_week
(
    id          int8 NOT NULL DEFAULT nextval('program_week_seq'),
    week_number int4 NOT NULL,
    program_id  int8 NOT NULL,
    CONSTRAINT program_week_pkey PRIMARY KEY (id),
    CONSTRAINT program_week_program_fk FOREIGN KEY (program_id) REFERENCES public.program (id)
);

CREATE TABLE public.program_day
(
    id              int8         NOT NULL DEFAULT nextval('program_day_seq'),
    muscle_group    varchar(255) NOT NULL,
    program_week_id int8         NOT NULL,
    CONSTRAINT program_day_pkey PRIMARY KEY (id),
    CONSTRAINT program_day_program_week_fk FOREIGN KEY (program_week_id) REFERENCES public.program_week (id)
);

CREATE TABLE public.program_day_exercise
(
    id              int8 NOT NULL DEFAULT nextval('program_day_exercise_seq'),
    exercise_number int4 NULL,
    target_reps     int4 NULL,
    target_sets     int4 NULL,
    exercise_id     int8 NOT NULL,
    program_day_id  int8 NOT NULL,
    CONSTRAINT program_day_exercise_pkey PRIMARY KEY (id),
    CONSTRAINT program_day_exercise_exercise_fk FOREIGN KEY (exercise_id) REFERENCES public.exercise (id),
    CONSTRAINT program_day_exercise_program_day_fk FOREIGN KEY (program_day_id) REFERENCES public.program_day (id)
);

CREATE TABLE public.exercise_session
(
    id                      int8 NOT NULL DEFAULT nextval('exercise_session_seq'),
    notes                   varchar(255) NULL,
    week_number             int4 NULL,
    program_day_exercise_id int8 NULL,
    CONSTRAINT exercise_session_pkey PRIMARY KEY (id),
    CONSTRAINT exercise_session_program_day_exercise_fk FOREIGN KEY (program_day_exercise_id) REFERENCES public.program_day_exercise (id)
);

CREATE TABLE public.set
(
    id                  int8 NOT NULL DEFAULT nextval('set_seq'),
    set_order           int4 NULL,
    achieved_reps       int4 NULL,
    weight_done         int4 NULL,
    exercise_session_id int8 NULL,
    CONSTRAINT set_pkey PRIMARY KEY (id),
    CONSTRAINT set_exercise_session_fk FOREIGN KEY (exercise_session_id) REFERENCES public.exercise_session (id)
);