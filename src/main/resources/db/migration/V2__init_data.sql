-- ==================== users ====================

INSERT INTO public.users (id, first_name, last_name, phone_number, email, "password", "role")
VALUES (1, 'Sean', 'Vinnicombe', '0762005123', 'sv@gmail.com',
        '$2a$12$tABYZqBnm2YadkV2EDeqb.jSZpJHs9PsLOyHXoatWekA6Nccggpay', 'ADMIN');

-- ==================== exercise ====================


INSERT INTO public.exercise (id, "name", muscle_group)
VALUES (1, 'Bench Press', 'CHEST'),
       (2, 'Incline Dumbbell Press', 'CHEST'),
       (3, 'Chest Fly', 'CHEST'),
       (4, 'Cable Crossovers', 'CHEST'),
       (5, 'Decline Bench Press', 'CHEST'),
       (6, 'Pull Ups', 'BACK'),
       (7, 'Lat Pulldown', 'BACK'),
       (8, 'Barbell Row', 'BACK'),
       (9, 'Seated Cable Row', 'BACK'),
       (10, 'Deadlift', 'BACK');
INSERT INTO public.exercise (id, "name", muscle_group)
VALUES (11, 'Shoulder Press', 'SHOULDERS'),
       (12, 'Lateral Raises', 'SHOULDERS'),
       (13, 'Front Raises', 'SHOULDERS'),
       (14, 'Rear Delt Fly', 'SHOULDERS'),
       (15, 'Arnold Press', 'SHOULDERS'),
       (16, 'Barbell Curl', 'BICEPS'),
       (17, 'Dumbbell Curl', 'BICEPS'),
       (18, 'Hammer Curl', 'BICEPS'),
       (19, 'Preacher Curl', 'BICEPS'),
       (20, 'Cable Curl', 'BICEPS');
INSERT INTO public.exercise (id, "name", muscle_group)
VALUES (21, 'Tricep Dips', 'TRICEPS'),
       (22, 'Close Grip Bench Press', 'TRICEPS'),
       (23, 'Rope Pushdown', 'TRICEPS'),
       (24, 'Overhead Tricep Extension', 'TRICEPS'),
       (25, 'Skull Crushers', 'TRICEPS'),
       (26, 'Barbell Squat', 'TRICEPS'),
       (27, 'Leg Press', 'QUADS'),
       (28, 'Lunges', 'QUADS'),
       (29, 'Leg Extension', 'QUADS'),
       (30, 'Hack Squat', 'QUADS');
INSERT INTO public.exercise (id, "name", muscle_group)
VALUES (31, 'Romanian Deadlift', 'HAMSTRINGS'),
       (32, 'Lying Leg Curl', 'HAMSTRINGS'),
       (33, 'Seated Leg Curl', 'HAMSTRINGS'),
       (34, 'Good Mornings', 'HAMSTRINGS'),
       (35, 'Glute Ham Raise', 'HAMSTRINGS'),
       (36, 'Hip Thrust', 'GLUTES'),
       (37, 'Bulgarian Split Squat', 'GLUTES'),
       (38, 'Cable Kickbacks', 'GLUTES'),
       (39, 'Step Ups', 'GLUTES'),
       (40, 'Sumo Deadlift', 'GLUTES');
INSERT INTO public.exercise (id, "name", muscle_group)
VALUES (41, 'Standing Calf Raise', 'CALVES'),
       (42, 'Seated Calf Raise', 'CALVES'),
       (43, 'Incline Bench Press', 'CHEST');

-- ==================== program ====================

INSERT INTO public."program" (id, program_length, user_id, "name")
VALUES (1, 8, 1, 'Strength'),
       (2, 10, 1, 'Strength'),
       (3, 8, 1, 'Hypertrophy');

-- ==================== program_week ====================

INSERT INTO public.program_week (id, week_number, program_id)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 3, 1),
       (4, 4, 1),
       (5, 5, 1),
       (6, 6, 1),
       (7, 7, 1),
       (8, 8, 1);

-- ==================== program_day ====================

INSERT INTO public.program_day (id, muscle_group, program_week_id)
VALUES (1, 'Chest/Shoulder', 1),
       (2, 'Back/Shoulder', 1),
       (3, 'Legs', 1),
       (4, 'Arms', 1),
       (5, 'Chest/Triceps', 2),
       (6, 'Back/Biceps', 2),
       (7, 'Legs', 2),
       (8, 'Shoulders', 2),
       (9, 'Arms', 2);

-- ==================== program_day_exercise ====================

INSERT INTO public.program_day_exercise (id, target_sets, target_reps, exercise_id, program_day_id, exercise_number)
VALUES (1, 3, 6, 1, 1, 1),
       (2, 3, 6, 11, 1, 2),
       (3, 3, 8, 3, 1, 3),
       (4, 3, 8, 12, 1, 4),
       (5, 3, 10, 4, 1, 5),
       (6, 3, 8, 5, 1, 6),
       (7, 3, 6, 6, 2, 1),
       (8, 3, 10, 14, 2, 2),
       (9, 3, 8, 7, 2, 3),
       (10, 3, 8, 15, 2, 4);
INSERT INTO public.program_day_exercise (id, target_sets, target_reps, exercise_id, program_day_id, exercise_number)
VALUES (11, 3, 8, 8, 2, 5),
       (12, 3, 8, 9, 2, 6),
       (13, 4, 8, 26, 3, 1),
       (14, 4, 8, 27, 3, 2),
       (15, 3, 8, 31, 3, 3),
       (16, 3, 8, 34, 3, 4),
       (17, 3, 10, 39, 3, 5),
       (18, 3, 10, 41, 3, 6),
       (19, 3, 8, 16, 4, 1),
       (20, 3, 8, 21, 4, 2);
INSERT INTO public.program_day_exercise (id, target_sets, target_reps, exercise_id, program_day_id, exercise_number)
VALUES (21, 3, 8, 17, 4, 3),
       (22, 3, 8, 22, 4, 4),
       (23, 3, 10, 18, 4, 5),
       (24, 3, 10, 23, 4, 6);

-- ==================== exercise_session ====================

INSERT INTO public.exercise_session (id, notes, program_day_exercise_id, week_number)
VALUES (569, 'Stay', 1, 1);

-- ==================== set ====================

INSERT INTO public."set" (id, set_order, exercise_session_id, achieved_reps, weight_done)
VALUES (903, 1, 569, 6, 105),
       (904, 1, 569, 6, 100),
       (905, 1, 569, 6, 100);


