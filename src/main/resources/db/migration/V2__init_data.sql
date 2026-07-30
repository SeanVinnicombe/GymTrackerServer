-- ==================== users ====================

INSERT INTO public.users (id, first_name, last_name, phone_number, email, "password", "role", created_at, updated_at)
VALUES (1, 'Sean', 'Vinnicombe', '0762005123', 'sv@gmail.com',
        '$2a$12$tABYZqBnm2YadkV2EDeqb.jSZpJHs9PsLOyHXoatWekA6Nccggpay', 'ADMIN',
        '2024-01-01 08:00:00', '2024-01-07 18:00:00');

-- ==================== exercise ====================

INSERT INTO public.exercise (id, "name", muscle_group, created_at)
VALUES (1, 'Bench Press', 'CHEST', '2024-01-01 08:00:00'),
       (2, 'Incline Dumbbell Press', 'CHEST', '2024-01-01 08:00:00'),
       (3, 'Chest Fly', 'CHEST', '2024-01-01 08:00:00'),
       (4, 'Cable Crossovers', 'CHEST', '2024-01-01 08:00:00'),
       (5, 'Decline Bench Press', 'CHEST', '2024-01-01 08:00:00'),
       (6, 'Pull Ups', 'BACK', '2024-01-01 08:00:00'),
       (7, 'Lat Pulldown', 'BACK', '2024-01-01 08:00:00'),
       (8, 'Barbell Row', 'BACK', '2024-01-01 08:00:00'),
       (9, 'Seated Cable Row', 'BACK', '2024-01-01 08:00:00'),
       (10, 'Deadlift', 'BACK', '2024-01-01 08:00:00');
INSERT INTO public.exercise (id, "name", muscle_group, created_at)
VALUES (11, 'Shoulder Press', 'SHOULDERS', '2024-01-01 08:00:00'),
       (12, 'Lateral Raises', 'SHOULDERS', '2024-01-01 08:00:00'),
       (13, 'Front Raises', 'SHOULDERS', '2024-01-01 08:00:00'),
       (14, 'Rear Delt Fly', 'SHOULDERS', '2024-01-01 08:00:00'),
       (15, 'Arnold Press', 'SHOULDERS', '2024-01-01 08:00:00'),
       (16, 'Barbell Curl', 'BICEPS', '2024-01-01 08:00:00'),
       (17, 'Dumbbell Curl', 'BICEPS', '2024-01-01 08:00:00'),
       (18, 'Hammer Curl', 'BICEPS', '2024-01-01 08:00:00'),
       (19, 'Preacher Curl', 'BICEPS', '2024-01-01 08:00:00'),
       (20, 'Cable Curl', 'BICEPS', '2024-01-01 08:00:00');
INSERT INTO public.exercise (id, "name", muscle_group, created_at)
VALUES (21, 'Tricep Dips', 'TRICEPS', '2024-01-01 08:00:00'),
       (22, 'Close Grip Bench Press', 'TRICEPS', '2024-01-01 08:00:00'),
       (23, 'Rope Pushdown', 'TRICEPS', '2024-01-01 08:00:00'),
       (24, 'Overhead Tricep Extension', 'TRICEPS', '2024-01-01 08:00:00'),
       (25, 'Skull Crushers', 'TRICEPS', '2024-01-01 08:00:00'),
       (26, 'Barbell Squat', 'TRICEPS', '2024-01-01 08:00:00'),
       (27, 'Leg Press', 'QUADS', '2024-01-01 08:00:00'),
       (28, 'Lunges', 'QUADS', '2024-01-01 08:00:00'),
       (29, 'Leg Extension', 'QUADS', '2024-01-01 08:00:00'),
       (30, 'Hack Squat', 'QUADS', '2024-01-01 08:00:00');
INSERT INTO public.exercise (id, "name", muscle_group, created_at)
VALUES (31, 'Romanian Deadlift', 'HAMSTRINGS', '2024-01-01 08:00:00'),
       (32, 'Lying Leg Curl', 'HAMSTRINGS', '2024-01-01 08:00:00'),
       (33, 'Seated Leg Curl', 'HAMSTRINGS', '2024-01-01 08:00:00'),
       (34, 'Good Mornings', 'HAMSTRINGS', '2024-01-01 08:00:00'),
       (35, 'Glute Ham Raise', 'HAMSTRINGS', '2024-01-01 08:00:00'),
       (36, 'Hip Thrust', 'GLUTES', '2024-01-01 08:00:00'),
       (37, 'Bulgarian Split Squat', 'GLUTES', '2024-01-01 08:00:00'),
       (38, 'Cable Kickbacks', 'GLUTES', '2024-01-01 08:00:00'),
       (39, 'Step Ups', 'GLUTES', '2024-01-01 08:00:00'),
       (40, 'Sumo Deadlift', 'GLUTES', '2024-01-01 08:00:00');
INSERT INTO public.exercise (id, "name", muscle_group, created_at)
VALUES (41, 'Standing Calf Raise', 'CALVES', '2024-01-01 08:00:00'),
       (42, 'Seated Calf Raise', 'CALVES', '2024-01-01 08:00:00'),
       (43, 'Incline Bench Press', 'CHEST', '2024-01-01 08:00:00');

-- ==================== program ====================

INSERT INTO public."program" (id, "name", program_length, user_id, status, created_at, updated_at)
VALUES (1, 'Strength', 8, 1, 'ACTIVE', '2024-01-01 08:00:00', '2024-01-15 10:30:00'),
       (2, 'Strength', 10, 1, 'COMPLETED', '2023-10-01 08:00:00', '2023-12-10 09:00:00'),
       (3, 'Hypertrophy', 8, 1, 'INACTIVE', '2024-01-16 08:10:00', '2024-01-16 08:10:00');

-- ==================== program_week ====================
-- Weeks 1 and 2 are underway (startedat populated); weeks 3-8 are scheduled but not yet started.

INSERT INTO public.program_week (id, week_number, program_id, started_at, created_at, updated_at)
VALUES (1, 1, 1, '2024-01-01 09:00:00', '2024-01-01 08:00:00', '2024-01-07 18:00:00'),
       (2, 2, 1, '2024-01-08 09:00:00', '2024-01-01 08:00:00', '2024-01-10 12:00:00'),
       (3, 3, 1, NULL, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (4, 4, 1, NULL, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (5, 5, 1, NULL, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (6, 6, 1, NULL, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (7, 7, 1, NULL, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (8, 8, 1, NULL, '2024-01-01 08:00:00', '2024-01-01 08:00:00');

-- ==================== program_day ====================
-- Days 1-6 (week 1 and first part of week 2) are marked complete; the rest are still pending.

INSERT INTO public.program_day (id, muscle_group, program_week_id, day_order, is_completed, updated_at)
VALUES (1, 'Chest/Shoulder', 1,1, true, '2024-01-01 10:30:00'),
       (2, 'Back/Shoulder', 1,2, true, '2024-01-03 10:30:00'),
       (3, 'Legs', 1, 3, true, '2024-01-05 10:30:00'),
       (4, 'Arms', 1, 4, true, '2024-01-07 10:30:00'),
       (5, 'Chest/Triceps', 2,1 , true, '2024-01-08 10:30:00'),
       (6, 'Back/Biceps', 2, 2, true, '2024-01-10 10:30:00'),
       (7, 'Legs', 2, 3, false, NULL),
       (8, 'Shoulders', 2,4 , false, NULL),
       (9, 'Arms', 2,5 , false, NULL);

-- ==================== program_day_exercise ====================
-- Note: "exercise_number" was renamed to "exercise_order" in the new schema.

INSERT INTO public.program_day_exercise (id, exercise_order, target_reps, target_sets, exercise_id, program_day_id, created_at, updated_at)
VALUES (1, 1, 6, 3, 1, 1, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (2, 2, 6, 3, 11, 1, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (3, 3, 8, 3, 3, 1, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (4, 4, 8, 3, 12, 1, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (5, 5, 10, 3, 4, 1, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (6, 6, 8, 3, 5, 1, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (7, 1, 6, 3, 6, 2, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (8, 2, 10, 3, 14, 2, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (9, 3, 8, 3, 7, 2, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (10, 4, 8, 3, 15, 2, '2024-01-01 08:00:00', '2024-01-01 08:00:00');
INSERT INTO public.program_day_exercise (id, exercise_order, target_reps, target_sets, exercise_id, program_day_id, created_at, updated_at)
VALUES (11, 5, 8, 3, 8, 2, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (12, 6, 8, 3, 9, 2, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (13, 1, 8, 4, 26, 3, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (14, 2, 8, 4, 27, 3, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (15, 3, 8, 3, 31, 3, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (16, 4, 8, 3, 34, 3, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (17, 5, 10, 3, 39, 3, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (18, 6, 10, 3, 41, 3, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (19, 1, 8, 3, 16, 4, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (20, 2, 8, 3, 21, 4, '2024-01-01 08:00:00', '2024-01-01 08:00:00');
INSERT INTO public.program_day_exercise (id, exercise_order, target_reps, target_sets, exercise_id, program_day_id, created_at, updated_at)
VALUES (21, 3, 8, 3, 17, 4, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (22, 4, 8, 3, 22, 4, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (23, 5, 10, 3, 18, 4, '2024-01-01 08:00:00', '2024-01-01 08:00:00'),
       (24, 6, 10, 3, 23, 4, '2024-01-01 08:00:00', '2024-01-01 08:00:00');

-- ==================== exercise_session ====================
-- A handful of logged sessions across week 1 and the start of week 2.

INSERT INTO public.exercise_session (id, notes, program_day_exercise_id, performed_at, created_at, updated_at)
VALUES (1, 'Stayed strong through all sets', 1, '2024-01-01 10:00:00', '2024-01-01 10:15:00', '2024-01-01 10:15:00'),
       (2, 'Shoulders felt tight', 2, '2024-01-01 10:20:00', '2024-01-01 10:35:00', '2024-01-01 10:35:00'),
       (3, 'New PR on pull ups', 7, '2024-01-03 09:00:00', '2024-01-03 09:20:00', '2024-01-03 09:20:00'),
       (4, 'Slight improvement from last week', 1, '2024-01-08 10:00:00', '2024-01-08 10:15:00', '2024-01-08 10:15:00');

-- ==================== set ====================

INSERT INTO public."set" (id, set_order, achieved_reps, weight_done, exercise_session_id, created_at, updated_at)
VALUES (1, 1, 6, 105, 1, '2024-01-01 10:05:00', '2024-01-01 10:05:00'),
       (2, 2, 6, 100, 1, '2024-01-01 10:08:00', '2024-01-01 10:08:00'),
       (3, 3, 6, 100, 1, '2024-01-01 10:11:00', '2024-01-01 10:11:00'),
       (4, 1, 6, 40, 2, '2024-01-01 10:22:00', '2024-01-01 10:22:00'),
       (5, 2, 6, 40, 2, '2024-01-01 10:25:00', '2024-01-01 10:25:00'),
       (6, 3, 5, 40, 2, '2024-01-01 10:28:00', '2024-01-01 10:28:00'),
       (7, 1, 6, 0, 3, '2024-01-03 09:02:00', '2024-01-03 09:02:00'),
       (8, 2, 6, 0, 3, '2024-01-03 09:05:00', '2024-01-03 09:05:00'),
       (9, 3, 5, 0, 3, '2024-01-03 09:08:00', '2024-01-03 09:08:00'),
       (10, 1, 6, 110, 4, '2024-01-08 10:02:00', '2024-01-08 10:02:00'),
       (11, 2, 6, 105, 4, '2024-01-08 10:05:00', '2024-01-08 10:05:00'),
       (12, 3, 6, 105, 4, '2024-01-08 10:08:00', '2024-01-08 10:08:00');

