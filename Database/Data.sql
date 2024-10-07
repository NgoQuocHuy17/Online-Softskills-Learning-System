-- 1. Insert data into the users table
INSERT INTO users (full_name, gender, email, password_hash, role, avatar_url)
VALUES 
('Nguyen Van A', 'Male', 'a.nguyen@example.com', 'hash_pass_1', 'Admin', 'avatar1.jpg'),
('Tran Thi B', 'Female', 'b.tran@example.com', 'hash_pass_2', 'Instructor', 'avatar2.jpg'),
('Le Van C', 'Male', 'c.le@example.com', 'hash_pass_3', 'Student', 'avatar3.jpg'),
('Pham Thi D', 'Female', 'd.pham@example.com', 'hash_pass_4', 'Guest', 'avatar4.jpg'),
('Do Van E', 'Other', 'e.do@example.com', 'hash_pass_5', 'Student', 'avatar5.jpg'),
('Nguyen Van F', 'Male', 'f.nguyen@example.com', 'hash_pass_6', 'Instructor', 'avatar6.jpg'),
('Tran Thi G', 'Female', 'g.tran@example.com', 'hash_pass_7', 'Admin', 'avatar7.jpg');
GO

-- 2. Insert data into the user_contacts table
INSERT INTO user_contacts (user_id, contact_type, contact_value)
VALUES 
(1, 'Phone', '0909123456'),
(2, 'Phone', '0909234567'),
(3, 'Email', 'c.le2@example.com'),
(4, 'Phone', '0909345678'),
(5, 'Phone', '0909456789'),
(6, 'Email', 'f.nguyen2@example.com');
GO

-- 3. Insert data into the login_history table
INSERT INTO login_history (user_id, ip_address)
VALUES 
(1, '192.168.1.1'),
(2, '192.168.1.2'),
(3, '192.168.1.3'),
(4, '192.168.1.4'),
(5, '192.168.1.5'),
(6, '192.168.1.6');
GO



-- 4. Insert data into the courses table
INSERT INTO courses (title, tag_line, description, category, basic_package_price, advanced_package_price, owner_id, status, is_sponsored)
VALUES 
('Effective Communication', 'Learn how to communicate effectively', 'This course will teach you how to improve your communication skills.', 'Communication', 100.00, 150.00, 2, 'Published', 1),
('Team Leadership', 'Become a strong leader', 'Master the skills needed to lead teams to success.', 'Leadership', 200.00, 250.00, 6, 'Draft', 0),
('Emotional Intelligence', 'Understand your emotions better', 'A comprehensive guide to emotional intelligence.', 'Psychology', 120.00, 170.00, 2, 'Published', 0),
('Conflict Resolution', 'Resolve conflicts easily', 'Techniques and strategies to resolve conflicts effectively.', 'Negotiation', 180.00, 230.00, 1, 'Published', 1),
('Time Management', 'Manage your time better', 'Learn time management techniques for increased productivity.', 'Productivity', 90.00, 140.00, 3, 'Published', 0);

-- Communication courses
('Public Speaking Mastery', 'Conquer your fear of public speaking', 'Learn techniques to become a confident and persuasive speaker.', 'Communication', 150.00, 250.00, 3, 'Published', 1),
('Business Writing Skills', 'Write with clarity and impact', 'Improve your professional writing for better business communication.', 'Communication', 120.00, 200.00, 2, 'Published', 0),
('Nonverbal Communication', 'Master the art of body language', 'Understand and utilize nonverbal cues for effective communication.', 'Communication', 90.00, 160.00, 4, 'Published', 0),

-- Leadership courses
('Strategic Leadership', 'Lead with vision and purpose', 'Develop strategic thinking skills to guide your organization to success.', 'Leadership', 250.00, 400.00, 1, 'Published', 1),
('Ethical Leadership', 'Lead with integrity', 'Explore ethical decision-making in leadership roles.', 'Leadership', 180.00, 300.00, 5, 'Published', 0),
('Adaptive Leadership', 'Thrive in changing environments', 'Learn to lead effectively in dynamic and uncertain situations.', 'Leadership', 200.00, 350.00, 2, 'Draft', 0),

-- Psychology courses
('Cognitive Behavioral Therapy', 'Change your thoughts, change your life', 'Learn the basics of CBT for personal growth and well-being.', 'Psychology', 180.00, 320.00, 6, 'Published', 1),
('Positive Psychology', 'Cultivate happiness and well-being', 'Explore the science of happiness and human flourishing.', 'Psychology', 140.00, 240.00, 3, 'Published', 0),
('Social Psychology', 'Understand human interactions', 'Explore how people think about, influence, and relate to others.', 'Psychology', 160.00, 280.00, 4, 'Draft', 0),

-- Negotiation courses
('Win-Win Negotiation', 'Create value in every deal', 'Master techniques for mutually beneficial negotiations.', 'Negotiation', 220.00, 380.00, 5, 'Published', 1),
('Cross-Cultural Negotiation', 'Navigate global deals', 'Learn to negotiate effectively across different cultures.', 'Negotiation', 200.00, 350.00, 1, 'Published', 0),
('Advanced Bargaining Strategies', 'Elevate your negotiation skills', 'Dive deep into complex negotiation scenarios and strategies.', 'Negotiation', 250.00, 450.00, 2, 'Draft', 0),

-- Productivity courses
('Getting Things Done', 'Master personal productivity', 'Learn David Allens GTD methodology for stress-free productivity.', 'Productivity', 130.00, 220.00, 4, 'Published', 1),
('The Pomodoro Technique', 'Boost focus and productivity', 'Master the time management method to accomplish more.', 'Productivity', 80.00, 140.00, 6, 'Published', 0),
('Mindfulness for Productivity', 'Achieve more by doing less', 'Learn how mindfulness can enhance your focus and productivity.', 'Productivity', 100.00, 180.00, 3, 'Published', 0),

-- New category: Project Management
('Agile Project Management', 'Lead agile projects successfully', 'Master the principles and practices of Agile project management.', 'Project Management', 220.00, 380.00, 2, 'Published', 1),
('Risk Management', 'Identify and mitigate project risks', 'Learn strategies to manage risks in complex projects.', 'Project Management', 180.00, 320.00, 5, 'Published', 0),
('Stakeholder Management', 'Build strong project relationships', 'Develop skills to effectively manage project stakeholders.', 'Project Management', 160.00, 280.00, 1, 'Draft', 0),

-- New category: Personal Finance
('Budgeting Basics', 'Take control of your finances', 'Learn how to create and stick to a personal budget.', 'Personal Finance', 90.00, 160.00, 3, 'Published', 1),
('Investing for Beginners', 'Start your investment journey', 'Understand the basics of investing and building wealth.', 'Personal Finance', 150.00, 250.00, 4, 'Published', 0),
('Retirement Planning', 'Secure your financial future', 'Learn strategies for effective retirement planning.', 'Personal Finance', 180.00, 320.00, 6, 'Draft', 0);
GO

-- 5. Insert data into the course_sale table
INSERT INTO course_sale (course_id, basic_package_sale_price, advanced_package_sale_price, start_date, end_date)
VALUES 
(1, 80.00, 120.00, '2024-01-01', '2024-01-31'),
(2, 150.00, 200.00, '2024-02-01', '2024-02-28'),
(3, 100.00, 150.00, '2024-03-01', '2024-03-31'),
(4, 160.00, 210.00, '2024-04-01', '2024-04-30'),
(5, 70.00, 120.00, '2024-05-01', '2024-05-31');
GO

-- 6. Insert data into the course_details table
INSERT INTO course_details (course_id, section_title, content, image_path, video_url)
VALUES 
(1, 'Introduction to Communication', 'In this section, we cover the basics of effective communication...', 'image1.jpg', 'video1.mp4'),
(2, 'Leadership Styles', 'Explore various leadership styles and their impact...', 'image2.jpg', 'video2.mp4'),
(3, 'What is Emotional Intelligence?', 'Understanding emotional intelligence is crucial for personal growth...', 'image3.jpg', 'video3.mp4'),
(4, 'Conflict Resolution Techniques', 'Learn different strategies for resolving conflicts...', 'image4.jpg', 'video4.mp4'),
(5, 'Time Management Strategies', 'Discover tips for managing your time effectively...', 'image5.jpg', 'video5.mp4');
GO

-- 7. Insert data into the course_translations table
INSERT INTO course_translations (course_id, language_code, title, description)
VALUES 
(1, 'vi', 'Giao tiếp hiệu quả', 'Khóa học về cách giao tiếp hiệu quả.'),
(2, 'vi', 'Lãnh đạo đội nhóm', 'Khóa học về lãnh đạo đội nhóm thành công.'),
(3, 'es', 'Inteligencia Emocional', 'Una guía completa para comprender la inteligencia emocional.'),
(4, 'fr', 'Résolution de conflits', 'Techniques pour résoudre des conflits de manière efficace.'),
(5, 'de', 'Zeitmanagement', 'Lernen Sie Zeitmanagement-Techniken für eine höhere Produktivität.');
GO

-- 8. Insert data into the registrations table
INSERT INTO registrations (user_id, course_id, package_name, total_cost, valid_from, valid_to)
VALUES 
(3, 1, 'Basic', 100.00, '2024-01-01', '2024-06-30'),
(4, 3, 'Advanced', 170.00, '2024-02-01', '2024-07-31'),
(5, 4, 'Basic', 180.00, '2024-03-01', '2024-08-31'),
(2, 2, 'Advanced', 250.00, '2024-04-01', '2024-09-30'),
(1, 5, 'Basic', 90.00, '2024-05-01', '2024-10-31');
GO

-- 9. Insert data into the user_courses table
INSERT INTO user_courses (user_id, course_id, role, status)
VALUES 
(3, 1, 'Student', 'Enrolled'),
(4, 2, 'Student', 'Enrolled'),
(5, 3, 'Student', 'Enrolled'),
(2, 4, 'Instructor', 'Enrolled'),
(1, 5, 'Student', 'Completed');
GO

-- 10. Insert data into the questions table
INSERT INTO questions (course_id, content, level)
VALUES 
(1, 'What is the main goal of communication?', 'Easy'),
(2, 'What are the key leadership traits?', 'Medium'),
(3, 'How can emotional intelligence help in personal growth?', 'Hard'),
(4, 'Which strategy is best for conflict resolution?', 'Medium'),
(5, 'How does time management impact productivity?', 'Easy');
GO

-- 11. Insert data into the answer_options table
INSERT INTO answer_options (question_id, option_text, is_correct)
VALUES 
(1, 'To transfer information', 1),
(1, 'To confuse the listener', 0),
(2, 'Being strict with team members', 0),
(2, 'Being empathetic and decisive', 1),
(3, 'Ignoring emotions', 0),
(3, 'Understanding and managing emotions', 1),
(4, 'Ignoring the conflict', 0),
(4, 'Finding a win-win solution', 1),
(5, 'It reduces stress', 1),
(5, 'It wastes time', 0);
GO

-- 12. Insert data into the quizzes table
INSERT INTO quizzes (course_id, title, duration, pass_rate)
VALUES 
(1, 'Communication Quiz', 30, 75.00),
(2, 'Leadership Quiz', 45, 80.00),
(3, 'Emotional Intelligence Quiz', 60, 85.00);
GO

-- 13. Insert data into the quiz_questions table
INSERT INTO quiz_questions (quiz_id, question_id)
VALUES 
(1, 1),
(2, 2),
(3, 3),
(3, 4),
(1, 5);
GO

-- 14. Insert data into the categories table
INSERT INTO categories (name)
VALUES 
('Leadership'),
('Communication'),
('Psychology'),
('Negotiation'),
('Productivity');
GO

-- 15. Insert data into the blog_posts table
INSERT INTO blog_posts (title, thumbnail_url, category_id, content, author_id)
VALUES 
('Effective Communication Tips', 'thumb1.jpg', 2, 'This blog post discusses the best communication tips.', 1),
('Leadership in Modern Organizations', 'thumb2.jpg', 1, 'Leadership strategies in modern workplaces.', 2),
('Emotional Intelligence for Success', 'thumb3.jpg', 3, 'How emotional intelligence can lead to success.', 3);
('Conflict Resolution Strategies', 'thumb4.jpg', 4, 'Best strategies to resolve conflicts in the workplace.', 4, 'Published'),
('Time Management for Productivity', 'thumb5.jpg', 5, 'How to manage your time effectively for better productivity.', 5, 'Published'),
('The Power of Active Listening', 'thumb6.jpg', 2, 'Learn how active listening can improve your communication.', 1, 'Published'),
('Building Team Leadership', 'thumb7.jpg', 1, 'How to build and strengthen leadership within teams.', 2, 'Published'),
('Understanding Emotional Triggers', 'thumb8.jpg', 3, 'A deep dive into emotional triggers and how to manage them.', 3, 'Published'),
('Negotiation Techniques for Success', 'thumb9.jpg', 4, 'Essential negotiation techniques for successful deals.', 4, 'Published'),
('Mastering Time Blocking', 'thumb10.jpg', 5, 'A guide to mastering time blocking for personal and professional use.', 5, 'Published'),
('Developing Empathy in Leadership', 'thumb11.jpg', 1, 'How developing empathy can make you a better leader.', 2, 'Published'),
('The Role of Emotional Intelligence in Negotiation', 'thumb12.jpg', 3, 'How emotional intelligence plays a role in successful negotiations.', 3, 'Published'),
('Advanced Communication Strategies', 'thumb13.jpg', 2, 'Advanced techniques to communicate more effectively.', 1, 'Published'),
('Boosting Team Morale Through Leadership', 'thumb14.jpg', 1, 'Leadership strategies to boost team morale and productivity.', 2, 'Published'),
('Psychological Approaches to Conflict Resolution', 'thumb15.jpg', 3, 'Using psychology to effectively resolve conflicts.', 4, 'Published'),
('Improving Personal Productivity', 'thumb16.jpg', 5, 'Simple tips to improve personal productivity.', 5, 'Published'),
('Draft Blog Post 1', 'thumb17.jpg', 2, 'This blog post is still in draft.', 1, 'Draft'),
('Draft Blog Post 2', 'thumb18.jpg', 3, 'This blog post is under construction.', 3, 'Draft'),
('Draft Blog Post 3', 'thumb19.jpg', 4, 'Waiting for approval.', 4, 'Draft'),
('Draft Blog Post 4', 'thumb20.jpg', 5, 'This is a future blog post.', 5, 'Draft'),
('Time Management in the Modern World', 'thumb21.jpg', 5, 'Strategies to manage your time in a fast-paced world.', 5, 'Published'),
('How to Negotiate Like a Pro', 'thumb22.jpg', 4, 'Learn the secrets of successful negotiation.', 4, 'Published'),
('The Importance of Emotional Intelligence in Leadership', 'thumb23.jpg', 1, 'Emotional intelligence is key to effective leadership.', 3, 'Published'),
('Communication Pitfalls to Avoid', 'thumb24.jpg', 2, 'Common communication mistakes and how to avoid them.', 1, 'Published'),
('Managing Stress through Time Management', 'thumb25.jpg', 5, 'How to reduce stress through better time management.', 5, 'Published');
GO

-- 16. Insert data into the tags table
INSERT INTO tags (name)
VALUES 
('Communication'), 
('Leadership'), 
('Emotional Intelligence'), 
('Success'), 
('Productivity');
GO

-- 17. Insert data into the blog_post_tags table
INSERT INTO blog_post_tags (blog_post_id, tag_id)
VALUES 
(1, 1),
(2, 2),
(3, 3),
(3, 4),
(1, 5);
GO

-- 18. Insert data into the sliders table
INSERT INTO sliders (title, image_url, backlink)
VALUES 
('Welcome to SoftSkills Online Learning!', 'slider1.jpg', '/home'),
('Sign up for the best leadership courses!', 'slider2.jpg', '/courses'),
('Improve your emotional intelligence now!', 'slider3.jpg', '/courses');
GO

-- 19. Insert data into the settings table
INSERT INTO settings (setting_type, value, order_num)
VALUES 
('Site Name', 'SoftSkills Online Learning System', 1),
('Logo URL', 'logo.jpg', 2),
('Footer Text', '© 2024 SoftSkills Online Learning', 3);
GO
