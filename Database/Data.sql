-- Insert users
INSERT INTO users (full_name, gender, email, password, role, avatar_url, created_at, updated_at, hash, isValid) 
VALUES 
('Nguyen Van A', 'Male', 'nguyenvana@example.com', 'password123', 'Admin', 'assets/img/user/user1.jpg', GETDATE(), GETDATE(), 'hash1', 1),
('Tran Thi B', 'Female', 'tranthib@example.com', 'password456', 'Student', 'assets/img/user/user2.jpg', GETDATE(), GETDATE(), 'hash2', 1),
('Le Van C', 'Male', 'levanc@example.com', 'password789', 'Student', 'assets/img/user/user3.jpg', GETDATE(), GETDATE(), 'hash3', 1),
('Pham Minh D', 'Male', 'phamind@example.com', 'password321', 'Teacher', 'assets/img/user/user4.jpg', GETDATE(), GETDATE(), 'hash4', 1),
('Vo Thi E', 'Female', 'vothie@example.com', 'password654', 'Teacher', 'assets/img/user/user5.jpg', GETDATE(), GETDATE(), 'hash5', 1),
('Nguyen Van F', 'Male', 'nguyenf@example.com', 'password987', 'Admin', 'assets/img/user/user6.jpg', GETDATE(), GETDATE(), 'hash6', 1),
('Tran Thi G', 'Female', 'trang@example.com', 'password159', 'Student', 'assets/img/user/user7.jpg', GETDATE(), GETDATE(), 'hash7', 1),
('Le Van H', 'Male', 'levanh@example.com', 'password753', 'Teacher', 'assets/img/user/user8.jpg', GETDATE(), GETDATE(), 'hash8', 1),
('Pham Minh I', 'Male', 'phami@example.com', 'password852', 'Teacher', 'assets/img/user/user9.jpg', GETDATE(), GETDATE(), 'hash9', 1),
('Vo Thi J', 'Female', 'voj@example.com', 'password246', 'Student', 'assets/img/user/user10.jpg', GETDATE(), GETDATE(), 'hash10', 1),
('Nguyen Van K', 'Male', 'nguyenk@example.com', 'password369', 'Admin', 'assets/img/user/user11.jpg', GETDATE(), GETDATE(), 'hash11', 1),
('Tran Thi L', 'Female', 'tranl@example.com', 'password147', 'Teacher', 'assets/img/user/user12.jpg', GETDATE(), GETDATE(), 'hash12', 1);
GO

INSERT INTO user_videos (user_id, video_url) 
VALUES 
(1, 'assets/video/Video1.mp4'),
(2, 'assets/video/Video2.mp4'),
(3, 'assets/video/Video3.mp4'),
(4, 'assets/video/Video4.mp4'),
(5, 'assets/video/Video5.mp4'),
(6, 'assets/video/Video6.mp4'),
(7, 'assets/video/Video1.mp4'),
(8, 'assets/video/Video2.mp4'),
(9, 'assets/video/Video3.mp4'),
(10, 'assets/video/Video4.mp4'),
(11, 'assets/video/Video5.mp4'),
(12, 'assets/video/Video6.mp4')

-- Insert user_contacts
INSERT INTO user_contacts (user_id, contact_type, contact_value, is_preferred) 
VALUES 
(1, 'Phone', '0123456789', 1),  
(1, 'Email', 'nguyenvana_work@example.com', 0), 
(1, 'Email', 'nguyenvana_home@example.com', 0),  
(2, 'Phone', '0987654321', 0),  
(2, 'Phone', '0981122334', 1),  
(2, 'Email', 'tranthib_email@example.com', 1),  
(3, 'Phone', '0345678901', 0),  
(3, 'Email', 'levanc_email@example.com', 0),  
(3, 'Email', 'levanc_alternate@example.com', 1),  
(4, 'Phone', '0246813579', 1),  
(4, 'Email', 'phamind_email@example.com', 0),  
(5, 'Phone', '0147246385', 0),  
(5, 'Phone', '0152958304', 1),  
(5, 'Email', 'vothie_email@example.com', 0),  
(6, 'Phone', '0161234567', 1),  
(6, 'Email', 'nguyenf_home@example.com', 0),  
(6, 'Email', 'nguyenf_work@example.com', 0),  
(7, 'Phone', '0179876543', 0),  
(7, 'Email', 'trang_email@example.com', 1),  
(8, 'Phone', '0186543210', 1),  
(8, 'Email', 'levanh_email@example.com', 0),  
(9, 'Phone', '0191234567', 0),  
(9, 'Email', 'phami_email@example.com', 0),  
(10, 'Phone', '0209876543', 1),  
(10, 'Email', 'voj_email@example.com', 0),  
(11, 'Phone', '0211234567', 0),  
(11, 'Email', 'nguyenk_email@example.com', 1),  
(12, 'Phone', '0229876543', 1),  
(12, 'Email', 'tranl_email@example.com', 0);
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
('Time Management', 'Manage your time better', 'Learn time management techniques for increased productivity.', 'Productivity', 90.00, 140.00, 3, 'Published', 0),

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

-- Project Management courses
('Agile Project Management', 'Lead agile projects successfully', 'Master the principles and practices of Agile project management.', 'Project Management', 220.00, 380.00, 2, 'Published', 1),
('Risk Management', 'Identify and mitigate project risks', 'Learn strategies to manage risks in complex projects.', 'Project Management', 180.00, 320.00, 5, 'Published', 0),
('Stakeholder Management', 'Build strong project relationships', 'Develop skills to effectively manage project stakeholders.', 'Project Management', 160.00, 280.00, 1, 'Draft', 0),

-- Personal Finance courses
('Budgeting Basics', 'Take control of your finances', 'Learn how to create and stick to a personal budget.', 'Personal Finance', 90.00, 160.00, 3, 'Published', 1),
('Investing for Beginners', 'Start your investment journey', 'Understand the basics of investing and building wealth.', 'Personal Finance', 150.00, 250.00, 4, 'Published', 0),
('Retirement Planning', 'Secure your financial future', 'Learn strategies for effective retirement planning.', 'Personal Finance', 180.00, 320.00, 6, 'Draft', 0);
GO

-- 5. Insert data into the packages table
INSERT INTO packages (course_id, package_name, price, sale_price, access_duration) 
VALUES 
(1, 'Basic Package', 65.00, 52.00, 30), 
(1, 'Premium Package', 95.00, 76.00, 90),
(2, 'Basic Package', 60.00, 48.00, 30), 
(2, 'Premium Package', 99.00, 79.20, 90),
(3, 'Basic Package', 70.00, 56.00, 30), 
(3, 'Premium Package', 90.00, 72.00, 90),
(4, 'Basic Package', 62.00, 49.60, 30), 
(4, 'Premium Package', 92.00, 73.60, 90),
(5, 'Basic Package', 67.00, 53.60, 30), 
(5, 'Premium Package', 94.00, 75.20, 90),
(6, 'Basic Package', 68.00, 54.40, 30), 
(6, 'Premium Package', 98.00, 78.40, 90),
(7, 'Basic Package', 63.00, 50.40, 30), 
(7, 'Premium Package', 93.00, 74.40, 90),
(8, 'Basic Package', 66.00, 52.80, 30), 
(8, 'Premium Package', 96.00, 76.80, 90),
(9, 'Basic Package', 61.00, 48.80, 30), 
(9, 'Premium Package', 97.00, 77.60, 90),
(10, 'Basic Package', 64.00, 51.20, 30), 
(10, 'Premium Package', 91.00, 72.80, 90);

INSERT INTO registrations (user_id, package_id, course_id, total_cost)
VALUES
(1, 1, 1, 65.00),   -- Basic Package cho course_id 1
(1, 2, 1, 95.00),   -- Premium Package cho course_id 1
(1, 3, 2, 60.00),   -- Basic Package cho course_id 2
(1, 4, 2, 99.00),   -- Premium Package cho course_id 2
(1, 5, 3, 70.00),   -- Basic Package cho course_id 3
(1, 6, 3, 90.00),   -- Premium Package cho course_id 3
(1, 7, 4, 62.00),   -- Basic Package cho course_id 4
(1, 8, 4, 92.00),   -- Premium Package cho course_id 4
(1, 9, 5, 67.00),   -- Basic Package cho course_id 5
(1, 10, 5, 94.00),  -- Premium Package cho course_id 5
(1, 11, 6, 68.00),  -- Basic Package cho course_id 6
(1, 12, 6, 98.00),  -- Premium Package cho course_id 6
(1, 13, 7, 63.00),  -- Basic Package cho course_id 7
(1, 14, 7, 93.00),  -- Premium Package cho course_id 7
(1, 15, 8, 66.00),  -- Basic Package cho course_id 8
(1, 16, 8, 96.00),  -- Premium Package cho course_id 8
(1, 17, 9, 61.00),  -- Basic Package cho course_id 9
(1, 18, 9, 97.00),  -- Premium Package cho course_id 9
(1, 19, 10, 64.00), -- Basic Package cho course_id 10
(1, 20, 10, 91.00); -- Premium Package cho course_id 10


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
--INSERT INTO registrations (user_id, course_id, package_name, total_cost, valid_from, valid_to)
--VALUES 
--GO

-- 9. Insert data into the user_courses table
INSERT INTO user_courses (user_id, course_id)
VALUES 
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(1, 19),
(1, 20),
(1, 21),
(1, 22),
(1, 23),
(1, 24),
(1, 25),
(1, 26);


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
('Emotional Intelligence for Success', 'thumb3.jpg', 3, 'How emotional intelligence can lead to success.', 3),
('Conflict Resolution Strategies', 'thumb4.jpg', 4, 'Best strategies to resolve conflicts in the workplace.', 4),
('Time Management for Productivity', 'thumb5.jpg', 5, 'How to manage your time effectively for better productivity.', 5),
('The Power of Active Listening', 'thumb6.jpg', 2, 'Learn how active listening can improve your communication.', 1),
('Building Team Leadership', 'thumb7.jpg', 1, 'How to build and strengthen leadership within teams.', 2),
('Understanding Emotional Triggers', 'thumb8.jpg', 3, 'A deep dive into emotional triggers and how to manage them.', 3),
('Negotiation Techniques for Success', 'thumb9.jpg', 4, 'Essential negotiation techniques for successful deals.', 4),
('Mastering Time Blocking', 'thumb10.jpg', 5, 'A guide to mastering time blocking for personal and professional use.', 5),
('Developing Empathy in Leadership', 'thumb11.jpg', 1, 'How developing empathy can make you a better leader.', 2),
('The Role of Emotional Intelligence in Negotiation', 'thumb12.jpg', 3, 'How emotional intelligence plays a role in successful negotiations.', 3),
('Advanced Communication Strategies', 'thumb13.jpg', 2, 'Advanced techniques to communicate more effectively.', 1),
('Boosting Team Morale Through Leadership', 'thumb14.jpg', 1, 'Leadership strategies to boost team morale and productivity.', 2),
('Psychological Approaches to Conflict Resolution', 'thumb15.jpg', 3, 'Using psychology to effectively resolve conflicts.', 4),
('Improving Personal Productivity', 'thumb16.jpg', 5, 'Simple tips to improve personal productivity.', 5),
('Draft Blog Post 1', 'thumb17.jpg', 2, 'This blog post is still in draft.', 1),
('Draft Blog Post 2', 'thumb18.jpg', 3, 'This blog post is under construction.', 3),
('Draft Blog Post 3', 'thumb19.jpg', 4, 'Waiting for approval.', 4),
('Draft Blog Post 4', 'thumb20.jpg', 5, 'This is a future blog post.', 5),
('Time Management in the Modern World', 'thumb21.jpg', 5, 'Strategies to manage your time in a fast-paced world.', 5),
('How to Negotiate Like a Pro', 'thumb22.jpg', 4, 'Learn the secrets of successful negotiation.', 4),
('The Importance of Emotional Intelligence in Leadership', 'thumb23.jpg', 1, 'Emotional intelligence is key to effective leadership.', 3),
('Communication Pitfalls to Avoid', 'thumb24.jpg', 2, 'Common communication mistakes and how to avoid them.', 1),
('Managing Stress through Time Management', 'thumb25.jpg', 5, 'How to reduce stress through better time management.', 5);
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
